package desmedt.bac.swing.quiz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class QuizApp extends JFrame implements ActionListener, QuizView.AnswerListener {
    
    public static final String NEXT = "Next";
    public static final String FINISH = "Finish";
    public static final String PREVIOUS = "Previous";
    
    JPanel cardsPanel = new JPanel();
    JPanel buttonPanel = new JPanel();
    CardLayout cards = new CardLayout();
    
    JButton bLeft = new JButton(PREVIOUS);
    JButton bRight = new JButton(NEXT);
    int id;
    int score;
    Boolean[] answers = new Boolean[QUESTIONS.size()];
    
    public QuizApp() {
        super("Quiz Game");
        setResizable(true);
        setSize(500, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
    
        id = 0;
        score = 0;
        Arrays.fill(answers, Boolean.FALSE);
        //Cards
        cardsPanel.setLayout(cards);
        QUESTIONS.forEach(question -> cardsPanel.add(new QuizView(question, this), Integer.toString(question.getId())));
        cards.show(cardsPanel, "0");
        add(cardsPanel);
        
        //Buttons
        bLeft.addActionListener(this);
        bRight.addActionListener(this);
        buttonPanel.setLayout(new GridBagLayout());
        buttonPanel.setMaximumSize(new Dimension(500,50));
        buttonPanel.setMinimumSize(new Dimension(500,50));
        buttonPanel.setPreferredSize(new Dimension(500,50));
        buttonPanel.setSize(new Dimension(500,50));
        buttonPanel.add(bLeft);
        buttonPanel.add(bRight);
//        buttonPanel.setBackground(Color.red);
        add(buttonPanel);
        
        bLeft.setVisible(false);
        
        setVisible(true);
    }
    
    public void onQuizEnd(){
        
        JLabel mLabel = new JLabel(
                String.format("\tYou scored %d/20! Play Again?", score),
                SwingConstants.CENTER
        );
        int choice = JOptionPane.showConfirmDialog(
                null,
                mLabel,
                "",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );
    
        if(choice == 0)
            main(null);
        else
            dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }
    
    
    @Override
    public void onAnswer(int id, boolean correct) {
        answers[id] = correct;
        
        score = (int) Arrays.stream(answers).filter(Boolean::booleanValue).count();
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case NEXT:
                if (id < 19) {
                    cards.next(cardsPanel);
                    id++;
                }
                break;
            case PREVIOUS:
                if (id > 0) {
                    cards.previous(cardsPanel);
                    id--;
                }
                break;
            case FINISH:
                onQuizEnd();
                break;
        }
    
    
        bLeft.setVisible(id > 0);
        bRight.setText(id == 19 ? FINISH : NEXT);
    }
    
    
    public static void main(String[] args) {
        new QuizApp();
    }
    
    private static final List<Question> QUESTIONS = List.of(
            new Question("0_What is the most common element in the universe?_Hydrogen_Helium_Argon_Beryllium"),
            new Question("1_How is the change in frequency of a wave (or other periodic event) for an observer moving relative to its source called?_Doppler Effect_Heisenberg's Uncertainty Principle_Spectrometric variable_Kepler's retroduction"),
            new Question("2_What is the speed of sound under normal circumstances?_343 m/s_343 km/h_343 km/s_343 m/h"),
            new Question("3_What is the average distance from the earth to the moon?_384 450 km_534 320 km_254 890 km_654 780 km"),
            new Question("4_Einstein received the Nobel prize for?_The photo-electric effect_General Relativity_Special Relativity_Einstein never received the Nobel prize"),
            new Question("5_How many 'named' satellites does Saturn have?_53_3_35_200+"),
            new Question("6_How long does it take the sun to complete a single orbit around the Milky Way's center (most likely a black hole)?_250 million years_350 million years_450 million years_550 million years"),
            new Question("7_How many planets does our solar system contain?_8_7_9_10"),
            new Question("8_Where can you find the Kuiper belt?_Beyond Neptune_Between Mars and Jupiter_Between Uranus and Neptune_Between Venus and Mercury"),
            new Question("9_Which planet in our solar system has relatively the biggest moon?_Earth_Jupiter_Saturn_Pluto"),
            new Question("10_How many times would you be able to fit the earth in Jupiter?_1321_3264_312_6654"),
            new Question("11_What is a pulsar?_A highly magnetized, rotating neutron star_A newborn star_A star about to go supernova_A highly magnetized, rotating planet"),
            new Question("12_How long is our sun estimated to live?_10 000 000 000 years_10 000 000 years_10 000 years_10 000 000 000 000 years"),
            new Question("13_Which of the following statements is false?_Saturn is the only ringed planet_Saturn is farthest planet visible with the naked eye_Saturn is the second biggest planet in our system_A Saturn year lasts 10 759 days"),
            new Question("14_What type of galaxy is the Milky Way?_Spiral galaxy_Elliptical galaxy_Ring galaxy_Dwarf galaxy"),
            new Question("15_How often does a total solar eclipse occur on our planet?_Every 18 months_Every 300 years_Every 1080 days_Every month"),
            new Question("16_How hot is the surface of the sun?_5 505 °C_5 505 505 °C_555 °C_55 505 °C"),
            new Question("17_What remains of the sun after its nuclear reactions stops?_A white dwarf_A neutron star_A pulsar_A black hole"),
            new Question("18_Which planet is called the sister planet?_Venus_Mars_Mercury_Pluto"),
            new Question("19_How many extrasolar planets have been discover so far?_more than 4000_less than 10_exactly 642_none")
    );
}
