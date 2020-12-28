package com.vojtechcahlik.spaceintact.screens;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * A screen that covers the whole program window. Virtually anything except the game canvas.
 */
public class Screen extends JPanel {
    
    private BufferedImage background;

    public Screen(String backgroundPath) {
        setLayout(null);
        try {
            background = ImageIO.read(getClass().getResource(backgroundPath));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(2);
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, null);
    }
    
}