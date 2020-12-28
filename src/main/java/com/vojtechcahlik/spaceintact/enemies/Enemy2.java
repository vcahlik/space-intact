package com.vojtechcahlik.spaceintact.enemies;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import com.vojtechcahlik.spaceintact.Game;

/**
 * A boring and a bit tougher enemy.
 */
public class Enemy2 extends Enemy {

    private static BufferedImage form1;
    private static BufferedImage form2;
    private static final double FORM_DURATION = 1.2;
    
    static {
        try {
            form1 = ImageIO.read(Enemy2.class.getResource("/sprite_enemy_level2a.png"));
            form2 = ImageIO.read(Enemy2.class.getResource("/sprite_enemy_level2b.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(2);
        }
    }
    
    public Enemy2() {
        super(0, -0.8, 2, 5, "/sprite_enemy_level2a.png");
        icon = form1;
    }

    @Override
    public void performActions() {
        super.performActions();
        if (Game.cycle % Game.convertSecondsToCycles(FORM_DURATION) == 0) {
            if (icon == form1) icon = form2;
            else icon = form1;
        }
    }
    
}