package cam.ac.uk.ds780.personal.shiptracker;

import java.awt.*;

/**
 * Created by serem on 06-Jan-17.
 * This is the class that represents a planet. It is a body that has a radius and a colour.
 *
 * When defining a Planet, here is what is important to consider:
 * 1. its position
 * 2. its velocity
 * 3. its color
 * 4. its radius
 * 5. whether it is drawn to scale on the graphics (boolean sizeToScale)
 * 6. whether we want the trail plotted or not
 * 7. the radius of its image
 */
public class Planet extends Body {
    private double radius;
    private Color color;
    private int imgRadius;
    private boolean trail;
    private boolean sizeToScale;

    //region getters and setters
    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getImgRadius() {
        return imgRadius;
    }

    public void setImgRadius(int imgRadius) {
        this.imgRadius = imgRadius;
    }

    public boolean isTrail() {
        return trail;
    }

    public void setTrail(boolean trail) {
        this.trail = trail;
    }

    public boolean isSizeToScale() {
        return sizeToScale;
    }

    public void setSizeToScale(boolean sizeToScale) {
        this.sizeToScale = sizeToScale;
    }
    //endregion

    public Planet() {
        super();
        color = Color.blue;
        this.setRadius(0d);
        this.setColor(Color.blue);
        this.setSizeToScale(true);
        this.setTrail(false);
        this.setImgRadius(5);
    }

    /**
     * Better to use this constructor
     * @param mass sets the mass
     * @param r sets the radius (actual radius)
     * @param pos sets the position
     * @param v sets the velocity
     * @param c sets the color
     * @param sizeToScale shows whether to draw to scale or not
     * @param imgRadius the radius of the image in case sizeToScale = false
     * @param trail shows whether to save and draw the trail
     */
    public Planet(double mass, double r, Vector2D pos, Vector2D v, Color c, boolean sizeToScale, int imgRadius,
                  boolean trail) {
        super(mass, pos, v);
        color = c;
        radius = r;
        this.sizeToScale = sizeToScale;
        this.imgRadius = imgRadius;
        this.trail = trail;
    }

    @Override
    public void draw(Graphics g, double scale) {
        g.setColor(color);
        if(!isSizeToScale())
            g.fillOval((int)Math.round(x()*scale),
                    (int)Math.round(y()*scale),
                    imgRadius, imgRadius);
        else
            g.fillOval((int)Math.round(x()*scale),
                    (int)Math.round(y()*scale),
                    (int)Math.round(radius*scale),
                    (int)Math.round(radius*scale));

        if(isTrail())
            for(Vector2D pos : getTrail()) {
                g.drawLine((int)Math.round(pos.x()*scale),
                        (int)Math.round(pos.y()*scale),
                        (int)Math.round(pos.x()*scale),
                        (int)Math.round(pos.y()*scale));
            }
    }

    /**
     * Function that draws over the last iteration of the Planet in the color of the background, essentially deleting
     * it.
     * @param d the graphics used
     * @param scale the scale to which the Planet is drawn
     * @param bckg the color of the background
     */
    @Override
    public void undraw(Graphics d, double scale, Color bckg) {
        d.setColor(bckg);
        double px = getPrevPosition().x();
        double py = getPrevPosition().y();
        if(isSizeToScale())
            d.fillOval((int)Math.round(px*scale),
                    (int)Math.round(py*scale),
                    imgRadius, imgRadius);
        else
            d.fillOval((int)Math.round(px*scale),
                    (int)Math.round(py*scale),
                    (int)Math.round(radius*scale),
                    (int)Math.round(radius*scale));
    }
}
