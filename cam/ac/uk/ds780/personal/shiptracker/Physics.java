package cam.ac.uk.ds780.personal.shiptracker;

import java.util.List;

/**
 * Created by serem on 06-Jan-17.
 * This class provides the physical interactions for Body-type objects.
 * Note that all physical quantities are expressed in SI units, as it is a consistent system.
 * Thus the forces are in Newtons, the time in seconds, the masses in kg and so on.
 */
public class Physics {
    public static final double G = 6.674e-11;
    private static double dt;

    public static void setDt(double time) {
        dt = time;
    }

    public static double getDt() {
        return dt;
    }

    /**
     * Function that calculates the gravitational attraction between two bodies;
     * @param A is the body upon which the force acts
     * @param B is the body which exerts the force
     * @return the gravitational attraction force.
     */
    public static Vector2D gForce(Body A, Body B) {
        Vector2D r = Vector2D.subtract(B.getPosition(), A.getPosition());

        //F = G * Ma * Mb / modulus(r)^2 * unit(r)
        return r.unit().mult(G * A.getMass() * B.getMass()).div(r.modulus()*r.modulus());
    }

    /**
     * Function that calculates the total gravitational force acting on one body as a result of the action of other
     * bodies.
     * @param a is the body for which the total force is calculated
     * @param bodies is the list of all the bodies that can act on body a. If bodies includes a, the effect on a on
     *               itself will not be take into account (this would, in fact, cause a division by zero error)
     * @return The total gravitational force acting on body a
     */
    public static Vector2D totalGForce(Body a, List<Body> bodies) {
        Vector2D result = new Vector2D();
        for(Body b : bodies) {
            if(b != a)
                result = result.add(gForce(a, b));
        }
        return result;
    }

    /**
     * Function that moves one planet as a result of the gForce from all other planets.
     * @param p the planet to be moved
     * @param bodies all the bodies that can act on p
     */
    public static void movePlanet(Planet p, List<Body> bodies) {
        Vector2D Fg = totalGForce(p, bodies);
        p.setAcceleration(Fg.div(p.getMass()));
        p.updatePrevPositions();
        p.setPosition(p.getPosition()
                .add(p.getVelocity().mult(getDt()))
                .add(p.getAcceleration().mult(getDt()*getDt()/2)));
    }
}
