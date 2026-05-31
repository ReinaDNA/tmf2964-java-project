// Main class that implements question
// Created by:

import java.util.Scanner;

public class Question implements InterfaceQuestion {
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

    public void setAnswer(){

    }
    
    public void setQuestion(String input){  
        question = input;
    }

    public void validateResponse(){
        // Validate response is to check if the user is input correctly or no
    }

    public boolean checkAnswer(String choice){
        return false;
    }
  
   
}

// Further extend this class to implement different question types
// Example, MCQ, fill in the blanks, radiobox, etc.