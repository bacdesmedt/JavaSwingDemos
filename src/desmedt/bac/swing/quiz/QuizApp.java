package desmedt.bac.swing.quiz;

import javax.swing.*;
import java.util.ArrayList;

public class QuizApp  extends JFrame {
    
    private final ArrayList<Question> questions = new ArrayList<>();
    public QuizApp() {
        super("Quiz Game");
        setResizable(true);
        setSize(500,400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        
        for (String question : QUESTIONS_ARRAY) {
            questions.add(new Question(question));
        }
        
        add(new QuestionView(questions.get(6)));
    
        setVisible(true);
    }
    
    public static void main(String[] args) {
        new QuizApp();
    }
    
    private static final String[] QUESTIONS_ARRAY = {
            "0_What is the most common element in the universe?_Hydrogen_Helium_Argon_Beryllium",
            "1_How is the change in frequency of a wave (or other periodic event) for an observer moving relative to its source called?_Doppler Effect_Heisenberg's Uncertainty Principle_Spectrometric variable_Kepler's retroduction",
            "2_What is the speed of sound under normal circumstances?_343 m/s_343 km/h_343 km/s_343 m/h",
            "3_What is the average distance from the earth to the moon?_384 450 km_534 320 km_254 890 km_654 780 km",
            "4_Einstein received the Nobel prize for?_The photo-electric effect_General Relativity_Special Relativity_Einstein never received the Nobel prize",
            "5_How many 'named' satellites does Saturn have?_53_3_35_200+",
            "6_How long does it take the sun to complete a single orbit around the Milky Way's center (most likely a black hole)?_250 million years_350 million years_450 million years_550 million years",
            "7_How many planets does our solar system contain?_8_7_9_10",
            "8_Where can you find the Kuiper belt?_Beyond Neptune_Between Mars and Jupiter_Between Uranus and Neptune_Between Venus and Mercury",
            "9_Which planet in our solar system has relatively the biggest moon?_Earth_Jupiter_Saturn_Pluto",
            "10_How many times would you be able to fit the earth in Jupiter?_1321_3264_312_6654",
            "11_What is a pulsar?_A highly magnetized, rotating neutron star_A newborn star_A star about to go supernova_A highly magnetized, rotating planet",
            "12_How long is our sun estimated to live?_10 000 000 000 years_10 000 000 years_10 000 years_10 000 000 000 000 years",
            "13_Which of the following statements is false?_Saturn is the only ringed planet_Saturn is farthest planet visible with the naked eye_Saturn is the second biggest planet in our system_A Saturn year lasts 10 759 days",
            "14_What type of galaxy is the Milky Way?_Spiral galaxy_Elliptical galaxy_Ring galaxy_Dwarf galaxy",
            "15_How often does a total solar eclipse occur on our planet?_Every 18 months_Every 300 years_Every 1080 days_Every month",
            "16_How hot is the surface of the sun?_5 505 °C_5 505 505 °C_555 °C_55 505 °C",
            "17_What remains of the sun after its nuclear reactions stops?_A white dwarf_A neutron star_A pulsar_A black hole",
            "18_Which planet is called the sister planet?_Venus_Mars_Mercury_Pluto",
            "19_How many extrasolar planets have been discover so far?_more than 4000_less than 10_exactly 642_none"
    };
}
