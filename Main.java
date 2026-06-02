import javax.swing.JPanel;

public class Main {
    public static void main(String[] args) {
        
        // 1. Start navigator (the main window)
        Navigator appNavigator = new Navigator();
        
        // 2. Initialize Tvya's & Maliska's classes
        JPanel infoPanel = new Info();
        // --> Maliska's Quiz panel...
        
        // 3. Register Tvya's & Maliska's panel into Navigator.java
        appNavigator.addScreen("INFO_PAGE", infoPanel);
        // --> Maliska's Quiz panel...
        
        // 4. Tell the app which screen to show when it first boots up (Test mode for tyva's part)
        appNavigator.showScreen("INFO_PAGE"); // "INFO_PAGE" is SUBJECT TO CHANGE...

    }
}