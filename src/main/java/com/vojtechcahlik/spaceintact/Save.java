package com.vojtechcahlik.spaceintact;

import java.io.Serializable;
import com.vojtechcahlik.spaceintact.levels.Level;
import com.vojtechcahlik.spaceintact.weapons.PlayerWeapon;

/**
 * Stores information about current level and the player. Can be serialized into a save file later.
 */
public class Save implements Serializable {
    
    public Class level;
    public Class playerWeapon;
    public int remainingLives;

    public Save(Level level, PlayerWeapon weapon, int remainingLives) {
        this.level = level.getClass();
        this.playerWeapon = weapon.getClass();
        this.remainingLives = remainingLives;
    }
    
}