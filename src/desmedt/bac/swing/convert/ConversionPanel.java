package desmedt.bac.swing.convert;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import static desmedt.bac.swing.convert.UnitOperators.CONVERTER;

public class ConversionPanel extends JPanel implements BTextField.ValueChangeListener {
    String title;
    Unit[] units;
    Map<Unit,Double> dataMap;
    JLabel[] labels;
    BTextField[] textFields;
    
    
    public ConversionPanel(UnitGroup unitGroup) {
        
        title = unitGroup.toString();
        units = unitGroup.units;
        dataMap = new HashMap<>();
        labels = new JLabel[units.length];
        textFields = new BTextField[units.length];
        
        setLayout(new GridLayout(units.length,2));
        setBorder(BorderFactory.createEmptyBorder(50,50,50,50));
        
    
        for (int i = 0; i < units.length; i++) {
            Unit unit = units[i];
            labels[i] = new JLabel(unit.toString());
            textFields[i] = new  BTextField(unit, this);
            dataMap.put(unit,0.0);
            add(labels[i]);
            add(textFields[i]);
        }
    }
    
    public void updateValues(Unit unit, double value) {
    
    }
    
    @Override
    public void onValueChanged(Unit unit, double value) {
        if(dataMap.get(unit).equals(value)) return;
    
        dataMap = CONVERTER.apply(unit,value);
        
        for (BTextField textField : textFields) {
            if (!textField.unit.equals(unit)) {
                textField.setValue(dataMap.get(textField.unit));
            }
        }
    }
}
