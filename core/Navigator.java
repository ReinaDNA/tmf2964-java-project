package core;
/*
 - Brings the navigation blueprint to life. This class acts as the main window of our SDG Math App
 - Created by: Arif Amirul Aiman Bin Marzuki (83282)
 - Tested by: Seng Zhi Jie (106256)
 */
import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import interfaces.InterfaceNavigator;

public class Navigator implements InterfaceNavigator {
    
    // Basic class variables
    private JFrame frame;
    private JPanel mainContainer;
    private CardLayout cardLayout;

    // Constructor to set up the main window
    public Navigator() {
        frame = new JFrame("SDG Discrete Math App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Set size for mobile screen layout as required (400x600)
        frame.setSize(400, 600);
        
        // Center the window on the computer screen
        frame.setLocationRelativeTo(null);

        // Set up the CardLayout
        cardLayout = new CardLayout();
        mainContainer = new JPanel(cardLayout);
        
        // Add the container to the frame
        frame.add(mainContainer);
        
        // Make the window visible
        frame.setVisible(true);
    }

    // Implement the interface method to add panels
    @Override
    public void addScreen(String screenName, JPanel panel) {
        mainContainer.add(panel, screenName);
    }

    // Implement the interface method to switch panels
    @Override
    public void showScreen(String screenName) {
        cardLayout.show(mainContainer, screenName);

        mainContainer.revalidate(); // Recalculates the layout with the new panel
        mainContainer.repaint(); //Forces the window to redraw itself visually
    }
}