package packageTetris.Model;

import packageTetris.View.GameField;

import java.awt.*;
import java.util.ArrayList;

public class Shape {
    public int x,y;
    public int[][] shapePattern; // shapePattern for current shape
    private static ArrayList<int[][]> shapePatterns; // list of shape's possible forms
    public ArrayList<Block> shapeBlocks; // block's list in shape

    static {
        shapePatterns = new ArrayList<>();

        shapePatterns.add(new int[][] {       {1, 1},
                                              {1, 1}
                                                             }); // [] - shape;

        shapePatterns.add(new int[][] {       {1, 1, 0},
                                              {0, 1, 1}
                                                             }); // z - shape;

        shapePatterns.add(new int[][] {       {0, 1, 1},
                                              {1, 1, 0}
                                                             }); // s - shape;*/

        shapePatterns.add(new int[][] {       {1, 1, 1, 1}
                                                             }); // | - shape;

        shapePatterns.add(new int[][] {       {0, 1, 0},
                                              {1, 1, 1}
                                                             }); // _|_ - shape;

        shapePatterns.add(new int[][] {       {0, 0, 1},
                                              {1, 1, 1}
                                                             }); // L - shape;

        shapePatterns.add(new int[][] {       {1, 0, 0},
                                              {1, 1, 1}
        }); // _/ - shape;

    }

    public Shape(int x, int y) {

        this.x = x;
        this.y = y;

        shapePattern = getRandomShapePattern();

        createFromShapePattern();
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getShapeWidth() {
        return shapePattern.length;
    }

    public int getShapeHeight() {
        return shapePattern[0].length;
    }

    public int getMAXy() { //return maxY in Shape
        Block[] arraySortedBlocks =  shapeBlocks.stream().sorted((block1,block2) -> ((Integer)block1.getY()).compareTo(block2.getY())).toArray(Block[]::new);//sorted array of blocks by Y
        Block blockWithMAXy = arraySortedBlocks[arraySortedBlocks.length-1]; // block with MAX y
        return blockWithMAXy.getY();
    }

    public int getMINy() { //return minY in Shape
        Block[] arraySortedBlocks =  shapeBlocks.stream().sorted((block1,block2) -> ((Integer)block1.getY()).compareTo(block2.getY())).toArray(Block[]::new);//sorted array of blocks by Y
        Block blockWithMINy = arraySortedBlocks[0]; // block with MIN Y
        return blockWithMINy.getY();
    }

    private static int[][] getRandomShapePattern() {
        return shapePatterns.get((int) (Math.random() * shapePatterns.size()));
    }

    private void createFromShapePattern() {
        shapeBlocks = new ArrayList<>();
        for(int i = 0; i < shapePattern.length; i++) {
            for(int j = 0; j < shapePattern[i].length; j++) {
                if(shapePattern[i][j] == 1) {
                    shapeBlocks.add(new Block(i + this.x, j + this.y));
                }
            }
        }
    }

    public void leaveOnTheGround() { // leave current shape (blocks) on the gameField
        for(Block block : shapeBlocks)
            GameField.fieldBoard[block.getX()][block.getY()] = 1;
    }

    public void shapeStepDown() {
        for(Block block : shapeBlocks)
            block.setY(block.getY()+1);
        y++;
    }

    public void shapeStepRight() {
        for(Block block : shapeBlocks)
            block.setX(block.getX()+1);
        x++;
    }

    public void shapeStepLeft() {
        for(Block block : shapeBlocks)
            block.setX(block.getX()-1);
        x--;
    }

    public void rotateShape() {
        int width = shapePattern[0].length;
        int height = shapePattern.length;
        int[][] temp = new int[width][height];

        for(int i = 0; i < temp.length; i++) {
            for(int j = 0, j1 = temp[i].length-1; j < temp[i].length; j++, j1--) {
                temp[i][j] = shapePattern[j1][i];
            }
        }
        shapePattern = temp;

        shapeBlocks.clear();
        createFromShapePattern();
    }

    public void shapePaint(Graphics g,int blockWidth, int blockHeight, int blockArc) {
       for(Block block : shapeBlocks)
           block.paintBlock(g,blockWidth,blockHeight,blockArc);
    }
}

