// Created by: Ma

public class MultipleChoiceQuestion extends Question{
    private String[] questionOption;

    public MultipleChoiceQuestion(
        int questionIndex, int answerPoint, 
        String answer, String question, String[] questionOption){
            
        super(questionIndex, answerPoint, answer, question);
        this.questionOption = questionOption; // We are storing the array into this field
    }

    // To access the questionOption
    public String[] getQuestionOption(){
        return questionOption;
    }

    @Override
    public boolean checkAnswer(String answer){
        return super.checkAnswer(answer);
    }
}
