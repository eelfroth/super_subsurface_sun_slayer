/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.BauhausGamesSyndicate.LudumDare29.overworld;

import com.BauhausGamesSyndicate.LudumDare29.GameObjects.AbstractEntity;
import com.BauhausGamesSyndicate.LudumDare29.GameObjects.Bauer;
import com.BauhausGamesSyndicate.LudumDare29.Tuning;

/**
 *
 * @author Paul
 */
public class Bauernhof2 extends AbstractSpawn{
    public Bauernhof2(Overworld overworld, int x, int y){
        super(overworld, x, y, "befestigter_bauernhof");
        setOverallQuantity(Tuning.BAUERNHOF2_QUANTITY);
        setBQuantity(Tuning.BAUERNHOF2_SPAWN_BAUER_RATE);
        setLQuantity(Tuning.BAUERNHOF2_SPAWN_LANZE_RATE);
    } 
}
