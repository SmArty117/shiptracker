package cam.ac.uk.ds780.personal.shiptracker;

import java.awt.*;

/**
 * Created by serem on 06-Jan-17.
 * This interface indicates that an object can be drawn and contains the draw method.
 */
public interface DrawableObject {
    void draw(Graphics g, double scale);
    void undraw(Graphics d, double scale, Color bckg);
}
