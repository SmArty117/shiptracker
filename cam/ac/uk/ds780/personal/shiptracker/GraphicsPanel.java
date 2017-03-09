package cam.ac.uk.ds780.personal.shiptracker;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Created by serem on 06-Jan-17.
 * This is the Panel onto which the bodies will be drawn for visualisation.
 * It is given the list of all the bodies and it decides how to draw them.
 */
public class GraphicsPanel extends JPanel {
    private List<Body> bodies;
    private double scalingFactor;

    GraphicsPanel() {
        super();
        this.setBackground(Color.black);
    }

    @Override
    protected void paintComponent(java.awt.Graphics g) {
        //cover the background
        g.setColor(getBackground());
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        //draw all the bodies
        for(Body b : bodies) {
            b.draw(g, scalingFactor);
        }
    }

    private double reviewScaling() {
        double maxX = 0;
        double maxY = 0;

        return 0d;
    }

    protected void display(List<Body> bodies1) {
        bodies = bodies1;
        repaint();
    }
}
