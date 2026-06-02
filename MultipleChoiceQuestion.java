// Created by: Ma

public class MultipleChoiceQuestion extends Question{
    String[] questionOption;

    public MultipleChoiceQuestion(
        int questionIndex, int answerPoint, 
        String answer, String question, String[] questionOption) {
            
        super(questionIndex, answerPoint, answer, question);
        this.questionOption = questionOption; // We are storing the array into this field
    }

    @Override
    public boolean checkAnswer(String answer) {
        return super.checkAnswer(answer);
    }
}
