package com.vojtechcahlik.spaceintact.weapons;

import com.vojtechcahlik.spaceintact.MovableSprite;

public abstract class Weapon {

    public double blastDamage;
    public double blastSpeed;
    public double blastHitPoints;
    protected MovableSprite wielder;
    protected String blastIconPath;
    
    public Weapon(MovableSprite wielder, String blastIconPath) {
        this.blastDamage = 2;
        this.blastSpeed = 3;
        this.blastHitPoints = 0;
        this.wielder = wielder;
        this.blastIconPath = blastIconPath;
    }
    
    public void requestBlast() {
        fire();
    }
    
    abstract protected void fire();
    
}