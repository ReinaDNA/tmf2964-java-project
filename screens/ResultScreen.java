package screens;
// Screen to display results
import java.awt.Color;
import java.awt.Component; // For centering
import java.awt.Font;      // To make the text look nicer
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory; // For screen padding
import javax.swing.Box;           // For spacing between elements
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


import core.Navigator;
import core.Quiz;

public class ResultScreen extends JPanel{
    @SuppressWarnings("unused")
    private Navigator navigator;
    private JLabel username;
    private JLabel statement;
    private JLabel score;
    private JLabel percentageLabel;
    private JButton returnHome;
    
    public ResultScreen(Navigator navigator, Quiz quiz){
        this.navigator = navigator;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        Color blondeColor = new Color(245, 245, 220);
        setBackground(blondeColor);
        
        // 1. Give the screen nice padding so it matches the other pages
        setBorder(BorderFactory.createEmptyBorder(50, 40, 50, 40));

        String usernameString = quiz.getUsername();
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
        
        username = new JLabel(usernameString + ",");
        score = new JLabel("Your score is: " + finalPoints + "/" + totalPoints);
        percentageLabel = new JLabel("Your percentage is: " + finalPercentage + "%");
        returnHome = new JButton("Back to Home");

        // 3. Center all the UI elements
        statement.setAlignmentX(Component.CENTER_ALIGNMENT);
        username.setAlignmentX(Component.CENTER_ALIGNMENT);
        score.setAlignmentX(Component.CENTER_ALIGNMENT);
        percentageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        returnHome.setAlignmentX(Component.CENTER_ALIGNMENT);

        // 4. Add them to the screen with vertical spacing
        add(Box.createVerticalStrut(50)); // Push everything down closer to the middle
        add(statement);
        add(Box.createVerticalStrut(20));
        add(username);
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
                saveScoreToFile(usernameString, finalPoints, finalPercentage);
                quiz.resetQuiz();
            }
        });
    }
    
    // Function to save the data into file
    public void saveScoreToFile(String username, int finalPoints, double finalPercentage){
        try{
            // Create data folder if it doesn't exist
            File folder = new File("data");
            if(!folder.exists()){
                folder.mkdirs();
            }

            // Create the file if it doesn't exist
            File file = new File("data/scores.txt");
            if(!file.exists()){
                file.createNewFile();
            }
            
            // Get current timestamp
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String timestamp = now.format(formatter);

            // Append to file
            FileWriter fw = new FileWriter(file, true); // true = append mode
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(username + "," + finalPoints + "," + finalPercentage + "," + timestamp);
            bw.newLine();
            bw.close();
            fw.close();

        }catch(IOException e){
            System.out.println("Error saving score: " + e.getMessage());
            e.printStackTrace();
        }
    }
}