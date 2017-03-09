package cam.ac.uk.ds780.personal.shiptracker;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by serem on 06-Jan-17.
 * This provides the general behaviour of a body.
 */
public abstract class Body implements Cloneable, DrawableObject {

    private double mass;
    private Vector2D velocity;
    private Vector2D acceleration;
    private Vector2D position;
    private List<Vector2D> prevPositions;

    Body() {
        initall();
    }

    Body(double mass, Vector2D position) {
        initall();
        this.setMass(mass);
        this.setPosition(position);
    }

    Body(double mass, Vector2D position, Vector2D v) {
        initall();
        this.setMass(mass);
        this.setPosition(position);
        this.setVelocity(v);
    }

    /**
     * Function that initialises all fields to their default values.
     */
    private void initall() {
        prevPositions = new LinkedList<>();
        this.setMass(0d);
        this.setPosition(new Vector2D());
        this.setAcceleration(new Vector2D());
        this.setVelocity(new Vector2D());
    }

    //region getters and setters

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public Vector2D getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2D velocity) {
        this.velocity = velocity;
    }

    public Vector2D getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(Vector2D acceleration) {
        this.acceleration = acceleration;
    }

    public Vector2D getPosition() {
        return position;
    }

    public void setPosition(Vector2D position) {
        this.position = position;
    }

    public double x() {
        return this.position.x();
    }

    public double y() {
        return this.position.y();
    }

    public Vector2D getPrevPosition() {
        return prevPositions.get(0);
    }

    public void updatePrevPositions() {
        prevPositions.add(0, position);
    }

    public List<Vector2D> getTrail() {
        return new LinkedList<>(prevPositions);
    }
    //endregion

    /* This doesn't work because i can't know from inside the Body class what the net force will be in the new
    position. This is why this will have to be done from the parent class.
    void update(int dt, Vector2D newAcceleration) {
        // r(t+dt) = r(t) + v(t) * dt + a(t) * dt^2 / 2
        position = Vector2D.add(position, Vector2D.add(velocity.mult(dt), acceleration.mult(dt*dt/2)));
        // v(t+dt) = v(t) + (a(t) + a(t+dt)) * dt / 2
        velocity = Vector2D.add(velocity, Vector2D.add(acceleration, newAcceleration).div(2).mult(dt));
        acceleration = newAcceleration;
    }*/

    public void updateState(Vector2D r, Vector2D v, Vector2D a) {
        setPosition(r);
        setVelocity(v);
        setAcceleration(a);
    }

    @Override
    public Body clone() throws CloneNotSupportedException {
        return (Body) super.clone();
    }
}
