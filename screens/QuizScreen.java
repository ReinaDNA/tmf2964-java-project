package screens;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;   

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.BorderFactory; // ADDED: For screen padding
import javax.swing.Box;           // ADDED: For spaces between buttons

import core.GameEngine;
import core.Navigator;
import interfaces.InterfaceQuestion;
import core.Quiz;

public class QuizScreen extends JPanel{
    // Suppress "unused" warnings from terminal
    private GameEngine gameEngine;
    @SuppressWarnings("unused")
    private Navigator navigator;
    private JButton nextButton;
    private JLabel question;
    private JLabel feedbackLabel;
    private JLabel timer;
    private JLabel healthLabel;
    private boolean isClickedOnce = false;
    private Quiz quiz;
    // Page index constant
    private int currentIndex = 0;

    // Constructor that holds the GUI
    public QuizScreen(Navigator navigator, Quiz quiz, GameEngine gameEngine){
        // Pass in the navigator used from Main.java
        this.navigator = navigator;
        this.quiz = quiz;
        this.gameEngine = gameEngine;

        // Sets a Health bar for the game (Each user has 5 lives)
        // healthBar = new JProgressBar(0,5);
        // healthBar.setValue(gameEngine.getCurrentHealth()); // Sets it to 5 
        // healthBar.setString(gameEngine.getHealthString());
        // healthBar.setStringPainted(true);
        // healthBar.setAlignmentX(Component.LEFT_ALIGNMENT);
        healthLabel = new JLabel(gameEngine.getHealthString());
        healthLabel.setFont(new Font("SansSerif", Font.BOLD, 24)); // Bigger hearts
        healthLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(healthLabel);

        // Set the layout of the GUI
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        // Give the screen nice padding so it's not touching the edges
        setBorder(BorderFactory.createEmptyBorder(50, 40, 50, 40)); 
        
        InterfaceQuestion currentQuestion = quiz.getQuestion(currentIndex);
        setBackground(Color.WHITE);
        
        // Creates a label for the timer
        timer = new JLabel("Time: 60s");
        timer.setFont(new Font("SansSerif", Font.BOLD, 22)); // Make it stand out

        question = new JLabel(currentQuestion.getQuestion());
        // Center the question text
        question.setAlignmentX(Component.LEFT_ALIGNMENT); 
        question.setMaximumSize(new Dimension(Integer.MAX_VALUE, question.getPreferredSize().height));
        
        add(question);
        add(Box.createVerticalStrut(20)); // Space below question

        JComponent[] answerComponents = currentQuestion.createAnswerComponents();
        for(int i = 0; i<answerComponents.length; i++){
            // CHANGED: Center the A/B/C/D buttons
            answerComponents[i].setAlignmentX(Component.LEFT_ALIGNMENT); 
            add(answerComponents[i]);
            add(Box.createVerticalStrut(10)); // Space between options
        }
        
        feedbackLabel = new JLabel("Incorrect Answer! The correct answer is " + currentQuestion.getCorrectAnswer() + ".");
        feedbackLabel.setVisible(false);
        // ADDED: Center the feedback text
        feedbackLabel.setAlignmentX(Component.LEFT_ALIGNMENT); 
        
        nextButton = new JButton("Next Question");
        // ADDED: Center the next button
        nextButton.setAlignmentX(Component.LEFT_ALIGNMENT); 
        
        add(Box.createVerticalStrut(15)); // Space before next button
        add(nextButton);
        add(Box.createVerticalStrut(15)); // Space before feedback
        add(feedbackLabel);
        add(Box.createVerticalStrut(15));
        add(timer);
        
        currentQuestion.setNextButton(nextButton);
        nextButton.setEnabled(false);

        
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                if(isClickedOnce){
                    isClickedOnce = false;
                    feedbackLabel.setVisible(false);
                    nextButton.setText("Next Question");
                    if(currentIndex == quiz.getLastIndex()){
                        endOfQuiz(navigator, quiz);
                    }else{
                        generateNextQuestion(quiz, currentQuestion, gameEngine);
                    }
                }else{
                    gameEngine.stopTimer();
                    boolean userAnswer = quiz.verifyQuestionAnswer(quiz.getQuestion(currentIndex));
                    if(userAnswer){
                        if(currentIndex == quiz.getLastIndex()){
                            endOfQuiz(navigator, quiz);
                        }else{
                            generateNextQuestion(quiz, currentQuestion, gameEngine);
                        }
                    }else{
                        isClickedOnce = true;
                        // Deduct HP for incorrect answer and update display
                        gameEngine.stopTimer();
                        gameEngine.takeDamage(1);
                        healthLabel.setText(gameEngine.getHealthString());

                        // Check if user lost the game
                        if (!gameEngine.isAlive()) {

                            endOfQuiz(navigator, quiz);
                            return; // Exit early so they can't click Continue
                        }

                        // Change the text of the next button and display feedback
                        nextButton.setText("Continue");
                        feedbackLabel.setVisible(true);
                    }
                }
            }
        });

        // Action Listener to update label every second
        gameEngine.setTickAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                timer.setText("Time: " + gameEngine.getRemainingTime() + "s");
            }
        });

        // Sets what happens when time runs out
        gameEngine.setTimeUpAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                // Treat it like a wrong answer, flip the isClicked switch
                isClickedOnce = true;
                nextButton.setText("Continue");
                nextButton.setEnabled(true);
                feedbackLabel.setText("Time's up! The correct answer is " + currentQuestion.getCorrectAnswer());
                feedbackLabel.setVisible(true);
                gameEngine.takeDamage(1);
                healthLabel.setText(gameEngine.getHealthString()); // Update health bar

                // Check if they died
                if(!gameEngine.isAlive()){
                    // Set Game Over for the game
                    endOfQuiz(navigator, quiz);
                }
            }
        });
    }

    // Fetches the next question in line and serves it.
    public void generateNextQuestion(Quiz quiz, InterfaceQuestion currentQuestion, GameEngine gameEngine){
        removeAll();
        currentIndex++;
        currentQuestion.clearUserResponse();
        InterfaceQuestion nextQuestion = quiz.getQuestion(currentIndex);
        question.setText(nextQuestion.getQuestion());
        feedbackLabel.setText("Incorrect Answer! The correct answer is " + nextQuestion.getCorrectAnswer() + ".");
        feedbackLabel.setVisible(false);
        add(healthLabel);
        add(Box.createVerticalStrut(20));
        add(question);
        add(Box.createVerticalStrut(20)); // Space below question

        JComponent[] newComponents = nextQuestion.createAnswerComponents();
        for(int i=0; i<newComponents.length; i++){
            // CHANGED: Center new buttons
            newComponents[i].setAlignmentX(Component.LEFT_ALIGNMENT); 
            add(newComponents[i]);
            add(Box.createVerticalStrut(10)); // Space between options
        }
        
        add(Box.createVerticalStrut(15));
        add(nextButton);
        add(Box.createVerticalStrut(15));
        add(feedbackLabel);
        
        nextButton.setEnabled(false);
        nextQuestion.setNextButton(nextButton);
        add(timer);
        gameEngine.startTimer();
        revalidate();
        repaint();
    }

    // Special function for the last question in the quiz.
    public void endOfQuiz(Navigator navigator, Quiz quiz){
        removeAll();

        // If the player ran out of health
        if(!gameEngine.isAlive()){
            // Big game over Label
            JLabel gameOverLabel = new JLabel("GAME OVER ");
            gameOverLabel.setFont(new Font("SansSerif", Font.BOLD, 32));
            gameOverLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            add(Box.createVerticalStrut(150));
            add(gameOverLabel);
            
            
            JLabel messageLabel = new JLabel("You ran out of health!");
            messageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            add(Box.createVerticalStrut(20));
            add(messageLabel);

            JButton backToHome = new JButton("Back to Home");
            backToHome.setAlignmentX(Component.CENTER_ALIGNMENT);
            add(Box.createVerticalStrut(20));
            add(backToHome);
            backToHome.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e){
                    navigator.showScreen("Home Screen");
                    gameEngine.resetAll();
                }
            });

        }else{
            JButton finalize = new JButton("Finish quiz");
            
            // ADDED: Center the finish button
            finalize.setAlignmentX(Component.CENTER_ALIGNMENT); 
            add(Box.createVerticalStrut(200)); // Push it down to the middle of the screen
            add(finalize);
            
       
            finalize.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e){
                    ResultScreen resultScreen = new ResultScreen(navigator, quiz);
                    navigator.addScreen("Result Screen", resultScreen);
                    navigator.showScreen("Result Screen");
                    resetQuizScreen();
                    gameEngine.resetAll();
                }
            });
        }
        revalidate();
        repaint();

        
    }

    public void resetQuizScreen(){
        this.currentIndex = 0;
        this.isClickedOnce = false;
        removeAll();
        InterfaceQuestion currentQuestion = quiz.getQuestion(currentIndex);
        question.setText(currentQuestion.getQuestion());
        healthLabel.setText(gameEngine.getHealthString()); // Update health bar
        add(healthLabel);
        add(question);
        add(Box.createVerticalStrut(20)); // ADDED: Space below question

        JComponent[] answerComponents = currentQuestion.createAnswerComponents();
        for(int i = 0; i<answerComponents.length; i++){
            // CHANGED: Center reset buttons
            answerComponents[i].setAlignmentX(Component.LEFT_ALIGNMENT); 
            add(answerComponents[i]);
            add(Box.createVerticalStrut(10)); // ADDED: Space between options
        }
        
        add(Box.createVerticalStrut(15));
        add(nextButton);
        add(Box.createVerticalStrut(15));
        add(feedbackLabel);
        add(timer);

        gameEngine.startTimer();
        feedbackLabel.setText("Incorrect Answer! The correct answer is " + currentQuestion.getCorrectAnswer() + ".");
        feedbackLabel.setVisible(false);
        nextButton.setEnabled(false);

        revalidate();
        repaint();
    }
}