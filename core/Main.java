package core;
import javax.swing.JPanel;


import screens.InfoScreen;
import screens.QuizScreen;
import screens.ResultScreen;

public class Main {
    public static void main(String[] args) {
        Quiz quiz = new Quiz();
        quiz.loadQuestionFromFile();

        // 1. Start navigator (the main window)
        Navigator appNavigator = new Navigator();
        
        // 2. Initialize Tvya's & Maliska's classes
        JPanel infoPanel = new InfoScreen();
        QuizScreen quizScreen = new QuizScreen(appNavigator, quiz);
        ResultScreen resultScreen = new ResultScreen(appNavigator);

        // 3. Register Tvya's & Maliska's panel into Navigator.java
        appNavigator.addScreen("INFO_PAGE", infoPanel);
        appNavigator.addScreen("Quiz", quizScreen);
        appNavigator.addScreen("Result Screen", resultScreen);
        // 4. Tell the app which screen to show when it first boots up (Test mode for tyva's part)
        appNavigator.showScreen("Quiz"); // "INFO_PAGE" is SUBJECT TO CHANGE...
    }
}
