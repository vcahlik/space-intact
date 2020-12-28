package com.vojtechcahlik.spaceintact.screens;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import com.vojtechcahlik.spaceintact.CustomButton;
import com.vojtechcahlik.spaceintact.Game;
import com.vojtechcahlik.spaceintact.GameWindow;
import com.vojtechcahlik.spaceintact.Save;
import java.io.IOException;
import javax.imageio.ImageIO;

public class MainMenu extends Screen {
    
    private final CustomButton continueButton;
    private final CustomButton newGameButton;
    private final CustomButton controlsButton;
    private Save savedGame;
    
    public MainMenu() {
        super("/main_menu/background.png");
        continueButton = new CustomButton((Game.PANEL_SIZE_X - 270) / 2, 200, 
                "/main_menu/button_continue.png", "/main_menu/button_continue_selected.png");
        continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!continueButton.isEnabled) return;
                Game.getInstance().loadFromSave(savedGame);
                GameWindow.getInstance().showGame();                
            }
        });
        newGameButton = new CustomButton((Game.PANEL_SIZE_X - 270) / 2, 270, 
                "/main_menu/button_new_game.png", "/main_menu/button_new_game_selected.png");
        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Game.getInstance().restart();
                GameWindow.getInstance().showGame();                
            }
        });
        controlsButton = new CustomButton((Game.PANEL_SIZE_X - 270) / 2, 340, 
                "/main_menu/button_controls.png", "/main_menu/button_controls_selected.png");
        controlsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameWindow.getInstance().showControlsScreen();
            }
        });
        add(continueButton);
        add(newGameButton);
        add(controlsButton);
        checkIfCanContinue();
    }
    
    /**
     * Check whether a save exists and set the continue button accordingly.
     */
    public final void checkIfCanContinue() {
        savedGame = Game.getInstance().loadSave();
        if (savedGame == null) {
            continueButton.isEnabled = false;
            try {
                continueButton.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("/main_menu/button_continue_disabled.png"))));
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(2);
            }
        } else {
            continueButton.isEnabled = true;
            continueButton.setIcon(continueButton.defaultIcon);
        }
    }
    
}