package screens;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import core.Navigator;
import core.Quiz;

public class ResultScreen extends JPanel{
    @SuppressWarnings("unused")
    private Navigator navigator;
    private JLabel statement;
    private JLabel score;
    private JLabel percentageLabel;
    private JButton returnHome;
    public ResultScreen(Navigator navigator, Quiz quiz){
        this.navigator = navigator;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.WHITE);

        double finalPercentage = quiz.calculateFinalScore();
        int finalPoints = quiz.getCurrentPoints();
        int totalPoints = quiz.getTotalPoints();

        statement = new JLabel("Some statement here.");
        score = new JLabel("Your score is: " + finalPoints + "/" + totalPoints);
        percentageLabel = new JLabel("Your percentage is: " + finalPercentage);
        returnHome = new JButton("Back to Home.");

        add(statement);
        add(score);
        add(percentageLabel);
        add(returnHome);


        returnHome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                navigator.showScreen("Home Screen");
            }
        });
    }    
}
