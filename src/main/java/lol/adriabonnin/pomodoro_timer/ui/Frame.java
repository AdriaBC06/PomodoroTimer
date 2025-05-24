package lol.adriabonnin.pomodoro_timer.ui;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
    public Frame(){
        JPanel panel = new Panel();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setResizable(false);

        setTitle("Pomodoro Timer");

        Toolkit screen = Toolkit.getDefaultToolkit();
        Dimension screenSize = screen.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize(650, 500);
        setLocationRelativeTo(null);

        setVisible(true);

        this.add(panel);
    }
}
