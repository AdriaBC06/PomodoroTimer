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

    private JButton[] minutePickerButtonsList = {addOneMinuteButton, removeOneMinuteButton, addFiveMinutesButton, removeFiveMinutesButton};

    private JPanel topPanel = new JPanel();

    private Color backgroundColor = Colors.DARKMARINEBLUE;
    private Color topTimeColor = Colors.LIGHTMARINEBLUE;
    private Color topButtonsTextColor = Colors.DARKMARINEBLUE;
    private Color topButtonsBackgroundColor = Colors.LIGHTMARINEBLUE;
    private Color timerColor = Colors.LIGHTMARINEBLUE;
    private Color startButtonTextColor = Colors.DARKMARINEBLUE;
    private Color startButtonBackgroundColor = Colors.LIGHTMARINEBLUE;

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

        // TOP TIMER TEXT (MINUTE PICKER) CONFIGURATION
        setLabelTextSize(timeCountDownLabel, 50);
        timeCountDownLabel.setForeground(topTimeColor);

        // MINUTE PICKER BUTTONS CONFIGURATION
        Dimension topButtonsSize = new Dimension(60, 50); // SIZE
        for (JButton button : minutePickerButtonsList){
            button.setMinimumSize(topButtonsSize); // MINIMUM SIZE
            button.setMaximumSize(topButtonsSize); // MAXIMUM SIZE
            button.setPreferredSize(topButtonsSize); // PREFERRED SIZE
            button.setForeground(topButtonsTextColor); // TEXT COLOR
            button.setBackground(topButtonsBackgroundColor); // BACKGROUND COLOR
            button.setFont(new Font(button.getFont().getName(), button.getFont().getStyle(), 20)); // CHANGE FONT SIZE
            button.setBorderPainted(false); // REMOVE BORDER
            // REMOVE FOCUS STYLE
            button.setFocusPainted(false);
            button.setContentAreaFilled(true);
            button.setOpaque(true);
        }

        // INITIALIZE COUNTER LABEL
        counterLabel = new JLabel(formatTime(timeCountDown));
        setLabelTextSize(counterLabel, counterTextSize);
        counterLabel.setForeground(timerColor);

        // START BUTTON CONFIGURATION
        Dimension startButtonSize = new Dimension(300, 100); // SIZE
        startButton.setMinimumSize(startButtonSize); // MINIMUM SIZE
        startButton.setMaximumSize(startButtonSize); // MAXIMUM SIZE
        startButton.setPreferredSize(startButtonSize); // PREFERRED SIZE
        startButton.setFont(new Font(startButton.getFont().getName(), startButton.getFont().getStyle(), 50)); // CHANGE FONT SIZE
        startButton.setForeground(startButtonTextColor); // SET TEXT COLOR
        startButton.setBackground(startButtonBackgroundColor); // SET BACKGROUND COLOR
        startButton.setBorderPainted(false); // REMOVE BORDER
        // REMOVE FOCUS STYLE
        startButton.setFocusPainted(false);
        startButton.setContentAreaFilled(true);
        startButton.setOpaque(true);

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
        this.add(Box.createRigidArea(new Dimension(0, 50)));
        this.add(startButton);
        topPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        counterLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // TIMER COUNTDOWN
        Timer timer = new Timer(1000, e -> {
            if(timeCountDown > 0){
                timeCountDown--;
            }
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
