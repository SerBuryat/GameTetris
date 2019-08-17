package main.java.packageTetris.Controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyControl extends KeyAdapter {
    GameEngine gameEngine;

    public KeyControl(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    public void keyPressed(KeyEvent e) {
        int keycode = e.getKeyCode();

        switch (keycode) {
            case KeyEvent.VK_LEFT:
                gameEngine.shapeMoveLeft();
                gameEngine.repaintField();
                break;
            case KeyEvent.VK_RIGHT:
                gameEngine.shapeMoveRight();
                gameEngine.repaintField();
                break;
            case KeyEvent.VK_UP:
                gameEngine.shapeRotate();
                gameEngine.repaintField();
                break;
            case KeyEvent.VK_DOWN:
                gameEngine.shapeMoveDown();
                gameEngine.repaintField();
                break;
            case KeyEvent.VK_ESCAPE:
                gameEngine.resetGame();
                gameEngine.repaintField();
                break;
            case KeyEvent.VK_P:
                gameEngine.setPause();
                break;
            case KeyEvent.VK_C:
                gameEngine.setContinue();
                break;
        }
    }
}
