package interfaces;
import javax.swing.JButton;
// Interface for question 
// This allows different types of question with the same base.
// Created by: Mohamad Ikhmal Iskandar bin Mohd Ibrahim  (105016)
// Tested by: Seng Zhi Jie (106256)

import javax.swing.JComponent;

public interface InterfaceQuestion {
    // Base requirements for a single question.
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
    public void setNextButton(JButton btn);
    public String getCorrectAnswer();
}
