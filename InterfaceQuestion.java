// Interface for question 
// This allows different types of question with the same base.

public interface InterfaceQuestion {
    // Base requirements for a single question.
    public void setQuestion();
    public void setAnswer();
    public boolean checkAnswer();
}
