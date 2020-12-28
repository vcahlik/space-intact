package com.vojtechcahlik.spaceintact;

import com.vojtechcahlik.spaceintact.weapons.PlayerWeapon;

/**
 * Gives the player a new weapon (on collision).
 */
public class GiftWeapon extends Aid {

    PlayerWeapon weapon;
    
    public GiftWeapon(PlayerWeapon weapon) {
        super(-1.2, "/gift.png");
        this.weapon = weapon;
    }

    @Override
    public void die() {
        Player.getInstance().weapon = this.weapon;
        super.die();
    }
    
}