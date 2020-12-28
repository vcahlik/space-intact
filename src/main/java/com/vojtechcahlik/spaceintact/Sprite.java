package com.vojtechcahlik.spaceintact;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * A character/picture on the game canvas.
 */
public class Sprite {

    public boolean isVisible;
    public int sizeX;
    public int sizeY;
    public double positionX;
    public double positionY;
    public BufferedImage icon;

    public Sprite(double positionX, double positionY, String iconPath) {
        this.isVisible = true;
        this.positionX = positionX;
        this.positionY = positionY;

        try {
           icon = ImageIO.read(getClass().getResource(iconPath));
           this.sizeX = icon.getWidth();
           this.sizeY = icon.getHeight();
        } catch (IOException e) {
             e.printStackTrace();
             System.exit(2);
        }
       
    }
    
}