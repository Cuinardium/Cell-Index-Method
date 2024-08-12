package ar.edu.itba.ss.g2.args;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import java.util.Comparator;
import java.util.List;

public class ArgParser {

    private static final List<Option> OPTIONS =
            List.of(
                    new Option("h", "help", false, "Show help"),
                    new Option("g", "generate", false, "Generate particles, if not set, input is read from input directory"),
                    new Option("in", "input", true, "Input directory, it must contain 'static.txt' and 'dynamic.txt' files"),
                    new Option("out", "output", true, "Output directory"),

                    // For particle generation
                    new Option("N", "number", true, "Amount of particles to be generated"),
                    new Option("L", "length", true, "Length of the area to be analyzed"),
                    new Option(
                            "r",
                            "radius",
                            true,
                            "[-r r] Constant particle radius, [-r r1:r2] Random uniform radius"
                                + " within r1 and r2"),

                    // For Cell Index Method
                    new Option("M", "matrix", true, "Amount of cells in the matrix: M x M"),
                    new Option("rc", "cutoff", true, "Cutoff radius for the Cell Index Method"));

    private final String[] args;
    private final Options options;

    public ArgParser(String[] args) {
        this.args = args;

        Options options = new Options();
        OPTIONS.forEach(o -> options.addOption(o));
        this.options = options;
    }

    public Configuration parse() {

        CommandLineParser parser = new DefaultParser();

        CommandLine cmd;

        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.err.println("Error parsing arguments: " + e.getMessage());
            return null;
        }

        if (cmd.hasOption("h")) {
            return null;
        }

        Configuration.Builder builder = new Configuration.Builder();

        // Either generate or read input directory
        if (cmd.hasOption("g")) {
            builder.generate();

            if (cmd.hasOption("N")) {
                Integer N;
                try {
                    N = Integer.parseInt(cmd.getOptionValue("N"));
                } catch (NumberFormatException e) {
                    System.err.println("Invalid number of particles: " + cmd.getOptionValue("N"));
                    return null;
                }

                builder.N(N);
            } else {
                System.err.println("Number of particles is required");
                return null;
            }


            if (cmd.hasOption("L")) {
                Long L;
                try {
                    L = Long.parseLong(cmd.getOptionValue("L"));
                } catch (NumberFormatException e) {
                    System.err.println("Invalid length: " + cmd.getOptionValue("L"));
                    return null;
                }

                builder.L(L);
                
            } else {
                System.err.println("Length is required");
                return null;
            }

            if (cmd.hasOption("r")) {
                String r = cmd.getOptionValue("r");

                if (r.contains(":")) {
                    String[] split = r.split(":");
                    if (split.length != 2) {
                        System.err.println("Invalid radius range: " + r);
                        return null;
                    }

                    Double minR;
                    Double maxR;

                    try {
                        minR = Double.parseDouble(split[0]);
                        maxR = Double.parseDouble(split[1]);
                    } catch (NumberFormatException e) {
                        System.err.println("Invalid radius range: " + r);
                        return null;
                    }

                    builder.randomRadius(minR, maxR);
                    
                } else {
                    Double radius;
                    try {
                        radius = Double.parseDouble(r);
                    } catch (NumberFormatException e) {
                        System.err.println("Invalid radius: " + r);
                        return null;
                    }

                    builder.constantRadius(radius);
                }
            } else {
                System.err.println("Radius is required");
                return null;
            }
        } else {
            if (cmd.hasOption("in")) {
                builder.readInputDirectory(cmd.getOptionValue("in"));
            } else {
                System.err.println("Input directory is required");
                return null;
            }
        }


        // Cell Index Method parameters
        
        if (cmd.hasOption("M")) {
            Long M;
            try {
                M = Long.parseLong(cmd.getOptionValue("M"));
            } catch (NumberFormatException e) {
                System.err.println("Invalid matrix size: " + cmd.getOptionValue("M"));
                return null;
            }

            builder.M(M);
        } else {
            System.err.println("Matrix size is required");
            return null;
        }

        if (cmd.hasOption("rc")) {
            Double rc;
            try {
                rc = Double.parseDouble(cmd.getOptionValue("rc"));
            } catch (NumberFormatException e) {
                System.err.println("Invalid cutoff radius: " + cmd.getOptionValue("rc"));
                return null;
            }

            builder.rc(rc);
        } else {
            System.err.println("Cutoff radius is required");
            return null;
        }


        if (cmd.hasOption("out")) {
            builder.outputDirectory(cmd.getOptionValue("out"));
        } else {
            System.err.println("Output directory is required");
            return null;
        }


        return builder.build();
    }

    public void printHelp() {

        HelpFormatter formatter = new HelpFormatter();
        formatter.setOptionComparator(Comparator.comparingInt(OPTIONS::indexOf));

        formatter.setLeftPadding(4);
        formatter.setWidth(120);

        String commandLineSyntax =
                "mvn exec:java -Dexec.mainClass=ar.edu.itba.ss.g2.App -Dexec.args=\"[options]\"";

        formatter.printHelp(commandLineSyntax, options);
    }
}
