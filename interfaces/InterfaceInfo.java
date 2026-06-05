package interfaces;
// Interface to display various info types
// Created by: Thiveya Shree a/p Baskaran (106564)
// Tested by: Arif Amirul Aiman Bin Marzuki (83282)
public interface InterfaceInfo {
    // Method to prepare all the text and image paths
    void setupContent();
    
    // Method to update the screen based on the current page number
    void updateDisplay(int pageIndex);
}
