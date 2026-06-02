// Main class that implements question
// Created by:

import java.util.Scanner;
import javax.swing.JComponent;

public abstract class Question implements InterfaceQuestion {
    String question;
    char response;
    int index;
    int pointValue;
    boolean isCorrect = false;

    public Question(int index, String question, int pointValue){
        // Creates a question object with prompt and answer
        this.index = index;
        this.question = question;
        this.pointValue = pointValue;
    }
    
    public void askQuestion(){
        try(Scanner s = new Scanner(System.in)){
            System.out.println(question);
            response = s.next().charAt(0);
            validateResponse();
        }
       
    }

    // Abstract functions
    // Must be overriden by child classes
    
    // Abstract method to create GUI components for the question
    public abstract JComponent[] createAnswerComponents();
    
    // Abstract method to verify if the user's response is within expected range
    // (Used for Fill in the blanks only)
    public abstract void validateResponse();
    
    // Abstract method to check user's answer is correct or not
    public abstract boolean checkAnswer();
    
    public abstract String getUserResponse();

    public abstract void clearUserResponse();
    
    // Setters
    public void setAnswer(){

    }
    
    public void setQuestion(String input){  
        question = input;
    }

    // Getters
    public int getIndex(){
        return this.index;
    }

    public int getPointValue(){
        return this.pointValue;
    }

    public String getQuestion(){
        return this.question;
    }
}

// Further extend this class to implement different question types
// Example, MCQ, fill in the blanks, radiobox, etc.