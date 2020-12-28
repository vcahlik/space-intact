package com.vojtechcahlik.spaceintact;

import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * A JButton that uses a custom graphical skin.
 * Uses an ImageIcon as a background image.
 */
public class CustomButton extends JButton {
    
    public ImageIcon defaultIcon;
    public ImageIcon hoverIcon;
    public boolean isEnabled;

    public CustomButton(int positionX, int positionY, String defaultIconPath, String hoverIconPath) {
        isEnabled = true;
        try {
            defaultIcon = new ImageIcon(ImageIO.read(getClass().getResource(defaultIconPath)));
            hoverIcon = new ImageIcon(ImageIO.read(getClass().getResource(hoverIconPath)));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(2);
        }
        setIcon(defaultIcon);
        setOpaque(false);
        setContentAreaFilled(false);
        setBorder(BorderFactory.createEmptyBorder());
        setFocusPainted(false);
        setLocation(positionX, positionY);
        setSize(getPreferredSize());
        //React to mouse hover
        addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                if (isEnabled) {
                    setIcon(hoverIcon);
                    setCursor(new Cursor(Cursor.HAND_CURSOR));                    
                }
            }
            public void mouseExited(MouseEvent e) {
                if (isEnabled) {
                    setIcon(defaultIcon);
                }
            }
        });
    }

}