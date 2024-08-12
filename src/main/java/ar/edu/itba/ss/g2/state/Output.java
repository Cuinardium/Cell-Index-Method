package ar.edu.itba.ss.g2.state;

import ar.edu.itba.ss.g2.Particle;

import java.util.List;
import java.util.Map;

public class Output {

    private final Map<Particle, List<Particle>> particleNeighbours;
    private final long millis;

    public Output(Map<Particle, List<Particle>> particleNeighbours, long millis) {
        this.millis = millis;
        this.particleNeighbours = particleNeighbours;
    }

    public Map<Particle, List<Particle>> getParticleNeighbours() {
        return particleNeighbours;
    }

    public long getMillis() {
        return millis;
    }
}
