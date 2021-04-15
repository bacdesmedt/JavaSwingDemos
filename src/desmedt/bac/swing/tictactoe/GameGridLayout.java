package desmedt.bac.swing.tictactoe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GameGridLayout extends JPanel implements ActionListener {

    //INSTANCE VARIABLES
    private boolean[] Xs = new boolean[9];
    private boolean[] Os = new boolean[9];
    private XOButton[] buttons = new XOButton[9];
    private GridLayout grid = new GridLayout(3, 3);

    //LISTENER
    private ArrayList<OnPlayListener> listeners = new ArrayList<>();

    public void addOnPlayListener(OnPlayListener listener) {
        listeners.add(listener);
    }

    public interface OnPlayListener {
        void onGameEnd(boolean isPlayerX, boolean draw);
        void onEndTurn(boolean isPlayerX);
    }

    //CONSTRUCTOR
    public GameGridLayout() {
        super();
        setLayout(grid);
        setSize(new Dimension(400, 400));

        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new XOButton(i);
            buttons[i].addActionListener(this);
            add(buttons[i]);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        XOButton xoButton = (XOButton) e.getSource();

        if (!xoButton.isPressed()) {
            int id = xoButton.getId();

            xoButton.play();

            if (xoButton.isPlayerXTurn()) {
                Xs[id] = true;
                checkIfWon(Xs, true);
            } else {
                Os[id] = true;
                checkIfWon(Os, false);
            }

            xoButton.endTurn();
            for (OnPlayListener listener : listeners) {
                listener.onEndTurn(xoButton.isPlayerXTurn());
            }
        }
    }
    
    private boolean movesLeft() {
        for (XOButton button : buttons) {
            if (!button.isPressed()) return true;
        }
        return false;
    }

    //WIN LOGIC
    private void checkIfWon(boolean[] checkStates, boolean isPlayerX) {
        if (isWon(checkStates)) {
            for (OnPlayListener listener : listeners) {
                listener.onGameEnd(isPlayerX, false);
            }
        } else if (!movesLeft()) {
            for (OnPlayListener listener : listeners) {
                listener.onGameEnd(isPlayerX, true);
            }
        }
    }

    private boolean isWon(boolean[] checkStates) {
        return isRowWin(checkStates) || isColumnWin(checkStates) || isDiagonalWin(checkStates);
    }

    private boolean isRowWin(boolean[] checkStates) {
        return checkStates[0] && checkStates[1] && checkStates[2]           //row 1
                || checkStates[3] && checkStates[4] && checkStates[5]       //row 2
                || checkStates[6] && checkStates[7] && checkStates[8];      //row 3
    }

    private boolean isColumnWin(boolean[] checkStates) {
        return checkStates[0] && checkStates[3] && checkStates[6]           //column 1
                || checkStates[1] && checkStates[4] && checkStates[7]       //column 2
                || checkStates[2] && checkStates[5] && checkStates[8];      //column 3
    }

    private boolean isDiagonalWin(boolean[] checkStates) {
        return checkStates[4] && (                                          // center
                checkStates[0] && checkStates[8]                            // left diagonal corners
                || checkStates[2] && checkStates[6]                         // right diagonal corners
                );
    }
}
