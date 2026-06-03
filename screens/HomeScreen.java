package screens;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import core.Navigator;

public class HomeScreen extends JPanel{
    @SuppressWarnings("unused")
    private Navigator navigator;
    public HomeScreen(Navigator navigator, QuizScreen quizScreen){
        this.navigator = navigator;

        // Set the layout of the GUI
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.WHITE);

        JLabel welcomeMessaage = new JLabel("Welcome to Math App!");
        JButton infoButton = new JButton("Info Panel");
        JButton quizButton = new JButton("Start a quiz");
        JButton leaderboardButton = new JButton("View Leaderboard");
        JButton exitButton = new JButton("Exit");

        add(welcomeMessaage);
        add(infoButton);
        add(quizButton);
        add(leaderboardButton);
        add(exitButton);

        // Event listeners
        infoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                navigator.showScreen("INFO_PAGE");
            }
        });

        quizButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                navigator.showScreen("Quiz");
                quizScreen.resetQuizScreen();
                
            }
        });

        leaderboardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                navigator.showScreen("Leaderboards");
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        });
    }
}
