package desmedt.bac.swing.quiz;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuestionView extends JPanel implements ActionListener {
    
    Question question;
    
    JPanel questionPanel = new JPanel();
    JPanel answersPanel = new JPanel();
    JPanel buttonPanel = new JPanel();
    
    JRadioButton[] rbAnswers = new JRadioButton[4];
    ButtonGroup buttonGroup = new ButtonGroup();
    
    JButton bNext = new JButton("Next");
    JButton bFinish = new JButton("Finish");
    
    private JTextArea textAreaProperties(JTextArea label) {
        label.setEditable(false);
        label.setCursor(null);
        label.setOpaque(false);
        label.setFocusable(false);
        label.setLineWrap(true);
        label.setWrapStyleWord(true);
        return label;
    }
    public QuestionView(Question question) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.question = question;
        
        //Question
        JTextArea txtQuestion = new JTextArea(question.getQuestion());
        System.out.println(getMaximumSize());
        txtQuestion.setSize(400,60);
//        txtQuestion.setPreferredSize(new Dimension(400,60));
        txtQuestion.setFont(new Font("Verdana", Font.PLAIN, 18));
        txtQuestion.setEditable(false);
//        txtQuestion.setCursor(null);
        txtQuestion.setOpaque(false);
//        txtQuestion.setFocusable(false);
        txtQuestion.setLineWrap(true);
        txtQuestion.setWrapStyleWord(true);
        questionPanel.add(txtQuestion);
        questionPanel.setBorder(new EmptyBorder(10,10,10,10));
        add(questionPanel);
        
        //Answers
        String[] answers = question.getAnswers();
        GridLayout gridLayout = new GridLayout(2, 2);
//        gridLayout.setHgap(40);
        
//        answersPanel.setPreferredSize(new Dimension(400,120));
        answersPanel.setLayout(gridLayout);
        for (int i = 0; i < rbAnswers.length; i++) {
            rbAnswers[i] = new JRadioButton(answers[i]);
//            rbAnswers[i].getFont()
            rbAnswers[i].addActionListener(this);
            buttonGroup.add(rbAnswers[i]);
            answersPanel.add(rbAnswers[i]);
        }
        answersPanel.setBorder(new EmptyBorder(50,50,50,50));
        add(answersPanel);
        
        //Buttons
        //bottom
        bNext.addActionListener(this);
        bFinish.addActionListener(this);
        buttonPanel.add(bNext);
        buttonPanel.add(bFinish);
        add(buttonPanel);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
    
    }
}
