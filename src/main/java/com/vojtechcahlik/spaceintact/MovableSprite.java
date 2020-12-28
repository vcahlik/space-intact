package com.vojtechcahlik.spaceintact;

/**
 * A sprite whose position changes by its speed on every cycle.
 */
public class MovableSprite extends Sprite {

    public double horizontalSpeed;
    public double verticalSpeed;
    
    public MovableSprite(double positionX, double positionY, double horizontalSpeed, String iconPath) {
        super(positionX, positionY, iconPath);
        this.horizontalSpeed = horizontalSpeed;
        this.verticalSpeed = 0;
    }
    
    public void performActions() {
        positionX += horizontalSpeed;
        positionY += verticalSpeed;
    }

}