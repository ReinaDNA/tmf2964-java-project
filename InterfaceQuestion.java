// Interface for question 
// This allows different types of question with the same base.

public interface InterfaceQuestion {
    // Base requirements for a single question.
    public void loadQuestionFromFile();
    public void askQuestion();
    public void setQuestion(String input);
    public void setAnswer(char choice);
    public boolean validateResponse(char choice);
}
