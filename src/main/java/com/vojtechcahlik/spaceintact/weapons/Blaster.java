package com.vojtechcahlik.spaceintact.weapons;

import com.vojtechcahlik.spaceintact.PhysicalSprite;
import com.vojtechcahlik.spaceintact.Game;

/**
 * Basic weapon.
 */
public class Blaster extends PlayerWeapon {

    public Blaster() {
        super(0.3, "/blaster_blast.png");
        blastDamage = 1;
        blastSpeed = 2;
    }

    @Override
    protected void fire() {
        PhysicalSprite blast = new PhysicalSprite(0, 0, blastSpeed, blastDamage, blastHitPoints, blastIconPath);
        blast.positionX = wielder.positionX + wielder.sizeX - blast.sizeX;
        blast.positionY = wielder.positionY + (wielder.sizeY - blast.sizeY) / 2;
        Game.FRIENDLY_SPRITES.add(blast);
    }

}