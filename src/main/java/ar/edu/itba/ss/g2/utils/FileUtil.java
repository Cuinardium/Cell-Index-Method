package ar.edu.itba.ss.g2.utils;

import ar.edu.itba.ss.g2.Particle;
import ar.edu.itba.ss.g2.state.Input;
import ar.edu.itba.ss.g2.state.Output;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FileUtil {

    private FileUtil() {
        throw new RuntimeException("Util class");
    }

    public static void serializeInput(Input input, String directory) throws IOException {

        // Create directory if it doesn't exist
        File dir = new File(directory);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        int N = input.getParticles().size();
        List<Particle> particles = input.getParticles();
        long L = input.getL();

        // Static file
        try (FileWriter writer = new FileWriter(new File(directory + "/static.txt"))) {
            writer.write(N + "\n");
            writer.write(L + "\n");
            for (Particle particle : particles) {
                writer.write(particle.getRadius() + "\n");
            }
        }

        // Dynamic file
        try (FileWriter writer = new FileWriter(new File(directory + "/dynamic.txt"))) {
            writer.write("0\n");
            for (Particle particle : particles) {
                writer.write(particle.getX() + " " + particle.getY() + "\n");
            }
        }
    }

    public static Input deserializeInput(String directory) throws IOException {

        long L;
        int N;
        List<Double> radiuses;

        // Read static file
        try (BufferedReader staticReader =
                new BufferedReader(new FileReader(new File(directory + "/static.txt")))) {

            N = Integer.parseInt(staticReader.readLine().trim());
            radiuses = new ArrayList<>(N);

            L = Long.parseLong(staticReader.readLine().trim());

            for (int i = 0; i < N; i++) {
                double radius = Double.parseDouble(staticReader.readLine().trim());
                radiuses.add(radius);
            }
        }

        List<Particle> particles = new ArrayList<>(N);

        // Read dynamic file
        try (BufferedReader dynamicReader =
                new BufferedReader(new FileReader(new File(directory + ("/dynamic.txt"))))) {
            // Skip the first line (timestamp)
            dynamicReader.readLine();

            for (int i = 0; i < N; i++) {
                String[] position = dynamicReader.readLine().trim().split(" ");
                double x = Double.parseDouble(position[0]);
                double y = Double.parseDouble(position[1]);
                double r = radiuses.get(i);

                particles.add(new Particle((long) i, x, y, r));
            }
        }

        return new Input(L, particles);
    }

    public static void serializeOutput(Output output, String directory) throws IOException {

        // Create directory if it doesn't exist
        File dir = new File(directory);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        Map<Particle, List<Particle>> particleNeighbours = output.getParticleNeighbours();
        List<Particle> sortedParticles =
                particleNeighbours.keySet().stream()
                        .sorted(Comparator.comparingLong(Particle::getId))
                        .toList();

        try (FileWriter writer = new FileWriter(new File(directory + "/output.txt"))) {

            // Duration in milliseconds
            writer.write(output.getMillis() + "\n");

            // Particle neighbours
            for (Particle particle : sortedParticles) {
                writer.write(
                        particle.getId()
                                + " "
                                + particleNeighbours.get(particle).stream()
                                        .map(p -> p.getId().toString())
                                        .collect(Collectors.joining(" ")));
            }
        }
    }
}
