package cam.ac.uk.ds780.personal.shiptracker;

/**
 * Created by serem on 06-Jan-17.
 * This class represents 2D vectors, such as may be needed for velocity, acceleration, position etc.
 * It supports basic vector operations, such as addition and dot product.
 * Note that this class is immutable. The methods do NOT modify the current object.
 */
public class Vector2D implements Cloneable {
    private final double x;
    private final double y;

    public Vector2D(double X, double Y) {
        this.x = X;
        this.y = Y;
    }

    Vector2D() {
        this.x = 0;
        this.y = 0;
    }

    double x(){
        return x;
    }

    double y(){
        return y;
    }

    double theta() {
        return Math.atan2(x, y);
    }

    Vector2D negative() {
        return new Vector2D(-this.x(), -this.y());
    }

    double modulus() {
        return Math.sqrt(x*x + y*y);
    }

    Vector2D unit() {
        return new Vector2D(x/modulus(), y/modulus());
    }

    Vector2D div(double c) {
        return new Vector2D(x/c, y/c);
    }

    Vector2D mult(double c) {
        return new Vector2D(x*c, y*c);
    }

    static Vector2D add(Vector2D u, Vector2D v) {
        return new Vector2D(u.x()+v.x(), u.y()+v.y());
    }

    Vector2D add(Vector2D u) {
        return new Vector2D(x + u.x, y + u.y);
    }

    Vector2D subtract(Vector2D u) {
        return this.add(u.negative());
    }

    static double dotprod(Vector2D u, Vector2D v) {
        return u.x()*v.x() + u.y()*v.y();
    }

    /**
     * @param u is the vector from which v is subtracted
     * @param v is the vector which is subtracted from u
     * @return the difference u-v
     */
    static Vector2D subtract(Vector2D u, Vector2D v) {
        return add(u, v.negative());
    }

    byte quadrant() {
        if(x > 0)
            if(y > 0)
                return 1;
            else return 4;
        else if(y > 0)
                return 2;
            else return 3;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vector2D vector2D = (Vector2D) o;

        return Double.compare(vector2D.x, x) == 0 && Double.compare(vector2D.y, y) == 0;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(x);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(y);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public Vector2D clone() throws CloneNotSupportedException {
        return (Vector2D) super.clone();
    }
}
