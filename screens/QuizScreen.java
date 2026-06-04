package screens;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.BorderFactory; // ADDED: For screen padding
import javax.swing.Box;           // ADDED: For spaces between buttons

import core.Navigator;
import interfaces.InterfaceQuestion;
import core.Quiz;

public class QuizScreen extends JPanel{
    // Suppress "unused" warnings from terminal
    @SuppressWarnings("unused")
    private Navigator navigator;
    private JButton nextButton;
    private JLabel question;
    private JLabel feedbackLabel;
    private boolean isClickedOnce = false;
    private Quiz quiz;
    // Page index constant
    private int currentIndex = 0;

    // Constructor that holds the GUI
    public QuizScreen(Navigator navigator, Quiz quiz){
        // Pass in the navigator used from Main.java
        this.navigator = navigator;
        this.quiz = quiz;

        // Set the layout of the GUI
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        // Give the screen nice padding so it's not touching the edges
        setBorder(BorderFactory.createEmptyBorder(50, 40, 50, 40)); 
        
        InterfaceQuestion currentQuestion = quiz.getQuestion(currentIndex);
        setBackground(Color.WHITE);
        
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
                        generateNextQuestion(quiz, currentQuestion);
                    }
                }else{
                    boolean userAnswer = quiz.verifyQuestionAnswer(quiz.getQuestion(currentIndex));
                    if(userAnswer){
                        if(currentIndex == quiz.getLastIndex()){
                            endOfQuiz(navigator, quiz);
                        }else{
                            generateNextQuestion(quiz, currentQuestion);
                        }
                    }else{
                        isClickedOnce = true;
                        nextButton.setText("Continue");
                        feedbackLabel.setVisible(true);
                    }
                }
            }
        });
    }

    // Fetches the next question in line and serves it.
    public void generateNextQuestion(Quiz quiz, InterfaceQuestion currentQuestion){
        removeAll();
        currentIndex++;
        currentQuestion.clearUserResponse();
        InterfaceQuestion nextQuestion = quiz.getQuestion(currentIndex);
        question.setText(nextQuestion.getQuestion());
        feedbackLabel.setText("Incorrect Answer! The correct answer is " + nextQuestion.getCorrectAnswer() + ".");
        feedbackLabel.setVisible(false);
        
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
        revalidate();
        repaint();
    }

    // Special function for the last question in the quiz.
    public void endOfQuiz(Navigator navigator, Quiz quiz){
        removeAll();
        JButton finalize = new JButton("Finish quiz");
        
        // ADDED: Center the finish button
        finalize.setAlignmentX(Component.CENTER_ALIGNMENT); 
        add(Box.createVerticalStrut(200)); // Push it down to the middle of the screen
        add(finalize);
        
        revalidate();
        repaint();

        finalize.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                ResultScreen resultScreen = new ResultScreen(navigator, quiz);
                navigator.addScreen("Result Screen", resultScreen);
                navigator.showScreen("Result Screen");
                resetQuizScreen();
            }
        });
    }

    public void resetQuizScreen(){
        this.currentIndex = 0;
        removeAll();
        InterfaceQuestion currentQuestion = quiz.getQuestion(currentIndex);
        question.setText(currentQuestion.getQuestion());
        
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
        
        feedbackLabel.setText("Incorrect Answer! The correct answer is " + currentQuestion.getCorrectAnswer() + ".");
        feedbackLabel.setVisible(false);
        nextButton.setEnabled(false);
    }
}