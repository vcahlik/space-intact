package com.vojtechcahlik.spaceintact.levels;

import com.vojtechcahlik.spaceintact.enemies.Enemy1;
import com.vojtechcahlik.spaceintact.enemies.Enemy2;
import com.vojtechcahlik.spaceintact.Game;
import com.vojtechcahlik.spaceintact.RandomGeneratorTimer;
import com.vojtechcahlik.spaceintact.weapons.IonCannon;

public class Level2 extends Level {

    public Level2() {
        super(2, 40, new IonCannon());
        generatedEnemies.add(new RandomGeneratorTimer(new Enemy1(), 3.5));
        generatedEnemies.add(new RandomGeneratorTimer(new Enemy2(), 8));
    }

    @Override
    protected void nextLevel() {
        Game.getInstance().currentLevel = new Level3();
    }

}