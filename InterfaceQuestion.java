// Interface for question 
// This allows different types of question with the same base.

import javax.swing.JComponent;

public interface InterfaceQuestion {
    // Base requirements for a single question.
    public void askQuestion();
    public void setQuestion(String input);
    public JComponent[] createAnswerComponents();
    public void setAnswer();
    public void validateResponse();
    public String getUserResponse();
    public boolean checkAnswer();
    public int getIndex();
    public int getPointValue();
    public String getQuestion();
    public void clearUserResponse();
}
