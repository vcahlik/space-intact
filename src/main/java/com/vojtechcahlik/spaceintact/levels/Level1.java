package com.vojtechcahlik.spaceintact.levels;

import com.vojtechcahlik.spaceintact.enemies.Enemy1;
import com.vojtechcahlik.spaceintact.Game;
import com.vojtechcahlik.spaceintact.RandomGeneratorTimer;
import com.vojtechcahlik.spaceintact.enemies.Enemy2;

public class Level1 extends Level {

    public Level1() {
        super(1, 30, null);
        generatedEnemies.add(new RandomGeneratorTimer(new Enemy1(), 4));
    }

    @Override
    protected void nextLevel() {
        Game.getInstance().currentLevel = new Level2();
    }

}