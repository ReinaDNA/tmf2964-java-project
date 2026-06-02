// Created by: Ma

public class ShortAnswerQuestion extends Question{
    public ShortAnswerQuestion(
        int questionIndex, int answerPoint, 
        String answer, String question) {
            
        super(questionIndex, answerPoint, answer, question);
    }

    @Override
    public boolean checkAnswer(String answer) {
        return this.answer.contains(answer);
    }
}