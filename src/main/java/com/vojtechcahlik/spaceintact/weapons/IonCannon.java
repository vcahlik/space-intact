package com.vojtechcahlik.spaceintact.weapons;

import com.vojtechcahlik.spaceintact.PhysicalSprite;
import com.vojtechcahlik.spaceintact.Game;

/**
 * More powerful version of the basic blaster.
 */
public class IonCannon extends PlayerWeapon {
    
    public IonCannon() {
        super(0.5, "/ion_cannon_blast.png");
    }

    @Override
    protected void fire() {
        PhysicalSprite blast = new PhysicalSprite(0, 0, blastSpeed, blastDamage, blastHitPoints, blastIconPath);
        blast.positionX = wielder.positionX + wielder.sizeX - blast.sizeX;
        blast.positionY = wielder.positionY + (wielder.sizeY - blast.sizeY) / 2;
        Game.FRIENDLY_SPRITES.add(blast);
    }
    
}