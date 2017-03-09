package cam.ac.uk.ds780.personal.shiptracker;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by serem on 05-Jan-17.
 * Application entry point. Contains the top level window of the application.
 */
public class ShipTracker extends JFrame
{
    private JPanel optionPanel;
    private JPanel mainPanel;
    private List<Body> bodies;
    private GraphicsPanel graphicsPanel;

    private int fps = 25;
    private int displayTimestep = 1000/fps;
    private int dt = 1;

    private ShipTracker() {
        super("ShipTracker");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(1024,768);

        bodies = new ArrayList<>();

        add(makeOptionPanel(), BorderLayout.WEST);
        add(makeMainPanel(), BorderLayout.CENTER);

        graphicsPanel.display(bodies);
    }

    //region GUI
    private JPanel makeOptionPanel() {
        optionPanel = new JPanel();
        optionPanel.setBackground(Color.darkGray);

        JButton addButton = new JButton("Add");
        addButton.addActionListener(e -> makeDialog());
        optionPanel.add(addButton);

        return optionPanel;
    }

    private JPanel makeMainPanel() {
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.black);

        graphicsPanel = new GraphicsPanel();
        mainPanel.add(graphicsPanel, BorderLayout.CENTER);

        System.out.println(graphicsPanel.getWidth());
        System.out.println(graphicsPanel.getHeight());

        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setBackground(Color.darkGray);
        GridBagConstraints constraints = new GridBagConstraints();

        JButton playButton = new JButton("Play");
        playButton.setBackground(Color.green);

        constraints.gridx=0; constraints.gridy=0;
        constraints.ipadx = 40; constraints.ipady = 20;
        buttonPanel.add(playButton, constraints);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        return mainPanel;
    }

    private void makeDialog() {
        JOptionPane.showMessageDialog(this,
                "This is the test dialog",
                "Dialog here",
                JOptionPane.INFORMATION_MESSAGE);
    }
    //endregion

    //region functionality


    public static void main(String[] args) {
        ShipTracker st = new ShipTracker();
        st.setVisible(true);
    }
}
