package com.BauhausGamesSyndicate.LudumDare29;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

/**
 *
 * @author Benedikt Vogler
 */
public class LudumDare29 extends Game {

    /**
     * @param args the command line arguments
     */
     public static void main(String[] args) {
        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = "hello-world";
        cfg.width = 1600;
        cfg.height = 900;
        cfg.foregroundFPS = 0;
        cfg.backgroundFPS =0;
        cfg.vSyncEnabled = false;
        
         LwjglApplication lwjglApplication = new LwjglApplication(new LudumDare29(), cfg);
    }

    @Override
    public void create() {
        setScreen(new GameScreen());
   }
    
}