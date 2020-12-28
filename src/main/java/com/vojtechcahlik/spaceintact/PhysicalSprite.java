package com.vojtechcahlik.spaceintact;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * A sprite that can collide with other PhysicalSprites.
 */
public class PhysicalSprite extends MovableSprite {
    
    public double damage;
    public double hitPoints;
    public boolean isInvincible;
    
    public PhysicalSprite(double positionX, double positionY, double horizontalSpeed, double damage, double hitPoints, String iconPath) {
        super(positionX, positionY, horizontalSpeed, iconPath);
        this.damage = damage;
        this.hitPoints = hitPoints;
        this.isInvincible = false;
    }
    
    public void takeDamage(double damage) {
        if (!isInvincible) hitPoints -= damage;
    }
    
    /**
     * Remove the sprite from the game canvas after it dies.
     */
    public void die() {
        Game.getInstance().removeSprite(this);
    }
    
/**
 * Check if the sprite is in collision with other sprites.
 * @param lists - a list of sprites to check - eg. enemy sprites.
 */
    public void checkCollisionWithSpriteInLists(CopyOnWriteArrayList<? extends PhysicalSprite> ... lists) {
        for (CopyOnWriteArrayList<? extends PhysicalSprite> list : lists) {
            for (PhysicalSprite sprite : list) {
                checkCollisionWithSprite(sprite);
            }
        }
    }
    
    /**
     * Check if sprite crashed to the given sprite.
     * @param sprite
     */
    public void checkCollisionWithSprite(PhysicalSprite sprite) {
        if (this.positionY + this.sizeY > sprite.positionY 
                && this.positionY < sprite.positionY + sprite.sizeY
                && this.positionX + this.sizeX > sprite.positionX
                && this.positionX < sprite.positionX + sprite.sizeX) {
            performCollisionWith(sprite);
        }
    }
    
    /**
     * Perform the collision logic.
     * @param collidedSprite 
     */
    protected void performCollisionWith(PhysicalSprite collidedSprite) {
        this.takeDamage(collidedSprite.damage);
        collidedSprite.takeDamage(this.damage);
        if (this.hitPoints < 0) {
            this.die();
        }
        if (collidedSprite.hitPoints < 0) {
            collidedSprite.die();
        }
    }

}