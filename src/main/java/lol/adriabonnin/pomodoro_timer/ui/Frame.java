package lol.adriabonnin.pomodoro_timer.ui;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
    public Frame(){
        JPanel panel = new Panel();

        this.add(panel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setTitle("Pomodoro Timer");

        Toolkit screen = Toolkit.getDefaultToolkit();
        Dimension screenSize = screen.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize(500, 500);
        setLocationRelativeTo(null);

        setVisible(true);
    }
}
