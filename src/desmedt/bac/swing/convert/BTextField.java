package desmedt.bac.swing.convert;

import javax.swing.*;
import java.awt.event.*;
import java.text.DecimalFormat;

public class BTextField extends JTextField implements FocusListener, KeyListener {
    
    public interface ValueChangeListener {
        void onValueChanged(Unit unit, double value);
    }
    
    private boolean decimal = false;
    private ValueChangeListener listener;
    
    public final Unit unit;
    
    public BTextField (Unit unit, ValueChangeListener listener) {
        this.unit = unit;
        this.listener = listener;
        this.addFocusListener(this);
        this.addKeyListener(this);
    }
    
    public void setValue (double value){
        DecimalFormat decimalFormat = new DecimalFormat("#.####");
        this.setText(decimalFormat.format(value));
    }
    
    @Override
    public void setText(String text) {
        super.setText(text);
        decimal = text.contains(".");
    }
    
    @Override
    public void focusGained(FocusEvent e) {
        this.select(0, getText().length());
    }
    
    @Override
    public void focusLost(FocusEvent e) {
        this.select(0, 0);
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        switch (e.getKeyChar()){
            case '.':
                if (decimal) e.consume();
                else decimal = true;
                break;
            case KeyEvent.VK_BACK_SPACE:
                decimal = this.getText().contains(".");
                break;
            default:
                if (!Character.isDigit(e.getKeyChar())) {
                    e.consume();
                }
        
        }
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
    
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        String text = this.getText();
        if (text != null && text.length() > 0) {
            if (listener != null)
                 listener.onValueChanged(unit, Double.parseDouble(text));
        }
    }
}
