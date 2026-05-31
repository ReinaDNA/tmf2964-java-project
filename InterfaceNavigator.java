/*
 - The blueprint to guide through the SDG Math App.
 - Created by: Arif Amirul Aiman Bin Marzuki (83282)
 */ 

import javax.swing.JPanel;

public interface InterfaceNavigator {
    // Method to add a new screen to the application
    void addScreen(String screenName, JPanel panel);
    
    // Method to change the currently visible screen
    void showScreen(String screenName);
}