// Created by: Ma

public class MatchingQuestion extends Question{
    private String[] leftHandSide;
    private String[] rightHandSide;

    public MatchingQuestion(
        int questionIndex, int answerPoint, 
        String answer, String question, 
        String[] leftHandSide, String[] rightHandSide){
            
        super(questionIndex, answerPoint, answer, question);
        this.leftHandSide = leftHandSide;
        this.rightHandSide = rightHandSide;
    }

    public String[] getLeftHandSide(){
        return leftHandSide;
    }

    public String[] getRightHandSide(){
        return rightHandSide;
    }

    @Override
    public boolean checkAnswer(String answer){
        return super.checkAnswer(answer);    
    }
}
