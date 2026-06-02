// Interface for question 
// This allows different types of question with the same base.

public interface InterfaceQuestion {
    // Base requirements for a single question.

    // Setters
    public void setQuestion(String question);
    public void setAnswer(String answer);
    // Getters
    public String getQuestion();
    public String getAnswer();
    public int getIndex();
    public int getPoint();

    public boolean checkAnswer(String answer);
}
