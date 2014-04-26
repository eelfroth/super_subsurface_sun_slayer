/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.BauhausGamesSyndicate.LudumDare29;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 *
 * @author Benedikt Vogler
 */
public class Tile {
   private Color color;
   public static final int WIDTH = 20;
   public static final int HEIGHT =20;

    public Tile(Color color) {
        this.color = color;
    }


    void render(ShapeRenderer sh, int xPos, int yPos) {
        sh.setColor(color);
        sh.rect(xPos, yPos, WIDTH, HEIGHT);
    }
   
   
}
