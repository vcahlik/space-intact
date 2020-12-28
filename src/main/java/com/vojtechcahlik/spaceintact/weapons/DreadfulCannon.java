package com.vojtechcahlik.spaceintact.weapons;

import com.vojtechcahlik.spaceintact.PhysicalSprite;
import com.vojtechcahlik.spaceintact.Game;
import com.vojtechcahlik.spaceintact.MovableSprite;

public class DreadfulCannon extends Weapon {

    public DreadfulCannon(MovableSprite wielder) {
        super(wielder, "/dreadful_cannon_blast.png");
    }

    @Override
    protected void fire() {
        PhysicalSprite blast = new PhysicalSprite(0, 0, -1.2 * blastSpeed, blastDamage, blastHitPoints, blastIconPath);
        blast.positionX = wielder.positionX;
        blast.positionY = wielder.positionY + (wielder.sizeY - blast.sizeY) / 2;
        Game.ENEMY_SPRITES.add(blast);
    }

}