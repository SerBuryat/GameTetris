package packageTetris.View;

import javax.swing.*;
import java.awt.*;

public class StartMenu extends JPanel {
    private JLabel greetingsLabel = new JLabel("Hi!");
    private JLabel rulesLabel = new JLabel("Hi!");
    private JButton startGameButton = new JButton("Start Game");

    public StartMenu() {
        add(greetingsLabel);
        add(rulesLabel);
        add(startGameButton);
        setLayout(new GridLayout(getComponentCount(), 0));
    }

}
