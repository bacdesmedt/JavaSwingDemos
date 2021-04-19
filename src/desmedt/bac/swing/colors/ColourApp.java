package desmedt.bac.swing.colors;

import javax.swing.*;
import java.awt.*;

public class ColourApp extends JFrame /*implements ColourSliderPanel.ColourChangeListener */{
    
    public static final String RGB_LABEL = "RGB";
    public static final String HSB_LABEL = "HSB";
    public static final String CMYK_LABEL = "CMYK";
    
    JPanel panelColour = new JPanel();
    ColourView colourView = new ColourView(150, Color.WHITE);
    
    JTabbedPane tabs = new JTabbedPane();
    ColourSliderPanel panelRGB = new ColourSliderPanel(ColourSpace.RGB, null);
    ColourSliderPanel panelHSB = new ColourSliderPanel(ColourSpace.HSB, null);
    ColourSliderPanel panelCMYK = new ColourSliderPanel(ColourSpace.CMYK, null);
    
    public ColourApp() {
        super("Colour picker");
        setSize(500,500);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    
        Container pane = getContentPane();
        
        //COLOUR VIEW
        panelColour.setPreferredSize(new Dimension(400,200));
        panelColour.setLayout(new GridBagLayout());
        panelColour.add(colourView);
        pane.add(panelColour, BorderLayout.NORTH);
        
        //TABS
        tabs.setPreferredSize(new Dimension(500,300));
        tabs.add(panelRGB, RGB_LABEL);
        tabs.add(panelHSB, HSB_LABEL);
        tabs.add(panelCMYK, CMYK_LABEL);
        pane.add(tabs,BorderLayout.CENTER);
    
        setVisible(true);
    }
    
    public static void main(String[] args) {
        new ColourApp();
    }
    
}
