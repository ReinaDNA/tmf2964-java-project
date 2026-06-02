// Quiz class to generate questions
// Created by:

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Quiz {
    ArrayList<InterfaceQuestion> questionBank = new ArrayList<>();
    int index = 1;

    public void loadQuestionFromFile(){
        try(Scanner qLoader = new Scanner(new File("test.txt"));){
            // Loops through the question bank
            do{
                index = 1; // Resets the index just in case 
                // Fetches the next question
                String data = qLoader.nextLine();
                if(data.trim().isEmpty()){
                    continue;
                }

                // Parsing the data and stores it using a String array
                String[] parts = data.split("::");
                String questionType = parts[0];
                // If data is invalid, skips to the next line
                if(questionType.isEmpty()){
                    System.err.println("No data received, proceeding with the next line.");
                    continue;
                }else{
                    // If data is valid, invoke the create question function to create new question
                    // Adds the question into the question bank
                    questionBank.add(createQuestions(questionType, parts, index));
                    index++;
                }
            }while(qLoader.hasNextLine());
        }catch(FileNotFoundException e){
            System.out.println("An error has occured.");
            e.printStackTrace();
        }
    }

    public InterfaceQuestion createQuestions(String questionType, String[] questionParts, int index){
        // Checking if there is anything in the questionType variable

            if(questionType.equalsIgnoreCase("MCQ") && questionParts.length == 8){
                // If it is MCQ type question
                // Parse out the required data
                String question = questionParts[1];
                String[] options = {"A) ", "B) ", "C) ", "D) "};
                for(int i=0; i<options.length; i++){
                    options[i] += questionParts[i+2];
                }
                String correctAnswer = questionParts[6];
                int pointValue = Integer.parseInt(questionParts[7]);
                InterfaceQuestion newQuestion = new MCQ(index, question, pointValue, options, correctAnswer);
                return newQuestion;
            }else if(questionType.equalsIgnoreCase("TF") && questionParts.length == 4){
                return null;
                // If it is True/False question
            }else{
                System.out.println("Invalid question type or missing data, unable to create question. Please try again.");
                return null;
            }
        }

        public ArrayList<InterfaceQuestion> getQuestionBank(){
            return this.questionBank;
        }

        public int getIndex(){
            return this.index;
        }

        public int getLastIndex(){
            return this.questionBank.size() - 1;
        }

        public InterfaceQuestion getQuestion(int index){
            return questionBank.get(index);
        }

        
}
