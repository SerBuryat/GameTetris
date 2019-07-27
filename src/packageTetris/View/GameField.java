package packageTetris.View;

import packageTetris.Controller.GameEngine;
import packageTetris.Controller.KeyControl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GameField extends JPanel  implements ActionListener {
    private GameEngine gameEngine;
    public static final int FIELD_WIDTH = 11;
    public static final int FIELD_HEIGHT = 18;
    public int BLOCK_WIDTH;
    public int BLOCK_HEIGHT;
    public static int[][] fieldBoard = new int[FIELD_WIDTH][FIELD_HEIGHT];

    public GameField(GameEngine gameEngine) {
        setFocusable(true);
        this.gameEngine = gameEngine;
        addKeyListener(new KeyControl(this.gameEngine));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gameEngine.gameInAction();
        repaint();
    }

    public void paint(Graphics g) {
        super.paint(g);
        gameEngine.paintGame(g, FIELD_WIDTH, FIELD_HEIGHT, getBlockWidth(), getBlockHeight());
    }

    public void drawGamefieldBlock(Graphics g, int i, int j, int blockWidth, int blockHeight) {
        if(fieldBoard[i][j] == 0) {
            g.setColor(Color.DARK_GRAY);
            g.drawRect(i * blockWidth, j * blockHeight, blockWidth, blockHeight);
        } else {
            g.setColor(Color.ORANGE);
            g.fillRect(i * blockWidth, j * blockHeight, blockWidth, blockHeight);
        }
    }

    private int getBlockWidth() {
        return BLOCK_WIDTH = (int)getSize().getWidth() / FIELD_WIDTH;
    }

    private int getBlockHeight() {
        return  BLOCK_HEIGHT = (int) getSize().getHeight() / FIELD_HEIGHT;
    }

}