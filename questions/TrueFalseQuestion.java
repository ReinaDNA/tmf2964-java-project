package questions;
// Question Type: True or False Question
// Inherits from Question to make different types of questions
// Created by: Mohamad Ikhmal Iskandar bin Mohd Ibrahim  (105016)
// Tested by: Seng Zhi Jie (106256)

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;

public class TrueFalseQuestion extends Question {

    String correctAnswer;
    String userChoice;
    

    public TrueFalseQuestion(int index, String question, int pointValue, String correctAnswer){
        super(index, question, pointValue);
        this.correctAnswer = correctAnswer; 
       
    }

    @Override
    public JComponent[] createAnswerComponents(){
        // Demonstrates polymorphism, as different question types have different answer components.
        JComponent[] buttonArray = new JComponent[2];
        JButton optionTrue = new JButton("True");
        JButton optionFalse = new JButton("False");

        
        // Sets the input to be sent when user clicks on the button
        optionTrue.setActionCommand("True");        
        optionFalse.setActionCommand("False");        

        
        // Action Listener to receive the user input
        ActionListener userResponse = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                userChoice = e.getActionCommand();

                if(nextButton != null){
                    nextButton.setEnabled(true);
                }
            }
        };

        // Attach the action listener on the buttons.
        optionTrue.addActionListener(userResponse);
        optionFalse.addActionListener(userResponse);


        // Store the buttons in the array and returns it to the function.
        buttonArray[0] = optionTrue;
        buttonArray[1] = optionFalse;

        
        return buttonArray;
    }
    
    @Override
    public void validateResponse(){
        // Check if user is inputting correctly or no
    }
    
    @Override
    public String getCorrectAnswer(){
        return this.correctAnswer;
    }
    
    @Override
    public boolean checkAnswer(){
        return userChoice.equalsIgnoreCase(correctAnswer);
    }

    public String getUserResponse(){
        return this.userChoice;
    }

    public void clearUserResponse(){
        this.userChoice = "";
    }
}
