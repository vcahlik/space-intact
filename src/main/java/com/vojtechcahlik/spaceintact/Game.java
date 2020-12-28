package com.vojtechcahlik.spaceintact;

import com.vojtechcahlik.spaceintact.levels.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Random;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.swing.JPanel;
import javax.swing.Timer;
import com.vojtechcahlik.spaceintact.weapons.PlayerWeapon;

public class Game extends JPanel implements ActionListener {

    private static Game instance = null;
    
    public static final int PANEL_SIZE_X = 600;
    public static final int PANEL_SIZE_Y = 450;
    public static final int CYCLE_TIME = 20;
    public static int cycle;
    private final Timer gameTimer;
    public Level currentLevel;
    
    //lists that hold all related sprites
    public static final CopyOnWriteArrayList<MovableSprite> STAR_SPRITES = new CopyOnWriteArrayList<>();
    public static final CopyOnWriteArrayList<PhysicalSprite> FRIENDLY_SPRITES = new CopyOnWriteArrayList<>();
    public static final CopyOnWriteArrayList<PhysicalSprite> ITEM_SPRITES = new CopyOnWriteArrayList<>();
    public static final CopyOnWriteArrayList<PhysicalSprite> ENEMY_SPRITES = new CopyOnWriteArrayList<>();
    
    public boolean keyFireIsPressed;
    public LifeIndicator lifeIndicator;
    private final Random random = new Random();
    private final RandomGeneratorTimer starGenerator;
    public final Notification notification;
    private boolean pauseMenuEnabled;
    
    protected Game() {
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(PANEL_SIZE_X, PANEL_SIZE_Y));
        setFocusable(true);
        
        cycle = 0;
        gameTimer = new Timer(CYCLE_TIME, this);
        addKeyListener(new InputAdapter());
        starGenerator = new RandomGeneratorTimer(2);
        prepareBackground();
        
