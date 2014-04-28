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
public class Fortress extends AbstractSpawn{
    public Fortress(Overworld overworld, int x, int y){
        super(overworld, x, y, "palisaden");
        setOverallQuantity(Tuning.BURG_QUANTITY);
        setLQuantity(Tuning.FORTRESS_SPAWN_LANZE_RATE);
        setRQuantity(Tuning.FORTRESS_SPAWN_RITTER_RATE);
        setBQuantity(Tuning.FORTRESS_SPAWN_BAUER_RATE);
    } 
}
