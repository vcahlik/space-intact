package com.vojtechcahlik.spaceintact;

/**
 * A sprite that helps the player somehow.
 */
public class Aid extends PhysicalSprite {
    
    public Aid(double horizontalSpeed, String iconPath) {
        super(0, 0, horizontalSpeed, 0, 0, iconPath);
    }
}