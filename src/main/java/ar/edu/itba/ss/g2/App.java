package ar.edu.itba.ss.g2;

import ar.edu.itba.ss.g2.args.ArgParser;
import ar.edu.itba.ss.g2.args.Configuration;
import ar.edu.itba.ss.g2.state.Input;
import ar.edu.itba.ss.g2.state.Output;
import ar.edu.itba.ss.g2.utils.FileUtil;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class App {

    public static void main(String[] args) {

        ArgParser parser = new ArgParser(args);
        Configuration configuration = parser.parse();

        if (configuration == null) {
            parser.printHelp();
            System.exit(1);
            return;
        }

        List<Particle> particles;
        long L;

        String outputDirectory = configuration.getOutputDirectory();

        if (configuration.generate()) {

            ParticleGenerator generator;

            int N = configuration.getN();
            L = configuration.getL();

            if (configuration.isConstantRadius()) {
                double r = configuration.getR();

                generator = new ParticleGenerator(L, N, r);
            } else {
                double minR = configuration.getMinR();
                double maxR = configuration.getMaxR();

                generator = new ParticleGenerator(L, N, minR, maxR);
            }

            particles = generator.generate();

            // Save generated input to files
            Input input = new Input(L, particles);

            try {
                FileUtil.serializeInput(input, outputDirectory);
            } catch (IOException e) {
                System.err.println("Error writing to output files: " + e.getMessage());
                System.exit(1);
                return;
            }

        } else {

            String inputDirectory = configuration.getInputDirectory();
            Input input;

            try {
                input = FileUtil.deserializeInput(inputDirectory);
            } catch (IOException e) {
                System.err.println("Error reading input files: " + e.getMessage());
                System.exit(1);
                return;
            }

            particles = input.getParticles();
            L = input.getL();
        }

        // Cell Index Method

        long M = configuration.getM();
        double r;
        if(!configuration.isConstantRadius()) {
            r = configuration.getMaxR();
        } else {
            r = configuration.getR();
        }

        double rc = configuration.getRc();

        if((double)L/M <= rc +r) {
            throw new IllegalArgumentException("L/M <= rc + r");
        }
        boolean isToroidal = configuration.isToroidal();

        // Start time
        long startTime = System.currentTimeMillis();

        Map<Particle, Set<Particle>> neighbours = CellIndexMethod.calculate(particles, L, M, rc, isToroidal);

        // End time
        long endTime = System.currentTimeMillis();

        System.out.println(endTime-startTime);
        Output output = new Output(neighbours, endTime - startTime);

        try {
            FileUtil.serializeOutput(output, outputDirectory);
        } catch (IOException e) {
            System.err.println("Error writing to output files: " + e.getMessage());
            System.exit(1);
            return;
        }

    }
}
