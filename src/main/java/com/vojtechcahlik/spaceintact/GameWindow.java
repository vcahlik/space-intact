package com.vojtechcahlik.spaceintact;

import com.vojtechcahlik.spaceintact.screens.PauseMenu;
import com.vojtechcahlik.spaceintact.screens.MainMenu;
import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import com.vojtechcahlik.spaceintact.screens.ControlsScreen;

/**
 * A window with a cardLayout JPanel, where the game and all the menus are cards.
 */
public class GameWindow extends JFrame {
    
    private static GameWindow instance = null;
    
    private final MainMenu mainMenu;
    private final PauseMenu pauseMenu;
    private final ControlsScreen controlsScreen;
    private final CardLayout cardLayout;
    private final JPanel cards;

    protected GameWindow() {
        mainMenu = new MainMenu();
        pauseMenu = new PauseMenu();
        controlsScreen = new ControlsScreen();
        cardLayout = new CardLayout();
        cards = new JPanel(cardLayout);
        cards.add(mainMenu, "MAIN_MENU");
        cards.add(pauseMenu, "PAUSE_MENU");
        cards.add(controlsScreen, "CONTROLS_SCREEN");
        cards.add(Game.getInstance(), "GAME");
        getContentPane().add(cards);
        pack();
        InitUI();
        cardLayout.show(cards, "MAIN_MENU");
    }
    
    public static GameWindow getInstance() {
        if (instance == null) {
            instance = new GameWindow();
        }
        return instance;
    }
    
    private void InitUI() {
        setTitle("Space Intact");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }
    
    /**
     * Switch to the mainMenu JPanel.
     */
    public void showMainMenu() {
        mainMenu.checkIfCanContinue();
        cardLayout.show(cards, "MAIN_MENU");
        mainMenu.requestFocusInWindow();
    }
    
    /**
     * Switch to the pauseMenu JPanel.
     */
    public void showPauseMenu() {
        cardLayout.show(cards, "PAUSE_MENU");
        pauseMenu.requestFocusInWindow();
    }
    
    /**
     * Switch to the controlsScreen JPanel.
     */
    public void showControlsScreen() {
        cardLayout.show(cards, "CONTROLS_SCREEN");
        controlsScreen.requestFocusInWindow();
    }
    
    /**
     * Switch to the game JPanel.
     */
    public void showGame() {
        cardLayout.show(cards, "GAME");
        Game.getInstance().requestFocusInWindow();
    }
    
}