package mobile.bny.swing.primes;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PrimeApp extends JFrame implements ActionListener, PrimeHandler.UIUpdater {
    
    public static final String CHECK_NUMBER = "Check number";
    public static final String CHECK_RANGE = "Check range";
    public static final String STOP = "Stop";
    public static final String CLEAR = "Clear";
    public static final String IDLE = "Idle";
    
    //    public static final String CHECK_NUMBER =
    
    //center
    JTextArea outputField = new JTextArea();
    JScrollPane outputScrollPane = new JScrollPane(
            outputField,
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
    );
    
    //west
    JPanel west = new JPanel();//contains buttons
    JButton bCheckNumber = new JButton(CHECK_NUMBER);
    JButton bCheckRange = new JButton(CHECK_RANGE);
    JButton bClear = new JButton(CLEAR);
    
    //south
    JPanel south = new JPanel();//contain input along with variables
    JLabel inputLabel = new JLabel("Input");
    JTextField inputField = new JTextField(10);
    JProgressBar progress = new JProgressBar();
    JLabel countLabel = new JLabel("Primes Found");
    JTextField countField = new JTextField(10);
    
    PrimeHandler primeHandler;
    
    public static void main(String[] args) {
        new PrimeApp();
    }
    
    public PrimeApp() {
        super("Prime Generation");
        setSize(600, 400);
        setResizable(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        Container pane = getContentPane();
        
        //Prime number output (center)
        outputField.setEditable(false);
        outputField.setLineWrap(true);
        outputField.setWrapStyleWord(true);
        outputField.setBorder(new EmptyBorder(1, 10, 1, 1));
        pane.add(outputScrollPane, BorderLayout.CENTER);
        
        //Buttons (west)
        west.setLayout(new GridLayout(10, 1));
        bCheckNumber.addActionListener(this);
        bCheckRange.addActionListener(this);
        bClear.addActionListener(this);
        west.add(bCheckNumber);
        west.add(bCheckRange);
        west.add(bClear);
        pane.add(west, BorderLayout.WEST);
        
        //South panel
        progress.setString(IDLE);
        progress.setStringPainted(true);
        countField.setEditable(false);
        south.setBorder(new EmptyBorder(10, 10, 10, 1));
        south.add(inputLabel);
        south.add(inputField);
        south.add(progress);
        south.add(countLabel);
        south.add(countField);
        pane.add(south, BorderLayout.SOUTH);
        
        setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        String input = inputField.getText();
        int number = isNumber(input);
        String output = "Input is invalid!";
        countField.setText("0");
        progress.setValue(0);
        progress.setString(IDLE);
        
        
        switch (e.getActionCommand()) {
            case CHECK_NUMBER:
                if (number >= 0) {
                    output = String.format(
                            "%n%d is %sa prime number",
                            number, PrimeHandler.isPrime(number) ? "" : "not "
                    );
                    countField.setText(PrimeHandler.isPrime(number) ? "1" : "0") ;
                }
                displayOutput(output);
                break;
            case CHECK_RANGE:
                if (number > 1) {
                    primeHandler = new PrimeHandler(this, number);
                    primeHandler.start();
                } else {
                    displayOutput(output);
                }
                break;
            case STOP:
                if (primeHandler != null) primeHandler.stop(true);
                break;
            case CLEAR:
                output = "";
                outputField.setText(output);
                break;
        }
    }
    
    public int isNumber(String input) {
        if (input.matches("\\d+")) {
            long l = Long.parseLong(input);
            return (l > Integer.MAX_VALUE) ? -1 : (int) l;
        }
        
        return -1;
    }
    
    void displayOutput(String text) {
        if (text != null && !text.isEmpty()) {
            int position = outputField.getText().length();
            outputField.insert("\n" + text, position);
        }
    
        JScrollBar sb = outputScrollPane.getVerticalScrollBar();
        sb.setValue(sb.getMaximum());
    }
    
    @Override
    public void onStart(int countGuess) {
        progress.setMaximum(countGuess);
        progress.setValue(0);
        progress.setString("Loading");
        
        countField.setText("0");
        bCheckRange.setText(STOP);
        
        displayOutput("\nLooking for Primes:");
    }
    
    @Override
    public void onProgressUpdate(int prime, int count) {
        displayOutput(Integer.toString(prime));
        countField.setText(Integer.toString(count));
        
        int value = Math.min(progress.getValue() + 1, progress.getMaximum());
        progress.setValue(value);
        
    }
    
    @Override
    public void onFinished(boolean interrupted, int count) {
        if (interrupted) {
            
            displayOutput("Interrupted");
            progress.setString("Interrupted");
            
        } else {
            
            progress.setValue(progress.getMaximum());
            progress.setString("Finished");
            displayOutput(String.format("%nFound %d primes!", count));
        }
        
            bCheckRange.setText(CHECK_RANGE);
    }
    
}
