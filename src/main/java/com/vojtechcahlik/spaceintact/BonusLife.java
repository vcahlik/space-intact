package com.vojtechcahlik.spaceintact;

public class BonusLife extends Aid {
    
    public BonusLife() {
        super(-0.8, "/life_symbol.png");
    }

    @Override
    public void die() {
        if (Player.getInstance().getRemainingLives() < Player.MAX_LIVES) {
            Player.getInstance().setRemainingLives(Player.getInstance().getRemainingLives() + 1);
        }
        super.die();
    }
    
}