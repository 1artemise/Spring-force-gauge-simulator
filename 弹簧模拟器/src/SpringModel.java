public class SpringModel {
    public double mass = 1.0;
    public double k = 50.0;
    public double damping = 2.0;

    public double position = 0.1;
    public double velocity = 0.0;

    private final double gravity = 9.8;

    public double initPosition = 0.1;
    public double initVelocity = 0.0;

    public void reset() {
        position = initPosition;
        velocity = initVelocity;
    }

    public void update(double dt) {
        double springForce = -k * position;
        double dampingForce = -damping * velocity;
        double netForce = springForce + dampingForce + mass * gravity;
        double acceleration = netForce / mass;

        velocity += acceleration * dt;
        position += velocity * dt;
    }
}
