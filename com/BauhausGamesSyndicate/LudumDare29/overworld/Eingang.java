/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.BauhausGamesSyndicate.LudumDare29.overworld;

import com.BauhausGamesSyndicate.LudumDare29.GameScreen;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 *
 * @author Benedikt Vogler
 * @author Paul Flechsig
 * @author Jacob Bauer
 */
public class Eingang{
    private final int x = 2000;
    private final int width = 200;
    
    public void render(GameScreen gs){
        ShapeRenderer sh = gs.getShapeRenderer();
        sh.begin(ShapeRenderer.ShapeType.Filled);
        sh.rect(x, 0, width, 400);
        sh.end();
    }

    public int getX() {
        return x;
    }

    public int getWidth() {
        return width;
    }
    
}
