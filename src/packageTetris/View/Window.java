package packageTetris.View;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    private static final int WIDTH = 545, HEIGHT = 960; // optimal is W = 545; H = 960
    private GameField gameField;
    private Score score;

    public Window(GameField gameField, Score score) {
        this.gameField = gameField;
        this.score = score;
    }

    public void initialize() {
        setTitle("Tetris by SerButryat");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        setLayout(new BorderLayout());
        setGameField();
    }

    public void setGameField() {
        add(gameField, BorderLayout.CENTER);
        add(score,BorderLayout.NORTH);
    }
}
