package com.vojtechcahlik.spaceintact.screens;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import com.vojtechcahlik.spaceintact.CustomButton;
import com.vojtechcahlik.spaceintact.Game;
import com.vojtechcahlik.spaceintact.GameWindow;

public class PauseMenu extends Screen {
    
    private final CustomButton resumeButton;
    private final CustomButton restartButton;
    private final CustomButton saveAndQuitButton;
    
    public PauseMenu() {
        super("/pause_menu/background.png");
        addKeyListener(new InputAdapter());
        resumeButton = new CustomButton((Game.PANEL_SIZE_X - 270) / 2, 130, 
                "/pause_menu/button_resume.png", "/pause_menu/button_resume_selected.png");
        resumeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Game.getInstance().resume();
                GameWindow.getInstance().showGame();
            }
        });
        restartButton = new CustomButton((Game.PANEL_SIZE_X - 270) / 2, 200, 
                "/pause_menu/button_restart.png", "/pause_menu/button_restart_selected.png");
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Game.getInstance().restart();
                GameWindow.getInstance().showGame();
            }
        });
        saveAndQuitButton = new CustomButton((Game.PANEL_SIZE_X - 270) / 2, 270, 
                "/pause_menu/button_save_and_quit.png", "/pause_menu/button_save_and_quit_selected.png");
        saveAndQuitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Game.getInstance().save();
            }
        });
        add(resumeButton);
        add(restartButton);
        add(saveAndQuitButton);
    }
    
    private class InputAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();   
            if (key == KeyEvent.VK_ESCAPE) {
                Game.getInstance().resume();
                GameWindow.getInstance().showGame();
            }
        }
        
    }
    
}