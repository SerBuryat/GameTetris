package main.java.packageTetris;

import main.java.packageTetris.Controller.GameEngine;

public class TetrisGameStart {

    public static void main(String[] args) {
        GameEngine.setGreetingsMessage();
        new GameEngine();
    }
}
