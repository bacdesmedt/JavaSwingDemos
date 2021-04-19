package desmedt.bac.swing.colors;

import javax.swing.*;
import java.awt.*;

public class ColourSliderPanel extends JPanel  implements ColourChannelSlider.ValueChangeListener{
    
    interface ColourChangeListener{
        void OnColourChange(ColourSpace colourSpace);
    }
    
    private ColourSpace colourSpace;
    private ColourChangeListener listener;
    
    public ColourSliderPanel(ColourSpace colourSpace, ColourChangeListener listener) {
        this.colourSpace = colourSpace;
        this.listener = listener;
        GridBagLayout gridBagLayout = new GridBagLayout();
        if( colourSpace.equals(ColourSpace.CMYK)) {
            gridBagLayout.rowHeights = new int[]{ 1, 1, 1, 1 ,1 };
            gridBagLayout.rowWeights = new double[]{ 0.2, 0.2, 0.2, 0.2, 0.2 };
        } else {
            gridBagLayout.rowHeights = new int[]{ 1, 1, 1, 1 };
            gridBagLayout.rowWeights = new double[]{ 0.25, 0.25, 0.25, 0.25 };
        }
        setLayout(gridBagLayout);
        GridBagConstraints constraints = gridBagLayout.getConstraints(this);
        constraints.fill = GridBagConstraints.HORIZONTAL;
    
        String[] labels = colourSpace.labels;
        for (int i = 0; i < labels.length; i++) {
            ColourChannelSlider s = new ColourChannelSlider(labels[i], this);
            constraints.gridy = i ;
            add(s, constraints);
        }
    }
    
    @Override
    public void onValueChange(String name, int value) {
//        System.out.println(name+value);
    }
    
    
    
}
