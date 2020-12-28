package com.vojtechcahlik.spaceintact.weapons;

import com.vojtechcahlik.spaceintact.Game;
import com.vojtechcahlik.spaceintact.PhysicalSprite;

/**
 * A defensive, hi-tech ultraweapon.
 */
public class UltimateAvenger extends PlayerWeapon {

    int currentBlast;
    
    public UltimateAvenger() {
        super(0.04, "/ultimate_avenger_blast.png");
        blastDamage = 0.3;
        blastSpeed = 3;
        currentBlast = 0;
    }

    /**
     * Fire the next round. The direction of the shots changes periodically, resulting in a wide spread.
     */
    @Override
    protected void fire() {
        double speedY = 0;
        switch(currentBlast) {
            case 0:
                speedY = -7;
                currentBlast++;
                break;
            case 1:
                speedY = -5;
                currentBlast++;
                break;
            case 2:
                speedY = -3;
                currentBlast++;
                break;
            case 3:
                speedY = -1;
                currentBlast++;
                break;
            case 4:
                speedY = 1;
                currentBlast++;
                break;
            case 5:
                speedY = 3;
                currentBlast++;
                break;
            case 6:
                speedY = 5;
                currentBlast++;
                break;
            case 7:
                speedY = 7;
                currentBlast = 0;
                break; 
        }
        speedY *= 0.1 ;
        PhysicalSprite blast = new PhysicalSprite(0, 0, blastSpeed, blastDamage, blastHitPoints, blastIconPath);
        blast.positionX = wielder.positionX + wielder.sizeX - blast.sizeX;
        blast.positionY = wielder.positionY + (wielder.sizeY - blast.sizeY) / 2;
        blast.verticalSpeed = speedY;
        Game.FRIENDLY_SPRITES.add(blast);
    }

}