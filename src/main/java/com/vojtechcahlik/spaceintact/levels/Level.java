package com.vojtechcahlik.spaceintact.levels;

import java.util.HashSet;
import java.util.Random;
import com.vojtechcahlik.spaceintact.BonusLife;
import com.vojtechcahlik.spaceintact.enemies.Enemy;
import com.vojtechcahlik.spaceintact.Game;
import com.vojtechcahlik.spaceintact.RandomGeneratorTimer;
import com.vojtechcahlik.spaceintact.GiftWeapon;
import com.vojtechcahlik.spaceintact.Player;
import com.vojtechcahlik.spaceintact.weapons.PlayerWeapon;

/**
 * Specify the level's duration, enemies, special events, etc.
 */
public abstract class Level {
    
    Random random = new Random();
    private int cycle;
    private PlayerWeapon giftWeapon;
    private final int bonusLifeDropCycle;
    protected final HashSet<RandomGeneratorTimer> generatedEnemies = new HashSet<>();
    protected Level nextLevel;

    public Level(int levelNumber, int duration, PlayerWeapon giftWeapon) {
        Game.getInstance().notification.show("LEVEL " + levelNumber, 3);
        this.cycle = Game.convertSecondsToCycles(duration);
        //If the player's health is below maximum, a bonus life will be dropped sometimes during this level.
        if (Player.getInstance().getRemainingLives() < Player.MAX_LIVES) {
            this.bonusLifeDropCycle = random.nextInt(cycle);
        } else {
            this.bonusLifeDropCycle = Integer.MAX_VALUE;
        }
        this.giftWeapon = giftWeapon;
        //Drop the gift weapon (if specified and not already wielded).
        if (giftWeapon != null && (Player.getInstance().weapon == null || !giftWeapon.getClass().equals(Player.getInstance().weapon.getClass()))) dropGift();
    }
    
    public void performActions() {
        if (cycle == bonusLifeDropCycle && Player.getInstance().getRemainingLives() < Player.MAX_LIVES) dropBonusLife();
        if (cycle <= 0) nextLevel();
        generateEnemies();
        cycle--;
    }
    
    /**
     * Specify what happens when this level is over.
     */
    abstract protected void nextLevel();
    
    protected void generateEnemies() {
        for (RandomGeneratorTimer generatedEnemy : generatedEnemies) {
            if (generatedEnemy.generate()) {
                try {
                    Enemy enemy = (Enemy)generatedEnemy.objectType.newInstance();
                    enemy.positionX = Game.PANEL_SIZE_X;
                    enemy.positionY = random.nextInt(Game.PANEL_SIZE_Y - enemy.sizeY);
                    Game.ENEMY_SPRITES.add(enemy);
                } catch (InstantiationException|IllegalAccessException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
    
    private void dropGift() {
        GiftWeapon gift = new GiftWeapon(giftWeapon);
        gift.positionX = Game.PANEL_SIZE_X;
        gift.positionY = random.nextInt(Game.PANEL_SIZE_Y - gift.sizeY);
        Game.ITEM_SPRITES.add(gift);
        giftWeapon = null;
    }

    private void dropBonusLife() {
        BonusLife life = new BonusLife();
        life.positionX = Game.PANEL_SIZE_X;
        life.positionY = random.nextInt(Game.PANEL_SIZE_Y - life.sizeY);
        Game.ITEM_SPRITES.add(life);
        giftWeapon = null;
    }
    
}