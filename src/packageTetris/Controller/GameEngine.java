package packageTetris.Controller;

import packageTetris.Model.Block;
import packageTetris.Model.Shape;
import packageTetris.View.GameField;
import packageTetris.View.Messages;
import packageTetris.View.Score;
import packageTetris.View.Window;
import javax.swing.Timer;
import java.awt.*;
import java.util.Arrays;

public class GameEngine {
    private static final int SPEED = 500;
    private static Timer timer;
    private boolean isGameStopped;

    private Window window;
    private GameField gameField;
    private Score scoreField;

    private int score;

    private int startX;
    private int startY;

    private Shape currentShape;

    public GameEngine() {
        gameField = new GameField(this);
        scoreField = new Score();

        window = new Window(this.gameField,this.scoreField);
        window.initialize();

        timer = new Timer(SPEED, gameField);
        timer.start();

        startX = GameField.FIELD_WIDTH/2;
        startY = 0;

        currentShape = new Shape(startX,startY);
        scoreField.setScore(score);
    }

    public void gameInAction() { // game is in action
        if(isGameStopped) { // check isGameOver
            timer.stop();
            setGameOverMessage();
        } else {
            if(isTouchGround()) {
                currentShape.leaveOnTheGround();
                if(isOverFlow()) // isOverFlow -> gameOver
                    isGameStopped = true;
                deleteFullRow(); // check and delete filled rows
                scoreField.setScore(score); // setScore after delete the row(s)
                currentShape = new Shape(startX, startY);
            } else
                currentShape.shapeStepDown();
        }
       }

    private boolean isTouchGround() { // is  shape on the ground?
        for(Block block : currentShape.shapeBlocks) {
            if(block.getY()+1 > GameField.FIELD_HEIGHT - 1 || GameField.fieldBoard[block.getX()][block.getY() + 1] == 1)
                return true;
        }
        return false;
    }
    private boolean isOverFlow() {
        for(Block block : currentShape.shapeBlocks) {
            if(block.getY() - 1 < 0) {
                return true;
            }
        }
        return false;
    }

    private void deleteFullRow() { // 1) Check if row is full (all cells in the row is "1" ) 2) Delete this row (all cells = 0).   3) Fill the gap after deleted row(s).
        int currentShapeMINy = currentShape.getMINy(); // getY of firstBlock in the shape
        int currentShapeMAXy = currentShape.getMAXy(); // getY of lastBlock in the shape
        int counterForScore = 0; // counter for score
        int multiplierForScore = 0; // 1 row deleted = score*1...n row deleted = score*n

        for(int y = currentShapeMINy; y <= currentShapeMAXy; y++) { // take distance between firstBlockY and  lastBlockY (starting from bottom to up)
            int[] array = new int[GameField.FIELD_WIDTH];
            for(int x = 0; x < GameField.FIELD_WIDTH; x++)
                array[x] = GameField.fieldBoard[x][y];

            if(Arrays.stream(array).allMatch(n -> n == 1)) { // if row is full (every cell == 1)
                counterForScore+=100; // if row deleted + 100 for score
                multiplierForScore+=1;
                for(int x = 0; x < GameField.FIELD_WIDTH; x++)
                    GameField.fieldBoard[x][y] = 0; // deleting first full row from up
                for(int i = 0; i < GameField.FIELD_WIDTH; i++) {
                    for(int j = y; j >= 0; j--) {
                        if(j != 0) //if not first row of gameField copy upper row to current row
                            GameField.fieldBoard[i][j] = GameField.fieldBoard[i][j-1]; // copy all rows from deleted row to up(first row cells == 0)
                        else
                            GameField.fieldBoard[i][j] = 0;
                    }
                }
            }
        }
        counterForScore*=multiplierForScore; // score * multiplier
        score += counterForScore;
    }

    public void paintGame(Graphics g, int fieldWidth, int fieldHeight, int blockWidth, int blockHeight, int blockArc) { // draw field and blocks on the ground
        for(int i = 0; i < fieldWidth; i++) {
            for(int j = 0; j < fieldHeight; j++) {
                gameField.drawGamefieldBlock(g, i, j, blockWidth, blockHeight, blockArc);
            }
        }
        currentShape.shapePaint(g, blockWidth, blockHeight,blockArc);
    }
    public void repaintField() {
        gameField.repaint();
    }

    private void setGameOverMessage() {
        Messages.getMessage("Oooops! Game over! " + "\n" + "Your score : " + score + "\n" + "Press ESC to restart game!");
    }
    public void resetGame() {
        isGameStopped = true;
        for(int i = 0; i < GameField.FIELD_WIDTH; i++) {
            for(int j = 0; j < GameField.FIELD_HEIGHT; j++) {
                GameField.fieldBoard[i][j] = 0;
            }
        }
        scoreField.setScore(0);
        isGameStopped = false;
        currentShape = new Shape(startX,startY);
        timer.start();
    }

    public static void setGreetingsMessage() {
        Messages.getMessage("Hey, buddy! There is some rules : " + "\n" +
                           "1.RIGHT - move right, LEFT - move left, UP - rotate, DOWN - down (faster)" + "\n" +
                           "2.ESC - reset , P - pause, C - continue " + "\n" +
                           "Good Luck! :)");
    }

    public void setPause() {
        isGameStopped = true;
        timer.stop();
    }
    public void setContinue() {
        isGameStopped = false;
        timer.start();
    }

    private boolean tryMove(int direction) { //-1 left and 1 right
        if(!isGameStopped) {
            for(Block block : currentShape.shapeBlocks) {
                if ((block.getX() + direction) > GameField.FIELD_WIDTH - 1 || (block.getX() + direction) < 0) // if borders there
                    return false;
                if(GameField.fieldBoard[block.getX() + direction][block.getY()] == 1) // if block there
                    return false;
            }
            return true;
        }
        return false;
    }
    private boolean tryRotate() { // check borders and move shape if need
        if(!isGameStopped) {
            int diffX = currentShape.getShapeHeight() - currentShape.getShapeWidth();
            int diffY = currentShape.getShapeWidth() - currentShape.getShapeHeight();
            for(Block block : currentShape.shapeBlocks) {
                if(block.getX() + currentShape.getShapeHeight() > GameField.FIELD_WIDTH -  1) {
                    currentShape.setX(currentShape.getX()-diffX);
                    return true;
                }
                if(block.getY() + currentShape.getShapeWidth() > GameField.FIELD_HEIGHT - 1) {
                    currentShape.setY(currentShape.getY()-diffY);
                    return true;
                }

            }
            return true;
        }
        return false;
    }// need to fix rotation on floorBlocks, when shape is rotating and and sets in another block

    public void shapeMoveDown() {
        if(!isGameStopped) {
            if(!isTouchGround())
                currentShape.shapeStepDown();
        }
    }
    public void shapeMoveLeft() { // shape MOVEMENT LEFT
        if(tryMove(-1))
            currentShape.shapeStepLeft();
    }
    public void shapeMoveRight() { // shape MOVEMENT RIGHT
        if(tryMove(1))
            currentShape.shapeStepRight();
    }
    public void shapeRotate() { // shape ROTATION RIGHT
        if(tryRotate()) {
            if(!isTouchGround())
                currentShape.rotateShape();
        }
    }
}
