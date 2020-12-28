package com.vojtechcahlik.spaceintact.levels;

import com.vojtechcahlik.spaceintact.Game;
import com.vojtechcahlik.spaceintact.weapons.DoubleBlaster;
import com.vojtechcahlik.spaceintact.enemies.Enemy1;
import com.vojtechcahlik.spaceintact.enemies.Enemy2;
import com.vojtechcahlik.spaceintact.RandomGeneratorTimer;
import com.vojtechcahlik.spaceintact.enemies.Enemy3;

public class Level3 extends Level {

    public Level3() {
        super(3, 60, new DoubleBlaster());
        generatedEnemies.add(new RandomGeneratorTimer(new Enemy1(), 3.5));
        generatedEnemies.add(new RandomGeneratorTimer(new Enemy2(), 6));
        generatedEnemies.add(new RandomGeneratorTimer(new Enemy3(), 13));
    }

    @Override
    protected void nextLevel() {
        Game.getInstance().currentLevel = new Level4();
    }

}