package packageTetris.Model;
import packageTetris.View.GameField;

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

    public void paintBlock(Graphics g, int blockWidth, int blockHeight) {
        g.setColor(Color.orange);
        g.fillRect(x * blockWidth, y * blockHeight, blockWidth, blockHeight);
    }

}
