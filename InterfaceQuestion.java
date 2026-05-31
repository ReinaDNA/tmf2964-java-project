// Interface for question 
// This allows different types of question with the same base.

public interface InterfaceQuestion {
    // Base requirements for a single question.
    public void askQuestion();
    public void setQuestion(String input);
    public void setAnswer();
    public void validateResponse();
    public boolean checkAnswer(String choice);
    public int getIndex();
    public int getPointValue();
    public String getQuestion();
}
