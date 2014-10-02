package com.BauhausGamesSyndicate.SSSS;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.Color;

/**
 *Needs libGDX v1.0 - v1.3
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
        
        //arguments
        if (args.length > 0){
            //look if contains launch parameters
            for (int i = 0; i < args.length; i++) {
                switch (args[i]) {
                    case "-fullscreen":
                    case "-f":
                        //start in fullscreen
                        cfg.fullscreen = true;
                        break;
                    case "-windowed":
                        //start in windowed mode
                        cfg.fullscreen = false;
                        break;
                    case "-w":
                        //set the width
                        cfg.width = Integer.parseInt(args[i+1]);
                        break;
                    case "-h":
                        //set the height
                        cfg.height = Integer.parseInt(args[i+1]);
                        break;
                     case "-dev":
                         //TO-DO 
                        break;    
                }
            }
        }    
        
         LwjglApplication lwjglApplication = new LwjglApplication(new SSSS(), cfg);
    }

    @Override
    public void create() {
        setScreen(new TitleScreen(this));
       
   }
}