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
    // Page index constant
    private int currentIndex = 0;

    // Constructor that holds the GUI
    public QuizScreen(Navigator navigator, Quiz quiz){
        // Pass in the navigator used from Main.java
        this.navigator = navigator;

        // Set the layout of the GUI
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        InterfaceQuestion currentQuestion = quiz.getQuestion(currentIndex);
        setBackground(Color.WHITE);
        
        question = new JLabel(currentQuestion.getQuestion());
        add(question);
        question.setAlignmentX(Component.LEFT_ALIGNMENT);
        question.setMaximumSize(new Dimension(Integer.MAX_VALUE, question.getPreferredSize().height));

        JComponent[] answerComponents = currentQuestion.createAnswerComponents();
        for(int i = 0; i<answerComponents.length; i++){
            add(answerComponents[i]);
            answerComponents[i].setAlignmentX(Component.LEFT_ALIGNMENT);
        }
        feedbackLabel = new JLabel("Incorrect Answer! The correct answer is " + currentQuestion.getCorrectAnswer() + ".");
        feedbackLabel.setVisible(false);
        nextButton = new JButton("Next Question");
        add(nextButton);
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
                        endOfQuiz(navigator);
                    }else{
                        generateNextQuestion(quiz, currentQuestion);
                    }
                }else{
                    boolean userAnswer = quiz.verifyQuestionAnswer(quiz.getQuestion(currentIndex));
                    if(userAnswer){
                        if(currentIndex == quiz.getLastIndex()){
                            endOfQuiz(navigator);
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
        JComponent[] newComponents = nextQuestion.createAnswerComponents();
        for(int i=0; i<newComponents.length; i++){
            add(newComponents[i]);
        }
        add(nextButton);
        add(feedbackLabel);
        nextButton.setEnabled(false);
        nextQuestion.setNextButton(nextButton);
        revalidate();
        repaint();
    }

    // Special function for the last question in the quiz.
    public void endOfQuiz(Navigator navigator){
        removeAll();
        JButton finalize = new JButton("Finish quiz");
        add(finalize);
        revalidate();
        repaint();

        finalize.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                navigator.showScreen("Result Screen");
            }
        });
    }
}
