package ar.edu.itba.ss.g2.state;

import ar.edu.itba.ss.g2.Particle;

import java.util.Map;
import java.util.Set;

public class Output {

    private final Map<Particle, Set<Particle>> particleNeighbours;
    private final long millis;

    public Output(Map<Particle, Set<Particle>> particleNeighbours, long millis) {
        this.millis = millis;
        this.particleNeighbours = particleNeighbours;
    }

    public Map<Particle, Set<Particle>> getParticleNeighbours() {
        return particleNeighbours;
    }

    public long getMillis() {
        return millis;
    }
}
