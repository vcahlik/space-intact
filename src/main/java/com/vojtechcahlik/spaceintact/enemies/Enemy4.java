package com.vojtechcahlik.spaceintact.enemies;

import com.vojtechcahlik.spaceintact.Game;

/**
 * An enemy which will eventually attempt to ram the player.
 */
public class Enemy4 extends Enemy {

    private static final double WAIT_DURATION = 3.5;
    private int waitCycles;
    private boolean waiting;
    
    public Enemy4() {
        super(0, -0.6, 1, 9, "/sprite_enemy_level4.png");
        waitCycles = Game.convertSecondsToCycles(WAIT_DURATION);
        waiting = false;
    }
    
    @Override
    public void performActions() {
        if (!waiting && Game.PANEL_SIZE_X - positionX > sizeX + 50) waiting = true;
        if (waiting) {
            if (waitCycles > 0) {
                horizontalSpeed = 0.07;
                waitCycles--;
            } else {
                horizontalSpeed -= 0.125 ;
            }
        }
        super.performActions();
    }
    
}