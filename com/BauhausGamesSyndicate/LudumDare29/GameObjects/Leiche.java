/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.BauhausGamesSyndicate.LudumDare29.GameObjects;

/**
 *
 * @author Benedikt Vogler
 */
public class Leiche extends AbstractEntity {

    public Leiche(float x, float y) {
        super(x, y, "tot", true, 4, 1);
        animateOnce();
    }

    @Override
    public void onDeath() {
       
    }
    
    
}
