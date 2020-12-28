package com.vojtechcahlik.spaceintact.levels;

import com.vojtechcahlik.spaceintact.Game;
import com.vojtechcahlik.spaceintact.enemies.Enemy1;
import com.vojtechcahlik.spaceintact.enemies.Enemy2;
import com.vojtechcahlik.spaceintact.RandomGeneratorTimer;
import com.vojtechcahlik.spaceintact.enemies.Enemy3;
import com.vojtechcahlik.spaceintact.enemies.Enemy4;
import com.vojtechcahlik.spaceintact.weapons.UltimateAvenger;

public class Level4 extends Level {

    public Level4() {
        super(4, 80, new UltimateAvenger());
        generatedEnemies.add(new RandomGeneratorTimer(new Enemy1(), 3));
        generatedEnemies.add(new RandomGeneratorTimer(new Enemy2(), 4));
        generatedEnemies.add(new RandomGeneratorTimer(new Enemy3(), 14));
        generatedEnemies.add(new RandomGeneratorTimer(new Enemy4(), 6));
    }

    @Override
    protected void nextLevel() {
        Game.getInstance().gameWon();
    }

}