package com.vojtechcahlik.spaceintact;

import com.vojtechcahlik.spaceintact.weapons.Blaster;
import com.vojtechcahlik.spaceintact.weapons.PlayerWeapon;

/**
 * Player's sprite.
 */
public final class Player extends PhysicalSprite {

    private static Player instance = null;
    public static final double MOVEMENT_SPEED = 4;
    public static final int MAX_LIVES = 5;
    public PlayerWeapon weapon;
    private int remainingLives;
    private int cyclesUntilVulnerable;
    private static final double INVINCIBILITY_DURATION_AFTER_HIT = 2;
    private static final double INVINCIBILITY_BLINK_DURATION = 0.1;
    
    protected Player() {
        super(0, 0, 0, 1000, 0, "/sprite_player_new.png");
        cyclesUntilVulnerable = 0;
    }
    
    public static Player getInstance() {
        if (instance == null) {
            instance = new Player();
        }
        return instance;
    }
    
    /**
     * Reset the player's state to default.
     */
    public void reset() {
        isInvincible = false;
        isVisible = true;
        positionX = 4;
        positionY = (Game.PANEL_SIZE_Y - sizeY) / 2;
        setRemainingLives(MAX_LIVES);
        weapon = new Blaster();
    }
    
    @Override
    public void performActions() {
        // Move only if not going past the game canvas border.
        if ((positionY + verticalSpeed >= 0) && (positionY + verticalSpeed <= Game.PANEL_SIZE_Y - sizeY)) {
            super.performActions();
        }
        if (weapon != null) {
            if (Game.getInstance().keyFireIsPressed) weapon.requestBlast();
            weapon.performActions();
        }
        // If invincible, reduce the time until vulnerable again and blink.
        if (isInvincible) {
            if (cyclesUntilVulnerable > 0) {
                cyclesUntilVulnerable--;
                if (cyclesUntilVulnerable % Game.convertSecondsToCycles(INVINCIBILITY_BLINK_DURATION) == 0) {
                    if (isVisible) isVisible = false;
                    else isVisible = true;
                }
            } else {
                isInvincible = false;
                isVisible = true;
            }
        }
    }

    @Override
    public void die() {
        Game.getInstance().gameOver();
    }

    @Override
    protected void performCollisionWith(PhysicalSprite collidedSprite) {
        // Don't affect the collided sprites while invincible, except if the collided sprite was Aid.
        if (!isInvincible || collidedSprite instanceof Aid) {
            collidedSprite.takeDamage(this.damage);
        }
        if (!this.isInvincible && collidedSprite.damage > 0) {
            setRemainingLives(getRemainingLives() - 1);
            // Become invincible for a short time after taking a hit.
            this.isInvincible = true;
            this.cyclesUntilVulnerable = Game.convertSecondsToCycles(INVINCIBILITY_DURATION_AFTER_HIT);            
        }
        if (getRemainingLives() > 0) {
            // Remove the collided sprite (but leave it on screen if this was the player's last life).
            if (collidedSprite.hitPoints < 0) {
                collidedSprite.die();
            }
        } else {
            this.die();
        }
    }

    public int getRemainingLives() {
        return remainingLives;
    }

    public void setRemainingLives(int remainingLives) {
        if (remainingLives > MAX_LIVES) {
            throw new IllegalArgumentException();
        }
        this.remainingLives = remainingLives;
        //must notify the LifeIndicator
        LifeIndicator.getInstance().update();
    }

}