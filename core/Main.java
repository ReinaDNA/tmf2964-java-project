package core;
import javax.swing.JPanel;
// Main class that runs the program from command prompt
// Created by: Arif Amirul Aiman Bin Marzuki (83282)
// Tested by: Seng Zhi Jie (106256)
import screens.HomeScreen;
import screens.InfoScreen;
import screens.LeaderBoardScreen;
import screens.QuizScreen;
import screens.UsernameScreen;

public class Main {
    public static void main(String[] args) {
        Quiz quiz = new Quiz();
        GameEngine gameEngine = new GameEngine();
        quiz.loadQuestionFromFile();

        // 1. Start navigator (the main window)
        Navigator appNavigator = new Navigator();
        JPanel infoPanel = new InfoScreen(appNavigator);
        QuizScreen quizScreen = new QuizScreen(appNavigator, quiz, gameEngine);
        HomeScreen homeScreen = new HomeScreen(appNavigator,quizScreen);
        LeaderBoardScreen leaderBoardScreen = new LeaderBoardScreen(appNavigator);
        UsernameScreen usernameScreen = new UsernameScreen(appNavigator, quiz);

        appNavigator.addScreen("INFO_PAGE", infoPanel);
        appNavigator.addScreen("Quiz", quizScreen);
        appNavigator.addScreen("Home Screen", homeScreen);
        appNavigator.addScreen("Leaderboards", leaderBoardScreen);
        appNavigator.addScreen("Username Screen", usernameScreen);

        appNavigator.showScreen("Home Screen");
    }
}
