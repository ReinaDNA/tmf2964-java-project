// Created by: Ma

public class ShortAnswerQuestion extends Question{
    public ShortAnswerQuestion(
        int questionIndex, int answerPoint, 
        String answer, String question) {
            
        super(questionIndex, answerPoint, answer, question);
    }

    @Override
    public boolean checkAnswer(String answer){
        String correct = getAnswer().replaceAll("\\s+", ""); // White space
        String user = answer.replaceAll("\\s+", ""); // Get rid of the white space

        return correct.equalsIgnoreCase(user);
    }
}
