package desmedt.bac.swing.colors;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class ColourChannelSlider extends JPanel implements ChangeListener {
    
    interface ValueChangeListener {
        void onValueChange(String name, int value);
    }
    
    private JSlider slider;
    private JLabel label;
    private JSpinner spinner;
    private String name;
    private int value;
    private ValueChangeListener listener;
    
    public ColourChannelSlider(String name, ValueChangeListener listener) {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(5,10,5,10));
    
        this.listener = listener;
        this.name = name;
        
        GridBagConstraints constraints = new GridBagConstraints();
//        constraints.anchor = GridBagConstraints.CENTER;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        
        //LABEL
//        label = new JLabel(name);
        label = new JLabel(name, SwingConstants.RIGHT);
        label.setPreferredSize(new Dimension(80,16));
        add(label, BorderLayout.WEST);
        
        //SLIDER
        slider = new JSlider(0,255);
        slider.setValue(255);
        slider.addChangeListener(this);
        slider.setPreferredSize(new Dimension(225,16));
        slider.setBorder(BorderFactory.createEmptyBorder(0,10,0,10));
        add(slider, BorderLayout.CENTER);
        
        //SPINNER
        spinner = new JSpinner(new SpinnerNumberModel(255,0,255,1));
        spinner.addChangeListener(this);
        add(spinner,BorderLayout.EAST);
    }
    
    @Override
    public void stateChanged(ChangeEvent e) {
        if (e.getSource() instanceof JSpinner){
            value = (Integer) spinner.getValue();
            slider.setValue(value);
        }
        if (e.getSource() instanceof JSlider) {
            value = slider.getValue();
            spinner.setValue(value);
        }
        
        if (listener != null)
            listener.onValueChange(name,value);
    
        System.out.println(label.getPreferredSize());
    }
}
