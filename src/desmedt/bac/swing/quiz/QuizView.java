package desmedt.bac.swing.quiz;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class QuizView extends JPanel implements ActionListener {
    
    public interface AnswerListener{
        void onAnswer(int id, boolean correct);
    }
    
    Question question;
    
    JRadioButton[] rbAnswers = new JRadioButton[4];
    ButtonGroup buttonGroup = new ButtonGroup();
    
    GridBagLayout gridBagLayout = new GridBagLayout();
    
    AnswerListener listener;
    
    public QuizView(Question question, AnswerListener listener) {
        setLayout(gridBagLayout);
        this.question = question;
        this.listener = listener;
        gridBagLayout.rowHeights = new int[]{ 2, 1, 1, 1 };
        gridBagLayout.rowWeights = new double[]{ 0.4, 0.2, 0.2, 0.2 };
        
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.weightx = 1;
        constraints.anchor = GridBagConstraints.CENTER;
        
        //Question
        JTextArea txtQuestion = new JTextArea(String.format("%d. %s", question.getId() + 1, question.getQuestion()));
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridy = 0;
        
        txtQuestion.setFont(new Font("Verdana", Font.PLAIN, 18));
        txtQuestion.setEditable(false);
        txtQuestion.setOpaque(false);
        txtQuestion.setLineWrap(true);
        txtQuestion.setWrapStyleWord(true);
        txtQuestion.setMaximumSize(new Dimension(500,200));
        txtQuestion.setMinimumSize(new Dimension(300,60));
        txtQuestion.setBorder(new EmptyBorder(10,10,10,10));
        
        add(txtQuestion, constraints);
        
        //Answers
        String[] answers = question.getAnswers();
        constraints.fill = GridBagConstraints.NONE;
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.ipadx = 50;
        
        for (int i = 0; i < rbAnswers.length; i++) {
            rbAnswers[i] = new JRadioButton(String.format("<html>%s</html>",answers[i]));
    
            rbAnswers[i].setMaximumSize(new Dimension(500,100));
            rbAnswers[i].setMinimumSize(new Dimension(300,60));
            rbAnswers[i].addActionListener(this);
            buttonGroup.add(rbAnswers[i]);
            constraints.gridy = i +1;
            add(rbAnswers[i], constraints );
        }
        setBorder(new EmptyBorder(5,5,20,5));
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
    
        if (listener!= null) {
            String guess = e.getActionCommand().replaceAll("\\<.*?\\>", "");
            
            listener.onAnswer(question.getId(), question.isCorrect(guess));
        }
        
    
    }
}
