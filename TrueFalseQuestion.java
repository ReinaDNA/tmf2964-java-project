// Created by: Ma

public class TrueFalseQuestion extends Question {
    public TrueFalseQuestion(
        int questionIndex, int answerPoint, 
        String answer, String question) {
            
        super(questionIndex, answerPoint, answer, question);
    }

    @Override
    public boolean checkAnswer(String answer) {
        return super.checkAnswer(answer);
    }
}
