/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.BauhausGamesSyndicate.LudumDare29.overworld;

import com.BauhausGamesSyndicate.LudumDare29.GameObjects.AbstractGameObject;
import com.BauhausGamesSyndicate.LudumDare29.GameObjects.Bauer;
import com.BauhausGamesSyndicate.LudumDare29.Tuning;

/**
 *
 * @author Paul
 */
public class CastleOfLight extends AbstractSpawn{
    public CastleOfLight(Overworld overworld, int x, int y){
        super(overworld, x, y, "sonnengottempel");
        setOverallQuantity(Tuning.CASTLEOFLIGHT_QUANTITY);
        setLQuantity(Tuning.CASTLEOFLIGHT_SPAWN_LANZE_RATE);
        setRQuantity(Tuning.CASTLEOFLIGHT_SPAWN_RITTER_RATE);
        setPQuantity(Tuning.CASTLEOFLIGHT_SPAWN_PALA_RATE);
        setMaxLanzen(Tuning.CASTLEOFLIGHT_MAX_LANZEN);
        setMaxRitter(Tuning.CASTLEOFLIGHT_MAX_RITTER);
        setMaxPala(Tuning.CASTLEOFLIGHT_MAX_PALA);
        setLife(Tuning.CASTLEOFLIGHT_LIFE);
    } 
}
 