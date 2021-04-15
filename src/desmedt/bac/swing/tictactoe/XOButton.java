package desmedt.bac.swing.tictactoe;

import javax.swing.*;

public class XOButton extends JButton {

    private static boolean playerXTurn = true;

    private final int id;
    private final ImageIcon X;
    private final ImageIcon O;
    private boolean pressed = false;

    public XOButton(int id){
        super();
        this.id =id;
        this.X = new ImageIcon(this.getClass().getResource("X.png"));
        this.O = new ImageIcon(this.getClass().getResource("O.png"));
    }

    public int getId() {
        return this.id;
    }

    public boolean isPlayerXTurn() {
        return playerXTurn;
    }

    public boolean isPressed() {
        return this.pressed;
    }

    public void play(){
        setIcon(playerXTurn ? X : O);
        this.pressed = !this.pressed;
    }

    public void endTurn() {
        playerXTurn = !playerXTurn;
    }
}
