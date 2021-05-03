package desmedt.bac.swing.convert;

import javax.swing.*;
import java.awt.*;

public class ConversionFrame extends JFrame {
    
        ConversionPanel distancePanel = new ConversionPanel(UnitGroup.DISTANCE);
        ConversionPanel massPanel = new ConversionPanel(UnitGroup.MASS);
        ConversionPanel tempPanel = new ConversionPanel(UnitGroup.TEMPERATURE);
        
        JTabbedPane tabs = new JTabbedPane();
        
        
    public ConversionFrame(){
        this.setTitle("Converter");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
//        this.setSize(new Dimension(500,300));

        tabs.add(distancePanel, distancePanel.title);
        tabs.add(massPanel, massPanel.title);
        tabs.add(tempPanel, tempPanel.title);

        this.add(tabs);

        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
    
}
