// Created by: Ma

public class FillBlankQuestion extends Question{
    public FillBlankQuestion(
        int questionIndex, int answerPoint, 
        String answer, String question) {

        super(questionIndex, answerPoint, answer, question);
    }

    @Override
    public boolean checkAnswer(String answer) {
        return this.answer.equalsIgnoreCase(answer);
    }
}