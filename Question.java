// Main class that implements question
// Created by: Ma

public abstract class Question implements InterfaceQuestion {
    private String question, answer;
    private int answerPoint, questionIndex; 

    public Question(
        int questionIndex, int answerPoint, 
        String answer, String question){
            
        this.questionIndex = questionIndex;
        this.answerPoint = answerPoint;
        this.answer = answer;
        this.question = question;
    }

    // Setters
    public void setQuestion(String question){
        this.question = question;
    }

    public void setAnswer(String answer){
        this.answer = answer;
    }

    // Getters
    public String getQuestion(){
        return question;
    }

    public String getAnswer(){
        return answer;
    }

    public int getIndex(){
        return questionIndex;
    }

    public int getPoint(){
        return answerPoint;
    }

    @Override
    public boolean checkAnswer(String answer){
         if(answer == null){
            return false;
        }
        return this.answer.equalsIgnoreCase(answer);
    }
}
