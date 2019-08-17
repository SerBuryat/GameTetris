package main.java.packageTetris.View;

import javax.swing.*;
import java.awt.*;

public class Window {
    public static final int WIDTH = 545, HEIGHT = 960; // optimal is W = 545; H = 960
    GameField gameField;
    Score score;

    public Window(GameField gameField, Score score) {
        this.gameField = gameField;
        this.score = score;
    }

    public void initialize() {
        JFrame window = new JFrame("Tetris by SerButryat");
        window.setSize(WIDTH, HEIGHT);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        window.setLayout(new BorderLayout());
        window.add(gameField, BorderLayout.CENTER);
        window.add(score,BorderLayout.NORTH);
    }
}
