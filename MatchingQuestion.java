// Created by: Ma

public class MatchingQuestion extends Question{
    String[] leftHandSide;
    String[] righthandSide;

    public MatchingQuestion(
        int questionIndex, int answerPoint, 
        String answer, String question, 
        String[] leftHandSide, String[] rightHandSide) {
            
        super(questionIndex, answerPoint, answer, question);
        this.leftHandSide = leftHandSide;
        this.righthandSide = rightHandSide;
    }

    @Override
    public boolean checkAnswer(String answer) {
        return super.checkAnswer(answer);    }
}
