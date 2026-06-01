import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class QuizScreen extends JPanel{
    @SuppressWarnings("unused")
    private Navigator navigator;
    int currentIndex = 0;
    public QuizScreen(Navigator navigator, Quiz quiz){
        this.navigator = navigator;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        InterfaceQuestion currentQuestion = quiz.getQuestion(currentIndex);
        setBackground(Color.WHITE);
        
        JLabel question = new JLabel(currentQuestion.getQuestion());
        add(question);
        question.setAlignmentX(Component.LEFT_ALIGNMENT);
        question.setMaximumSize(new Dimension(Integer.MAX_VALUE, question.getPreferredSize().height));

        JComponent[] answerComponents = currentQuestion.createAnswerComponents();
        for(int i = 0; i<answerComponents.length; i++){
            add(answerComponents[i]);
            answerComponents[i].setAlignmentX(Component.LEFT_ALIGNMENT);
        }

        JButton submit = new JButton("Next Question");

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                navigator.showScreen("Question " + currentIndex);
                currentIndex++;
            }
        });
        add(submit);
        SwingUtilities.invokeLater(()->{
            revalidate();
            repaint();
        });
    }
}
