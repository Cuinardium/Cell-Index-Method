package ar.edu.itba.ss.g2;

public class Particle {

    private final Long id;

    private final Double x;
    private final Double y;

    private final Double radius;

    public Particle(Long id, Double x, Double y, Double radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.id = id;
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

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "{" + "id=" + id + ", x=" + x + ", y=" + y + ", r=" + radius + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Particle) {
            Particle p = (Particle) obj;
            return p.id.equals(this.id);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
