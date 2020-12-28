package com.vojtechcahlik.spaceintact.enemies;

import com.vojtechcahlik.spaceintact.PhysicalSprite;
import com.vojtechcahlik.spaceintact.RandomGeneratorTimer;
import com.vojtechcahlik.spaceintact.weapons.Weapon;

/**
 * A hostile sprite. Has the ability to shoot back at the player.
 */
public class Enemy extends PhysicalSprite {

    Weapon weapon;
    boolean weaponIsEnabled;
    RandomGeneratorTimer blastGenerator;

    public Enemy(double blastInterval, double horizontalSpeed, double damage, double hitPoints, String iconPath) {
        super(0, 0, horizontalSpeed, damage, hitPoints, iconPath);
        weaponIsEnabled = true;
        blastGenerator = new RandomGeneratorTimer(blastInterval);
        weapon = null;
    }
    
    @Override
    public void performActions() {
        super.performActions();
        if (weaponIsEnabled && weapon != null && blastGenerator.generate()) {
            weapon.requestBlast();
        }
    }
    
}