        notification = new Notification();
        add(notification);
    }
    
    public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }
    
    //Gets called by the system when rendering the window.
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawAllSpritesFromLists(g, STAR_SPRITES, ITEM_SPRITES, ENEMY_SPRITES, FRIENDLY_SPRITES, LifeIndicator.getInstance().SYMBOLS);
        if (Player.getInstance().isVisible) {
            g.drawImage(Player.getInstance().icon, (int)Player.getInstance().positionX, (int)Player.getInstance().positionY, null);
        }
        Toolkit.getDefaultToolkit().sync();
    }
    
    /**
     * Start a new game.
     */
    public void restart() {
        pauseMenuEnabled = true;
        keyFireIsPressed = false;
        FRIENDLY_SPRITES.clear();
        ITEM_SPRITES.clear();
        ENEMY_SPRITES.clear();
        Player.getInstance().reset();
        cycle = 0;
        currentLevel = new Level1();
        gameTimer.start();
    }
    
    /**
     * Start a new game using a save.
     * @param save
     */
    public void loadFromSave(Save save) {
        try {
            pauseMenuEnabled = true;
            keyFireIsPressed = false;
            FRIENDLY_SPRITES.clear();
            ITEM_SPRITES.clear();
            ENEMY_SPRITES.clear();
            Player.getInstance().reset();
            cycle = 0;
            Player.getInstance().weapon = (PlayerWeapon)save.playerWeapon.newInstance();
            Player.getInstance().setRemainingLives(save.remainingLives);
            currentLevel = (Level)save.level.newInstance();
            gameTimer.start();
        } catch (InstantiationException|IllegalAccessException ex) {
            ex.printStackTrace();
        }
    }
    
    public void pause() {
        gameTimer.stop();
    }
    
    public void resume() {
        gameTimer.start();
        Player.getInstance().verticalSpeed = 0;
        keyFireIsPressed = false;
    }
    
    public void gameOver() {
        pauseMenuEnabled = false;
        notification.show("GAME OVER", 4);
        gameTimer.stop();
        Player.getInstance().isVisible = true;
        new java.util.Timer().schedule(new ReturnToMainMenuTask(), 4000);
    }
    
    public void gameWon() {
        pauseMenuEnabled = false;
        notification.show("YOU WIN", 4);
        gameTimer.stop();
        Player.getInstance().isVisible = true;
        new java.util.Timer().schedule(new ReturnToMainMenuTask(), 4000);
    }
    
    /**
     * Attempt to save the current position in game.
     */
    public void save() {
        Save save = new Save(Game.getInstance().currentLevel, Player.getInstance().weapon, Player.getInstance().getRemainingLives());
        try {
            FileOutputStream fos = new FileOutputStream("save.bin");
            try (ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                oos.writeObject(save);
            }
            Game.getInstance().pause();
            GameWindow.getInstance().showMainMenu();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    /**
     * Attempt to load a saved game.
     * @return an instance of Save on success, or null if an error occured
     */
    public Save loadSave() {
        Save save = null;
        try {
            FileInputStream fis = new FileInputStream("save.bin");
            ObjectInputStream ois = new ObjectInputStream(fis);
            save = (Save)ois.readObject();
        } catch (IOException|ClassNotFoundException ex) {
        }
        return save;
    }
    
    /**
     * Perform the game logic.
     */
    private void nextCycle() {
        cycle++;
        generateBackground();
        currentLevel.performActions();
        performSpriteActions();
        checkCollisions();
        repaint();
    }
    
    /**
     * Call the performActions() on all relevant sprites (gets called on every cycle).
     */
    private void performSpriteActions() {
        Player.getInstance().performActions();
        performActionsOfSpritesInLists(STAR_SPRITES, ITEM_SPRITES, FRIENDLY_SPRITES, ENEMY_SPRITES);
    }
    
    /**
     * Pre-generate the background.
     */
    private void prepareBackground() {
        for (int x = 1; x < PANEL_SIZE_X; x++) {
            if (random.nextInt(15) == 0) {
                MovableSprite star = new MovableSprite(x, 0, -1 * (0.2 * random.nextDouble() + 0.1), "/star.png");
                star.positionY = random.nextInt(PANEL_SIZE_Y - star.sizeY);
                STAR_SPRITES.add(star);
            }
        }
    }
    
    private void generateBackground() {
        if (starGenerator.generate()) {
            MovableSprite star = new MovableSprite(PANEL_SIZE_X, 0, -1 * (0.2 * random.nextDouble() + 0.1), "/star.png");
            star.positionY = random.nextInt(PANEL_SIZE_Y - star.sizeY);
            STAR_SPRITES.add(star);
        }
    }
    
    /**
     * Check whether a relevant collision occured.
     */
    private void checkCollisions() {
        Player.getInstance().checkCollisionWithSpriteInLists(ENEMY_SPRITES, ITEM_SPRITES);
        for (PhysicalSprite friendlySprite : FRIENDLY_SPRITES) {
            friendlySprite.checkCollisionWithSpriteInLists(ENEMY_SPRITES);
        }
    }
    
    /**
     * Call performActions() on all relevant sprites and delete them if they are gone from the game canvas.
     * @param lists
     */
    private void performActionsOfSpritesInLists(CopyOnWriteArrayList<? extends MovableSprite> ... lists) {
        for (CopyOnWriteArrayList<? extends MovableSprite> list : lists) {
            for (MovableSprite sprite : list) {
                sprite.performActions();
                if (sprite.positionX + sprite.horizontalSpeed + sprite.sizeX < 0
                        || sprite.positionX + sprite.horizontalSpeed > PANEL_SIZE_X
                        || sprite.positionY + sprite.verticalSpeed + sprite.sizeY < 0
                        || sprite.positionY + sprite.verticalSpeed > PANEL_SIZE_Y) {
                    list.remove(sprite);
                }
            }
        }
    }
    
    /**
     * Show the sprites on the game canvas.
     * @param g
     * @param lists 
     */
    private void drawAllSpritesFromLists(Graphics g, List<? extends Sprite> ... lists) {
        for (List<? extends Sprite> list : lists) {
            for (Sprite sprite : list) {
                if (sprite.isVisible) {
                    g.drawImage(sprite.icon, (int)sprite.positionX, (int)sprite.positionY, null);
                }
            }
        }
    }
    
    /**
     * Delete a sprite from all lists.
     * @param sprite - sprite whose reference should be removed
     */
    public void removeSprite(Sprite sprite) {
        removeSpriteFromLists(sprite, STAR_SPRITES, ITEM_SPRITES, FRIENDLY_SPRITES, ENEMY_SPRITES);
    }
    
    private void removeSpriteFromLists(Sprite sprite, CopyOnWriteArrayList<? extends Sprite> ... lists) {
        for (CopyOnWriteArrayList<? extends Sprite> list : lists) {
            for (Sprite candidate : list) {
                if (sprite.equals(candidate)) {
                    list.remove(sprite);
                    return;
                }
            }
        }
    }
    
    //Called by the game timer.
    @Override
    public void actionPerformed(ActionEvent e) {
        nextCycle();
    }
    
    /**
     * Determine how many cycles will pass within the given time.
     * @param time - time in seconds
     * @return int - number of cycles
     */
    public static int convertSecondsToCycles(double time) {
        return (int)(time / CYCLE_TIME * 1000);
    }

    // Triggered after gameOver() or gameWon().
    private class ReturnToMainMenuTask extends TimerTask {
        @Override
        public void run() {
            GameWindow.getInstance().showMainMenu();
            pauseMenuEnabled = true;
        }
    }
    
    /**
     * Detect the user input. Panel must have focus in order for this to work.
     */
    private class InputAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_UP) Player.getInstance().verticalSpeed = -1 * Player.MOVEMENT_SPEED;
            if (key == KeyEvent.VK_DOWN) Player.getInstance().verticalSpeed = Player.MOVEMENT_SPEED;        
            if (key == KeyEvent.VK_SPACE) keyFireIsPressed = true;
        }
        
        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN) Player.getInstance().verticalSpeed = 0;
            if (key == KeyEvent.VK_SPACE) keyFireIsPressed = false;
            if (key == KeyEvent.VK_ESCAPE && pauseMenuEnabled) {
                pause();
                GameWindow.getInstance().showPauseMenu();
            }
        }
        
    }
    
}