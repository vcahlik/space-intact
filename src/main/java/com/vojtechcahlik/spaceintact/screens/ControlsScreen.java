package com.vojtechcahlik.spaceintact.screens;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.vojtechcahlik.spaceintact.CustomButton;
import com.vojtechcahlik.spaceintact.Game;
import com.vojtechcahlik.spaceintact.GameWindow;

public class ControlsScreen extends Screen {
    
    private final CustomButton goBackButton;
    
    public ControlsScreen() {
        super("/controls_screen/background.png");
        goBackButton = new CustomButton((Game.PANEL_SIZE_X - 270) / 2, 320, 
                "/controls_screen/button_go_back.png", "/controls_screen/button_go_back_selected.png");
        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameWindow.getInstance().showMainMenu();
            }
        });
        add(goBackButton);
    }
    
}