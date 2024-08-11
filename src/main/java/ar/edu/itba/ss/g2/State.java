package ar.edu.itba.ss.g2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class State {

    private final long L;
    private final List<Particle> particles;

    public State(long L, List<Particle> particles) {
        this.L = L;
        this.particles = particles;
    }

    public long getL() {
        return this.L;
    }

    public List<Particle> getParticles() {
        return this.particles;
    }

    public void serialize(String directory) throws IOException {

        // Create directory if it doesn't exist
        File dir = new File(directory);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        int N = particles.size();

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

    public static State deserialize(String directory) throws IOException {

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

                particles.add(new Particle(x, y, r));
            }
        }

        return new State(L, particles);
    }
}
