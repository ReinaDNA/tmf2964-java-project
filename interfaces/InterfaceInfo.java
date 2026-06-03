// Interface to display various info types
// Created by: Tvya
package interfaces;
public interface InterfaceInfo {
    // Method to prepare all the text and image paths
    void setupContent();
    
    // Method to update the screen based on the current page number
    void updateDisplay(int pageIndex);
}
