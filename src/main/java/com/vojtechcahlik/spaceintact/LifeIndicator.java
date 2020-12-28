package com.vojtechcahlik.spaceintact;

/**
 * Displays the remaining lives.
 */
public final class LifeIndicator extends HeadUpDisplay {

    private static LifeIndicator instance = null;
    private int remainingLives;
    public static final int MARGIN_RIGHT = 8;
    public static final int MARGIN_TOP = 4;
    public static final int GAP_SIZE = 8;
    
    protected LifeIndicator() {
        for (int i = 1; i <= Player.MAX_LIVES; i++) {
            Sprite lifeSymbol = new Sprite(0, 0, "/life_symbol.png");
            lifeSymbol.positionX = Game.PANEL_SIZE_X - MARGIN_RIGHT - i * lifeSymbol.sizeX - (i - 1) * GAP_SIZE;
            lifeSymbol.positionY = MARGIN_TOP;
            SYMBOLS.add(lifeSymbol);
        }
        update();
    }
    
    public static LifeIndicator getInstance() {
        if (instance == null) {
            instance = new LifeIndicator();
        }
        return instance;
    }
    
    /**
     * Update the indicator. Must be called whenever the number of remaining lives changes.
     */
    @Override
    public void update() {
        remainingLives = Player.getInstance().getRemainingLives();
        for (Sprite lifeSymbol : SYMBOLS) {
            lifeSymbol.isVisible = SYMBOLS.indexOf(lifeSymbol) + 1 <= remainingLives;
        }
    }
    
}