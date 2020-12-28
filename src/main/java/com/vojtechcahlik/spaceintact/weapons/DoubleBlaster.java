package com.vojtechcahlik.spaceintact.weapons;

import com.vojtechcahlik.spaceintact.PhysicalSprite;
import com.vojtechcahlik.spaceintact.Game;

public class DoubleBlaster extends PlayerWeapon {

    public DoubleBlaster() {
        super(0.2, "/blaster_blast.png");
        blastDamage = 0.75;
        blastSpeed = 2;
    }

    @Override
    protected void fire() {
        PhysicalSprite blast1 = new PhysicalSprite(0, 0, blastSpeed, blastDamage, blastHitPoints, blastIconPath);
        blast1.positionX = wielder.positionX + wielder.sizeX - blast1.sizeX;
        blast1.positionY = wielder.positionY + (wielder.sizeY - blast1.sizeY) / 2 - 8;
        Game.FRIENDLY_SPRITES.add(blast1);
        
        PhysicalSprite blast2 = new PhysicalSprite(0, 0, blastSpeed, blastDamage, blastHitPoints, blastIconPath);
        blast2.positionX = wielder.positionX + wielder.sizeX - blast2.sizeX;
        blast2.positionY = wielder.positionY + (wielder.sizeY - blast2.sizeY) / 2 + 8;
        Game.FRIENDLY_SPRITES.add(blast2);
    }

}
