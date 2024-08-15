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

    public Double distanceTo(Particle other) {
        double dx = this.x - other.x;
        double dy = this.y - other.y;
        double centerToCenterDistance = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));

        double borderToBorderDistance = centerToCenterDistance - this.radius - other.radius;

        return Math.max(0, borderToBorderDistance);
    }

    public Double toroidalDistanceTo(Particle other, Long L) {
        double dx = Math.abs(this.x - other.x);
        double dy = Math.abs(this.y - other.y);

        // Toroide, me quedo con la distancia más corta
        dx = Math.min(dx, L - dx);
        dy = Math.min(dy, L - dy);

        double centerToCenterDistance = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));

        double borderToBorderDistance = centerToCenterDistance - this.radius - other.radius;

        return Math.max(0, borderToBorderDistance);
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
