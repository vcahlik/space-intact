package com.vojtechcahlik.spaceintact.enemies;

import com.vojtechcahlik.spaceintact.weapons.LameThrower;

/**
 * Basic enemy. Shoots at the player from time to time.
 */
public class Enemy1 extends Enemy {

    public Enemy1() {
        super(8, -1, 1, 3, "/sprite_enemy_level1.png");
        weapon = new LameThrower(this);
    }

}