package screens;
// Screen to prompt user input a username
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import core.Navigator;
import core.Quiz;

public class UsernameScreen extends JPanel{
    private JLabel askInput;
    private JTextField usernameField; 
    private JButton nextButton;
    @SuppressWarnings("unused")
    private Navigator navigator;
    @SuppressWarnings("unused")
    private Quiz quiz;

    public UsernameScreen(Navigator navigator, Quiz quiz){
        // Pass in the navigator used from Main.java
        this.navigator = navigator;
        this.quiz = quiz;

        // Set the layout of the GUI
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        Color blondeColor = new Color(245, 245, 220);
        setBackground(blondeColor);
        
        // Give the screen nice padding so it's not touching the edges
        setBorder(BorderFactory.createEmptyBorder(50, 40, 50, 40)); 
        
        // Create the GUI elements
        askInput = new JLabel("Please insert your username.");
        usernameField = new JTextField(10);
        nextButton = new JButton("Next");

        // Adjust GUI element positions
        askInput.setAlignmentX(Component.CENTER_ALIGNMENT);
        usernameField.setPreferredSize(new Dimension(100, 30));
        usernameField.setAlignmentX(Component.CENTER_ALIGNMENT);
        usernameField.setMaximumSize(usernameField.getPreferredSize());
        nextButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add the GUI elements into the screen
        add(Box.createVerticalStrut(100)); // Push everything down to center
        add(askInput);
        add(Box.createVerticalStrut(20)); // Add space below the question statment.
        add(usernameField);
        add(Box.createVerticalStrut(30)); // Add space between the input box and the next button.
        add(nextButton);

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                String username = usernameField.getText();
                usernameField.setText(""); // Reset the field for next attempt
                quiz.setUsername(username);
                navigator.showScreen("Quiz");
            }
        });
    }
}
