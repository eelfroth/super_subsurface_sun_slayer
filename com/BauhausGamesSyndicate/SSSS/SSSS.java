package com.BauhausGamesSyndicate.SSSS;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.Color;

/**
 *
 * @author Benedikt Vogler
 * @author Paul Flechsig
 * @author Jacob Bauer
 */
public class SSSS extends Game {

    /**
     * @param args the command line arguments
     */
     public static void main(String[] args) {
        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = "Super Subsurface Sun Slayer";
        cfg.width = 1920;
        cfg.height = 1080;
        cfg.foregroundFPS = 0;
        cfg.backgroundFPS =0;
        cfg.vSyncEnabled = true;
        cfg.initialBackgroundColor = new Color(0,0,0,1);
        cfg.fullscreen = false;
         LwjglApplication lwjglApplication = new LwjglApplication(new SSSS(), cfg);
    }

    @Override
    public void create() {
        setScreen(new TitleScreen(this));
       
   }
}