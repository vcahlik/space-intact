package com.vojtechcahlik.spaceintact.weapons;

import com.vojtechcahlik.spaceintact.Game;
import com.vojtechcahlik.spaceintact.Player;

/**
 * Synchronizes the player's fire button with the actual weapon (which is limited by its repeat time).
 */
public abstract class PlayerWeapon extends Weapon {

    private boolean blastScheduled;
    private double cyclesUntilWeaponReady;
    public double repeatTime;
    
    public PlayerWeapon(double repeatTime, String blastIconPath) {
        super(Player.getInstance(), blastIconPath);
        this.repeatTime = repeatTime;
        blastScheduled = false;
        cyclesUntilWeaponReady = 0;
    }

    public void performActions() {
        if (cyclesUntilWeaponReady > 0) {
            cyclesUntilWeaponReady--;
        } else if (blastScheduled) {
            fire();
            resetCyclesUntilWeaponReady();
            blastScheduled = false;
        }
    }
    
    public void resetCyclesUntilWeaponReady() {
        cyclesUntilWeaponReady = Game.convertSecondsToCycles(repeatTime);
    }

    @Override
    public void requestBlast() {
        if (cyclesUntilWeaponReady < Game.convertSecondsToCycles(repeatTime) / 2) {
            blastScheduled = true;
        }
    }

}