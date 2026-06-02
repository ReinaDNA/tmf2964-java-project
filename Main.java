import javax.swing.JPanel;

public class Main {
    public static void main(String[] args) {
         Quiz quiz = new Quiz();
        quiz.loadQuestionFromFile();

        
        
        // 1. Start navigator (the main window)
        Navigator appNavigator = new Navigator();
        
        // 2. Initialize Tvya's & Maliska's classes
        JPanel infoPanel = new Info();
        QuizScreen quizScreen = new QuizScreen(appNavigator, quiz);
        
        // 3. Register Tvya's & Maliska's panel into Navigator.java
        appNavigator.addScreen("INFO_PAGE", infoPanel);
        appNavigator.addScreen("quiz", quizScreen);
        
        // 4. Tell the app which screen to show when it first boots up (Test mode for tyva's part)
        appNavigator.showScreen("INFO_PAGE"); // "INFO_PAGE" is SUBJECT TO CHANGE...
    }
}
