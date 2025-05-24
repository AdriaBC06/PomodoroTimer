package lol.adriabonnin.pomodoro_timer.ui;

import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel {
    private JLabel timeCountDownLabel;
    private int getTimeCountDownInMinutes = 25;
    private int timeCountDown;

    private JLabel counterLabel;
    private int counterTextSize = 100;

    private JButton startButton = new JButton("START!");
    private JButton addOneMinuteButton = new JButton("+1");
    private JButton removeOneMinuteButton = new JButton("-1");

    public Panel() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.setBackground(new Color(241, 231, 231));

        timeCountDownLabel = new JLabel(String.valueOf(getTimeCountDownInMinutes));

        counterLabel = new JLabel(formatTime(timeCountDown));
        setLabelTextSize(counterLabel, counterTextSize);
        counterLabel.setForeground(new Color(230, 157, 184));

        this.add(removeOneMinuteButton);
        this.add(timeCountDownLabel);
        this.add(addOneMinuteButton);

        this.add(counterLabel);

        this.add(startButton);

        removeOneMinuteButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        timeCountDownLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        addOneMinuteButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        counterLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        Timer timer = new Timer(1000, e -> {
            timeCountDown--;
            updateText(counterLabel, formatTime(timeCountDown));
        });

        startButton.addActionListener(e -> {
            timer.start();
            System.out.println("timer started");
        });
    }

    public void updateText(JLabel label, String text) {
        label.setText(text);
    }

    public void setLabelTextSize(JLabel label, int size) {
        Font currentFont = label.getFont();
        label.setFont(new Font(currentFont.getName(), currentFont.getStyle(), size));
    }

    public String formatTime(int totalSeconds) {
        int hours = totalSeconds / 3600;
        int minutes = (totalSeconds % 3600) / 60;
        int seconds = totalSeconds % 60;

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
}
