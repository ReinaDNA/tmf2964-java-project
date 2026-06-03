// Question Type: Multiple Choice Question
// Inherits from Question to make different types of questions
package questions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;

public class MCQ extends Question {
    String[] optionsDesc = {"A) ", "B) ", "C) ", "D) "};
    char[] options = {'a', 'b', 'c', 'd'};
    String correctAnswer;
    String userChoice;
    

    public MCQ(int index, String question, int pointValue, String[] optionsDesc, String correctAnswer){
        super(index, question, pointValue);
        this.correctAnswer = correctAnswer; 
        for(int i=0; i< optionsDesc.length; i++){
            this.optionsDesc[i] += optionsDesc[i];
        }
    }

    @Override
    public JComponent[] createAnswerComponents(){
        // Demonstrates polymorphism, as different question types have different answer components.
        JComponent[] buttonArray = new JComponent[4];
        JButton optionA = new JButton(optionsDesc[0]);
        JButton optionB = new JButton(optionsDesc[1]);
        JButton optionC = new JButton(optionsDesc[2]);
        JButton optionD = new JButton(optionsDesc[3]);
        
        // Sets the input to be sent when user clicks on the button
        optionA.setActionCommand("A");        
        optionB.setActionCommand("B");        
        optionC.setActionCommand("C");        
        optionD.setActionCommand("D");
        
        // Action Listener to receive the user input
        ActionListener userResponse = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                userChoice = e.getActionCommand();
            }
        };

        // Attach the action listener on the buttons.
        optionA.addActionListener(userResponse);
        optionB.addActionListener(userResponse);
        optionC.addActionListener(userResponse);
        optionD.addActionListener(userResponse);

        // Store the buttons in the array and returns it to the function.
        buttonArray[0] = optionA;
        buttonArray[1] = optionB;
        buttonArray[2] = optionC;
        buttonArray[3] = optionD;
        
        return buttonArray;
    }
    
    @Override
    public void validateResponse(){
        // Check if user is inputting correctly or no
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
