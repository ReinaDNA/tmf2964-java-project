package screens;
import java.awt.Color;
import java.awt.Component; // ADDED: For centering
import java.awt.Font;      // ADDED: To make the text look nicer
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory; // ADDED: For screen padding
import javax.swing.Box;           // ADDED: For spacing between elements
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
        
        // 1. Give the screen nice padding so it matches the other pages
        setBorder(BorderFactory.createEmptyBorder(50, 40, 50, 40));

        double finalPercentage = quiz.calculateFinalScore();
        int finalPoints = quiz.getCurrentPoints();
        int totalPoints = quiz.getTotalPoints();
        
        // 2. Fulfill the specific rubric requirement for motivational messages
        String motivationMessage = "";
        if (finalPercentage >= 80) {
            motivationMessage = "Outstanding!";
        } else if (finalPercentage >= 60) {
            motivationMessage = "That's good!";
        } else if (finalPercentage >= 40) {
            motivationMessage = "Good try!";
        } else if (finalPercentage >= 20) {
            motivationMessage = "You can do better!";
        } else {
            motivationMessage = "Don't give up!";
        }

        statement = new JLabel(motivationMessage);
        statement.setFont(new Font("SansSerif", Font.BOLD, 22)); // Make it stand out
        
        score = new JLabel("Your score is: " + finalPoints + "/" + totalPoints);
        percentageLabel = new JLabel("Your percentage is: " + finalPercentage + "%");
        returnHome = new JButton("Back to Home");

        // 3. Center all the UI elements
        statement.setAlignmentX(Component.CENTER_ALIGNMENT);
        score.setAlignmentX(Component.CENTER_ALIGNMENT);
        percentageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        returnHome.setAlignmentX(Component.CENTER_ALIGNMENT);

        // 4. Add them to the screen with vertical spacing
        add(Box.createVerticalStrut(50)); // Push everything down closer to the middle
        add(statement);
        add(Box.createVerticalStrut(20));
        add(score);
        add(Box.createVerticalStrut(10));
        add(percentageLabel);
        add(Box.createVerticalStrut(40));
        add(returnHome);

        returnHome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                // Important: Ensure "Home Screen" matches exactly what you named it in Main.java
                navigator.showScreen("Home Screen"); 
                quiz.resetQuiz();
            }
        });
    }    
}