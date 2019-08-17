package main.java.packageTetris.Model;
import java.awt.*;

public class Block {
    private int x,y;

    public Block(int x, int y) {
        this.setX(x);
        this.setY(y);
    }

    public void setX (int x) {
        this.x = x;
    }

    public void setY (int y) {
        this.y = y;
    }

    public int getX () {
        return x;
    }

    public int getY () {
        return y;
    }

    public void paintBlock(Graphics g, int blockWidth, int blockHeight, int blockArc) {
        g.setColor(Color.ORANGE);
        g.fillRoundRect(x * blockWidth, y * blockHeight, blockWidth, blockHeight, blockArc, blockArc);
        g.setColor(Color.BLACK);
        g.drawRoundRect(x * blockWidth, y * blockHeight, blockWidth, blockHeight, blockArc,blockArc);
    }

}
