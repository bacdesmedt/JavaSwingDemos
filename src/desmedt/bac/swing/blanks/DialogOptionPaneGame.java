package desmedt.bac.swing.blanks;


import javax.swing.*;

public class DialogOptionPaneGame {
    
    public static void main(String[] args) {
        new DialogOptionPaneGame();
    }
    
    public DialogOptionPaneGame() {
        boolean done = false;
        
        while (!done) {
            String[] answers = new String[11];
            JOptionPane.showMessageDialog(null, "Let's make a story", "Fill in the blanks", JOptionPane.INFORMATION_MESSAGE);
            
            //1-color
            String[] colors = { "Orange", "Green", "Blue", "Yellow", "Red", "Purple" };
            int colorIndex = JOptionPane.showOptionDialog(null,
                    "What's your favourite color?", "color",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE, null,
                    colors, 0);
            answers[0] = colors[colorIndex];
            
            //2-liquid
            answers[1] = JOptionPane.showInputDialog(null, "What is your favorite thing to drink? (liquid)", "Liquid",
                    JOptionPane.QUESTION_MESSAGE);
            //3-plural noun
            answers[2] = JOptionPane.showInputDialog("Name something people bring with them to school (plural noun)");
            //4-adjective
            answers[3] = JOptionPane.showInputDialog("What is one aspect you would want an " +
                    "imaginary friend to have? (adjective)");
            //5-present tense verb
            answers[4] = JOptionPane.showInputDialog("What is something you do when you drive " +
                    "a car (present tense verb)");
            //6-plural noun
            answers[5] = JOptionPane.showInputDialog("Name something you own multiple of " +
                    "(plural noun)");
            //7-singular noun
            answers[6] = JOptionPane.showInputDialog("What is something you might find in " +
                    "the U.S.A. (singular noun)");
            //8-adjective
            answers[7] = JOptionPane.showInputDialog("Describe your current leader in one " +
                    "word (adjective)");
            //9-present tense verb
            answers[8] = JOptionPane.showInputDialog("What is something you do while " +
                    "travelling (present tense verb)");
            //10-present tense verb
            answers[9] = JOptionPane.showInputDialog("Name something you do when you go to the " +
                    "gym (present tense verb)");
            //11-singular noun
            answers[10] = JOptionPane.showInputDialog("If you could have one thing in the world " +
                    "what would it be? (singular noun)");
            
            done = true;
            int input = JOptionPane.showConfirmDialog(null, "Are you sure you are ready to the result?");
            
            if (input == 1 || input == 2) {
                done = false;
            } else {
                input = JOptionPane.showConfirmDialog(
                        null,
                        "Really this is your last chance to bail, are you certain?",
                        "",
                        JOptionPane.YES_NO_OPTION
                );
                if (input == 1) {
                    done = false;
                } else {
                    input = JOptionPane.showConfirmDialog(
                            null,
                            "Ok, we'll move on (but you can still press cancel if you're " +
                                    "not ready)",
                            "",
                            JOptionPane.OK_CANCEL_OPTION
                    );
                }
            }
            
            if(done){
                JOptionPane.showMessageDialog(
                        null,
                        "Each spring, the sky turns "+ answers[0]+". Giant drops of "+answers[1]+" fall from the sky.\n"+
                                "All this "+answers[1]+" helps the grass and the "+answers[2]+" to grow, but it can make things\n"+
                                "really "+answers[3]+" too.\n"+
                                
                                "Some places get so much "+answers[1]+", that rivers "+answers[4]+" into the streets. Driving can\n"+
                                "be tricky when this happens, so some people put special "+answers[5]+" on their cars.\n"+
                                
                                "And when the "+answers[1]+" is falling, don't forget your "+answers[6]+". Otherwise, your feet\n"+
                                "might get "+answers[7]+" if you "+answers[8]+" in puddles!\n"+
                                
                                "After all the "+answers[1]+" has fallen, the skies begin to "+answers[9]+". If you are lucky, you\n"+
                                "might see a huge "+answers[10]+" stretched across the sky.\n"
                );
            }else{
                JOptionPane.showMessageDialog(null,"Ok we'll start over then");
            }
        }
        
    }
}