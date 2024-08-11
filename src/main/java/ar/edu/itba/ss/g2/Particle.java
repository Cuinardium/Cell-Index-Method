package ar.edu.itba.ss.g2;

public class Particle {

    private final Double x;
    private final Double y;

    private final Double radius;

    public Particle(Double x, Double y, Double radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public Double getX() {
        return x;
    }

    public Double getY() {
        return y;
    }

    public Double getRadius() {
        return radius;
    }

    @Override
    public String toString() {
        return "{" + "x=" + x + ", y=" + y + ", r=" + radius + '}';
    }
}
