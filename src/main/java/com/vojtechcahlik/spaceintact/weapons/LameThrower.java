package com.vojtechcahlik.spaceintact.weapons;

import com.vojtechcahlik.spaceintact.PhysicalSprite;
import com.vojtechcahlik.spaceintact.Game;
import com.vojtechcahlik.spaceintact.MovableSprite;
/**
 * A basic enemy weapon.
 */
public class LameThrower extends Weapon {

    public LameThrower(MovableSprite wielder) {
        super(wielder, "/lame_thrower_blast.png");
    }

    @Override
    protected void fire() {
        PhysicalSprite blast = new PhysicalSprite(0, 0, -1 * blastSpeed, blastDamage, blastHitPoints, blastIconPath);
        blast.positionX = wielder.positionX;
        blast.positionY = wielder.positionY + (wielder.sizeY - blast.sizeY) / 2;
        Game.ENEMY_SPRITES.add(blast);
    }

}