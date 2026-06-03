package core;
// Quiz class to generate questions
// Created by:

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import interfaces.InterfaceQuestion;
import questions.MultipleChoiceQuestion;

public class Quiz {
    ArrayList<InterfaceQuestion> questionBank = new ArrayList<>();
    int currentPoints = 0; // Initialize the points of the user
    
    int index = 1; // THIS INDEX REFERS TO THE QUESTION NUMBER

    public void loadQuestionFromFile(){
        try(Scanner qLoader = new Scanner(new File("./data/questions.txt"));){
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
                String[] options = {" ", " ", " ", " "};
                for(int i=0; i<options.length; i++){
                    options[i] += questionParts[i+2];
                }
                String correctAnswer = questionParts[6];
                int pointValue = Integer.parseInt(questionParts[7]);
                InterfaceQuestion newQuestion = new MultipleChoiceQuestion(index, question, pointValue, options, correctAnswer);
                return newQuestion;
            }else if(questionType.equalsIgnoreCase("TF") && questionParts.length == 4){
                return null;
                // If it is True/False question
            }else{
                System.out.println("Invalid question type or missing data, unable to create question. Please try again.");
                return null;
            }
        }

        public void verifyQuestionAnswer(InterfaceQuestion question){
            if(question.checkAnswer()){
                this.currentPoints += question.getPointValue();
                System.out.println(currentPoints);
            } else{
                System.out.println("Incorrect answer, please try again");
            }         
        }

        public int getTotalPoints(){
            int total = 0;
            for(int i=0; i<questionBank.size(); i++){
                total += questionBank.get(i).getPointValue();
            }
            return total;
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
