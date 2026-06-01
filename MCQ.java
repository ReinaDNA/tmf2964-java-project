// Question Type: Multiple Choice Question
// Inherits from Question to make different types of questions

import javax.swing.JButton;
import javax.swing.JComponent;

public class MCQ extends Question {
    String[] optionsDesc = {"A) ", "B) ", "C) ", "D) "};
    char[] options = {'a', 'b', 'c', 'd'};
    char correctAnswer;
    

    public MCQ(int index, String question, int pointValue, String[] optionsDesc, char correctAnswer){
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
        buttonArray[0] = new JButton(optionsDesc[0]);
        buttonArray[1] = new JButton(optionsDesc[1]);
        buttonArray[2] = new JButton(optionsDesc[2]);
        buttonArray[3] = new JButton(optionsDesc[3]);

        return buttonArray;
    }
    
    @Override
    public void validateResponse(){
        // Check if user is inputting correctly or no
    }
}
