package com.vojtechcahlik.spaceintact.enemies;

import com.vojtechcahlik.spaceintact.Game;
import com.vojtechcahlik.spaceintact.weapons.DreadfulCannon;

/**
 * An enemy that shoots and moves.
 */
public class Enemy3 extends Enemy {
    
    private int direction;
    private static final double DIRECTION_CHANGES_DURATION_TIME = 4;
    private int cycleDirectionChanges;
    private final int directionChangesDuration;
    
    public Enemy3() {
        super(2.5, -0.7, 2, 4, "/sprite_enemy_level3.png");
        weapon = new DreadfulCannon(this);
        directionChangesDuration = Game.convertSecondsToCycles(DIRECTION_CHANGES_DURATION_TIME);
        cycleDirectionChanges = 0;
    }

    @Override
    public void performActions() {
        super.performActions();
        if (cycleDirectionChanges < directionChangesDuration * 0.25
                && positionY > 0) {
            verticalSpeed = -0.7;
        } else if (cycleDirectionChanges >= directionChangesDuration * 0.5 && cycleDirectionChanges < directionChangesDuration * 0.75
                && positionY < Game.PANEL_SIZE_Y) {
            verticalSpeed = 0.7;
        } else {
            verticalSpeed = 0;
        }
        cycleDirectionChanges++;
        if (cycleDirectionChanges >= directionChangesDuration) cycleDirectionChanges = 0;
    }
    
}