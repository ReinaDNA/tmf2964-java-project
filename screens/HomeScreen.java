package screens;
// Screen to display home page
// Created by: Seng Zhi Jie (106256)
// Tested by: Arif Amirul Aiman Bin Marzuki (83282)
import java.awt.Color;
import java.awt.Component; // Needed to center items in BoxLayout
import java.awt.Font;      // Needed to make the welcome text bigger
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;          // Needed to add invisible spaces between buttons
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.BorderFactory; // Needed to add padding to the edges of the screen

import core.Navigator;

public class HomeScreen extends JPanel {
    
    @SuppressWarnings("unused")
    private Navigator navigator;
    
    public HomeScreen(Navigator navigator, QuizScreen quizScreen) {
        this.navigator = navigator;

        // Keep your exact layout!
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        Color blondeColor = new Color(245, 245, 220);
        setBackground(blondeColor);
        
        // 1. Add some invisible padding so buttons don't touch the window edges
        setBorder(BorderFactory.createEmptyBorder(50, 40, 50, 40));

        // 2. Make the Welcome message bigger and center it
        JLabel welcomeMessaage = new JLabel("Welcome to Math App!");
        welcomeMessaage.setFont(new Font("SansSerif", Font.BOLD, 24));
        welcomeMessaage.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton infoButton = new JButton("Info Panel");
        JButton quizButton = new JButton("Start a quiz");
        JButton leaderboardButton = new JButton("View Leaderboard");
        JButton exitButton = new JButton("Exit");

        // 3. Center all the buttons in the middle of the screen
        infoButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        quizButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        leaderboardButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // 4. Add items to the screen with Box.createVerticalStrut() to add spaces between them
        add(welcomeMessaage);
        add(Box.createVerticalStrut(40)); // Big space below the title
        
        add(infoButton);
        add(Box.createVerticalStrut(15)); // Small space between buttons
        
        add(quizButton);
        add(Box.createVerticalStrut(15));
        
        add(leaderboardButton);
        add(Box.createVerticalStrut(15));
        
        add(exitButton);

        // Event Listeners for every button on Home Page
        infoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                navigator.showScreen("INFO_PAGE");
            }
        });

        quizButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                navigator.showScreen("Username Screen");
                quizScreen.resetQuizScreen(); // Resets the quiz object and screen 
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