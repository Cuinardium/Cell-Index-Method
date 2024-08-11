package ar.edu.itba.ss.g2.state;

import ar.edu.itba.ss.g2.Particle;

import java.util.List;
import java.util.Map;

public class Output {

    private final Map<Particle, List<Particle>> particleNeighbours;

    public Output(Map<Particle, List<Particle>> particleNeighbours) {
        this.particleNeighbours = particleNeighbours;
    }

    public Map<Particle, List<Particle>> getParticleNeighbours() {
        return particleNeighbours;
    }
}
