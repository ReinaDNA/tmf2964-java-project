// To manage the quiz itself
// Created by: Ma

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Quiz {
    // Question object
    private ArrayList<Question> questionList = new ArrayList<Question>();
    private int userScore = 0, currentQuestionIndex = 0;
    
    public Quiz(){
        loadQuestion();
    }

    // File Handling
    // TYPE|INDEX|POINT|ANSWER|QUESTION|EXTRA
    // Note: | in the file is java delimiter
    private void loadQuestion(){
        // ERROR HANDLING
        try {
            Scanner readFile = new Scanner(new File("question.txt"));
            // LOOP
            while(readFile.hasNextLine()){
                String line = readFile.nextLine();

                String[] data = line.split("\\|");

                // File line data
                String type = data[0], answer = data[3], question = data[4];

                // To parsing the file content properly to the variable
                int questionIndex = Integer.parseInt(data[1]);
                int answerPoint = Integer.parseInt(data[2]);

                // CONDITIONAL STATEMENT; Checking the type of question in the file

                // Multiple Choice Question
                if(type.equalsIgnoreCase("MCQ")){
                    String[] options = data[5].split("~");
                    questionList.add(new MultipleChoiceQuestion(
                            questionIndex,
                            answerPoint,
                            answer,
                            question,
                            options
                        )
                    );
                }

                // True False
                else if(type.equalsIgnoreCase("TF")){
                    questionList.add(new TrueFalseQuestion(
                            questionIndex,
                            answerPoint,
                            answer,
                            question
                        )
                    );
                }

                // Fill In The Blank
                else if(type.equalsIgnoreCase("FILL")){
                    questionList.add(new FillBlankQuestion(
                            questionIndex,
                            answerPoint,
                            answer,
                            question
                        )
                    );
                }

                // Short Answer
                else if(type.equalsIgnoreCase("SHORT")){
                    questionList.add(new ShortAnswerQuestion(
                            questionIndex,
                            answerPoint,
                            answer,
                            question
                        )
                    );
                }
                
                // Matching
                else if(type.equalsIgnoreCase("MATCH")){

                    String[] leftHandSide = data[5].split("~");
                    String[] rightHandSide = data[6].split("~");

                    questionList.add(new MatchingQuestion(
                            questionIndex,
                            answerPoint,
                            answer,
                            question,
                            leftHandSide,
                            rightHandSide
                        )
                    );
                }

                else{
                    System.out.println(
                        "Unknown question type: " + type
                    );
                }
            }
            readFile.close();
        }
        catch(FileNotFoundException e) {
            System.out.println("Missing question.txt");
        }
    }

    public Question getCurrentQuestion(){
        if(isFinished()) {
            return null;
        }
        return questionList.get(currentQuestionIndex);
    }

    // Check the answer
    public boolean submitAnswer(String userAnswer){
        Question currentQuestion = getCurrentQuestion();
        if(currentQuestion == null) {
            return false;
        }

        // Checking for the right answer
        boolean correct = currentQuestion.checkAnswer(userAnswer);

        if(correct) {
            userScore += currentQuestion.getPoint();
        }
        return correct;
    }

    // Index tracking
     public void nextQuestion(){
        if(!isFinished()) {
            currentQuestionIndex++;
        }
    }

    // To see when it is finished based on the list size
    public boolean isFinished(){
        return currentQuestionIndex >= questionList.size();
    }
    
    public int getScore(){
        return userScore;
    }

    public int getQuestionCount(){
        return questionList.size();
    }

    // Track the question index
    public int getCurrentQuestionNumber() {
        return currentQuestionIndex + 1;
    }
}