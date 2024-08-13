package ar.edu.itba.ss.g2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ParticleGenerator {

    private final Long areaLength;
    private final Integer particleAmount;

    // If set, radius is constant, else random
    private final Double particleRadius;

    // If set, upper and lower bound of radius distribution
    private final Double minRadius;
    private final Double maxRadius;

    private final Random random;

    public ParticleGenerator(Long areaLength, Integer particleAmount, Double particleRadius) {

        if (particleRadius < 0) {
            throw new IllegalArgumentException("Particle radius must be greater than 0");
        }

        if (areaLength <= 0) {
            throw new IllegalArgumentException("Area length must be greater than 0");
        }

        if (particleAmount <= 0) {
            throw new IllegalArgumentException("Particle amount must be greater than 0");
        }

        this.areaLength = areaLength;
        this.particleAmount = particleAmount;
        this.particleRadius = particleRadius;
        this.minRadius = null;
        this.maxRadius = null;
        this.random = new Random();
    }

    public ParticleGenerator(
            Long areaLength, Integer particleAmount, Double minRadius, Double maxRadius) {

        if (minRadius <= 0 || maxRadius <= 0) {
            throw new IllegalArgumentException("Radius must be greater than 0");
        }

        if (minRadius > maxRadius) {
            throw new IllegalArgumentException("Min radius must be less than max radius");
        }

        if (areaLength <= 0) {
            throw new IllegalArgumentException("Area length must be greater than 0");
        }

        if (particleAmount <= 0) {
            throw new IllegalArgumentException("Particle amount must be greater than 0");
        }

        this.areaLength = areaLength;
        this.particleAmount = particleAmount;
        this.particleRadius = null;
        this.minRadius = minRadius;
        this.maxRadius = maxRadius;
        this.random = new Random();
    }

    public List<Particle> generate() {
        List<Particle> particles = new ArrayList<>(particleAmount);

        boolean randomRadius = particleRadius == null;

        for (long i = 0; i < particleAmount; i++) {
            Double x = random.nextDouble() * areaLength;
            Double y = random.nextDouble() * areaLength;

            Double radius =
                    randomRadius
                            ? minRadius + (maxRadius - minRadius) * random.nextDouble()
                            : particleRadius;

            particles.add(new Particle(i, x, y, radius));
        }

        return particles;
    }
}
