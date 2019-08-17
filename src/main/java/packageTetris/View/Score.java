package main.java.packageTetris.View;

import javax.swing.*;
import java.awt.*;

public class Score extends JPanel {
    private  Label score = new Label();
    public Score() {
        this.setLayout(new BorderLayout());
        this.add(score,BorderLayout.CENTER);
        score.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        score.setForeground(Color.RED);
        score.setAlignment(Label.CENTER);
    }
    public void setScore(int score) {
        this.score.setText("Your score :" + score);
    }
}
