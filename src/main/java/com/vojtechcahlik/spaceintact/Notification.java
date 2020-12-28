package com.vojtechcahlik.spaceintact;

import java.awt.Color;
import javax.swing.JLabel;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A JLabel that can show messages with specified text and duration.
 */
public class Notification extends JLabel {
    
    private Timer timer;

    public Notification() {
        setFont(getFont().deriveFont(16f));
        setForeground(Color.white);
        setVisible(false);
    }
    
    public void show(String text, double duration) {
        if (timer != null) timer.cancel();
        timer = new Timer();
        setText(text);
        setVisible(true);
        // Wait and eventually invoke the run() method of the HideNotification class.
        timer.schedule(new HideNotificationTask(), (long)duration * 1000);
    }

    private class HideNotificationTask extends TimerTask {
        @Override
        public void run() {
            setVisible(false);
        }
    }
    
}