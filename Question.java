// Main class that implements question
// Created by:

import java.util.Scanner;

public class Question implements InterfaceQuestion {
    String question;
    char response;
    char correctAnswer;
    int index;
    int pointValue;
    boolean isCorrect = false;

    public Question( int index, String question, char correctAnswer){
        // Creates a question object with prompt and answer
        this.index = index;
        this.question = question;
        this.correctAnswer = correctAnswer;
    }
    
    public void askQuestion(){
        Scanner s = new Scanner(System.in);
        System.out.println(question);
        response = s.next().charAt(0);
        isCorrect = validateResponse(response);
        s.close();
    }

    public void setQuestion(String input){  
        question = input;
    }

    public void setAnswer(char choice){
        correctAnswer = choice;
    }

    public boolean validateResponse(char choice){
        return false;
    }

  
   
}

// Further extend this class to implement different question types
// Example, MCQ, fill in the blanks, radiobox, etc.