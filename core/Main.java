package core;
import javax.swing.JPanel;

import screens.HomeScreen;
import screens.InfoScreen;
import screens.LeaderBoardScreen;
import screens.QuizScreen;

public class Main {
    public static void main(String[] args) {
        Quiz quiz = new Quiz();
        quiz.loadQuestionFromFile();

        // 1. Start navigator (the main window)
        Navigator appNavigator = new Navigator();
        JPanel infoPanel = new InfoScreen(appNavigator);
        QuizScreen quizScreen = new QuizScreen(appNavigator, quiz);
        HomeScreen homeScreen = new HomeScreen(appNavigator,quizScreen);
        LeaderBoardScreen leaderBoardScreen = new LeaderBoardScreen(appNavigator);

        appNavigator.addScreen("INFO_PAGE", infoPanel);
        appNavigator.addScreen("Quiz", quizScreen);
        appNavigator.addScreen("Home Screen", homeScreen);
        appNavigator.addScreen("Leaderboards", leaderBoardScreen);

        appNavigator.showScreen("Home Screen");
    }
}
