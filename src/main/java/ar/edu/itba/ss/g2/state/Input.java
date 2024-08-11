package ar.edu.itba.ss.g2.state;

import java.util.List;

import ar.edu.itba.ss.g2.Particle;

public class Input {

    private final long L;
    private final List<Particle> particles;

    public Input(long L, List<Particle> particles) {
        this.L = L;
        this.particles = particles;
    }

    public long getL() {
        return this.L;
    }

    public List<Particle> getParticles() {
        return this.particles;
    }
}
