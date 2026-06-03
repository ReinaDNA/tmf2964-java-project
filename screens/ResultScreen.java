package screens;
import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import core.Navigator;

public class ResultScreen extends JPanel{
    @SuppressWarnings("unused")
    private Navigator navigator;
    private JLabel statement;
    private JLabel score;
    private JButton returnHome;
    public ResultScreen(Navigator navigator){
        this.navigator = navigator;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.WHITE);
        statement = new JLabel("Some statement here.");
        score = new JLabel("Your score is : 6/7");
        returnHome = new JButton("Back to Home.");

        add(statement);
        add(score);
        add(returnHome);
    }    
}
