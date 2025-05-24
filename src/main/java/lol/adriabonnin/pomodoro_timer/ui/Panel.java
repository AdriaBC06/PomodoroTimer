package lol.adriabonnin.pomodoro_timer.ui;

import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel {
    private JLabel timeCountDownLabel;
    private int timeCountDownInMinutes = 25;
    private int timeCountDown;

    private JLabel counterLabel;
    private int counterTextSize = 100;

    private JButton startButton = new JButton("START!");
    private JButton addOneMinuteButton = new JButton("+1");
    private JButton removeOneMinuteButton = new JButton("-1");
    private JButton addFiveMinutesButton = new JButton("+5");
    private JButton removeFiveMinutesButton = new JButton("-5");

    private JPanel topPanel = new JPanel();

    private Color backgroundColor = new Color(241, 231, 231);
    private Color topTimeColor = new Color(230, 157, 184);
    private Color timerColor = new Color(230, 157, 184);
    private Color startButtonBackgroundColor = new Color(255, 208, 199);

    public Panel() {
        // SET LAYOUT FOR MAIN PANEL
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // SET BACKGROUND COLOR FOR MAIN PANEL
        this.setBackground(backgroundColor);

        // TOP PANEL CONFIGURATION
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        topPanel.setBackground(backgroundColor);

        // SET DEFAULT TEXT FOR TOP TIME LABEL
        timeCountDownLabel = new JLabel(timeCountDownInMinutes + "m");

        // CONFIGURE TOP TIMER TEXT (MINUTE PICKER)
        setLabelTextSize(timeCountDownLabel, 50);
        timeCountDownLabel.setForeground(topTimeColor);

        // INITIALIZE COUNTER LABEL
        counterLabel = new JLabel(formatTime(timeCountDown));
        setLabelTextSize(counterLabel, counterTextSize);
        counterLabel.setForeground(timerColor);

        // SET START BUTTON SIZE
        startButton.setPreferredSize(new Dimension(100, 100));

        // TOP PANEL CREATION
        topPanel.add(removeFiveMinutesButton);
        topPanel.add(Box.createRigidArea(new Dimension(15, 0)));
        topPanel.add(removeOneMinuteButton);
        topPanel.add(Box.createRigidArea(new Dimension(15, 0)));
        topPanel.add(timeCountDownLabel);
        topPanel.add(Box.createRigidArea(new Dimension(15, 0)));
        topPanel.add(addOneMinuteButton);
        topPanel.add(Box.createRigidArea(new Dimension(15, 0)));
        topPanel.add(addFiveMinutesButton);

        // MAIN PANEL CREATION
        this.add(Box.createRigidArea(new Dimension(0, 50)));
        this.add(topPanel);
        this.add(Box.createRigidArea(new Dimension(0, 30)));
        this.add(counterLabel);
        this.add(Box.createRigidArea(new Dimension(0, 20)));
        this.add(startButton);
        topPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        counterLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // TIMER COUNTDOWN
        Timer timer = new Timer(1000, e -> {
            timeCountDown--;
            updateText(counterLabel, formatTime(timeCountDown));
        });

        // TOP BUTTON FUNCTIONS
        removeOneMinuteButton.addActionListener(e -> {
            if (timeCountDownInMinutes > 1) {
                timeCountDownInMinutes--;
            }
            updateText(timeCountDownLabel, (timeCountDownInMinutes + "m"));
        });
        addOneMinuteButton.addActionListener(e -> {
            if (timeCountDownInMinutes < 120) {
                timeCountDownInMinutes++;
            }
            updateText(timeCountDownLabel, (timeCountDownInMinutes + "m"));
        });
        removeFiveMinutesButton.addActionListener(e -> {
            if (timeCountDownInMinutes > 5) {
                timeCountDownInMinutes -= 5;
            }
            updateText(timeCountDownLabel, (timeCountDownInMinutes + "m"));
        });
        addFiveMinutesButton.addActionListener(e -> {
            timeCountDownInMinutes += 5;
            if (timeCountDownInMinutes > 120) {
                timeCountDownInMinutes = 120;
            }
            updateText(timeCountDownLabel, (timeCountDownInMinutes + "m"));
        });

        // START BUTTON FUNCTION
        startButton.addActionListener(e -> {
            timeCountDown = timeCountDownInMinutes * 60;
            timer.start();
            System.out.println("timer started");
        });
    }

    // FUNCTION TO UPDATE THE TEXT OF A LABEL
    public void updateText(JLabel label, String text) {
        label.setText(text);
    }

    // FUNCTION TO SET THE SIZE OF THE TEXT OF A LABEL
    public void setLabelTextSize(JLabel label, int size) {
        Font currentFont = label.getFont();
        label.setFont(new Font(currentFont.getName(), currentFont.getStyle(), size));
    }

    // FUNCTION TO FORMAT TIME FROM SECONDS TO HH:MM:SS FORMAT
    public String formatTime(int totalSeconds) {
        int hours = totalSeconds / 3600;
        int minutes = (totalSeconds % 3600) / 60;
        int seconds = totalSeconds % 60;

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
}
