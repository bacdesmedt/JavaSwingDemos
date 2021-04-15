package desmedt.bac.swing.tictactoe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

public class TicTacToeGame extends JFrame implements GameGridLayout.OnPlayListener {
    public static final String PLAYER_ONE = "Player 1's turn";
    public static final String PLAYER_TWO = "Player 2's turn";

    GameGridLayout gameGridLayout = new GameGridLayout();
    JLabel label = new JLabel(PLAYER_ONE, SwingConstants.CENTER);

    public static void main(String[] args) {
        new TicTacToeGame();
    }

    public TicTacToeGame() {
        super("Tic Tac Toe");
        setSize(400,450);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Container pane = getContentPane();

        label.setPreferredSize(new Dimension(200, 50));
        pane.add(label, BorderLayout.NORTH);

        gameGridLayout.addOnPlayListener(this);
        pane.add(gameGridLayout, BorderLayout.CENTER);
        setVisible(true);
    }

    @Override
    public void onEndTurn(boolean isPlayerX) {
        label.setText(isPlayerX ? PLAYER_ONE : PLAYER_TWO);
    }

    @Override
    public void onWin(boolean isPlayerX) {
        JLabel mLabel = new JLabel(
                String.format("\tPlayer %d wins! Play Again?", isPlayerX ? 1 : 2),
                SwingConstants.CENTER
        );
        int choice = JOptionPane.showConfirmDialog(
                null,
                mLabel,
                "",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if(choice == 0)
            main(null);
        else
            dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }
}
