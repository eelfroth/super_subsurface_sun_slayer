/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.BauhausGamesSyndicate.LudumDare29.overworld;

import com.BauhausGamesSyndicate.LudumDare29.Tuning;

/**
 *
 * @author Paul
 */
public class Burg extends AbstractSpawn{
    public Burg(Overworld overworld, int x, int y){
        super(overworld, x, y, "burg");
        setOverallQuantity(Tuning.BURG_QUANTITY);
        setLQuantity(Tuning.BURG_SPAWN_LANZE_RATE);
        setRQuantity(Tuning.BURG_SPAWN_RITTER_RATE);
        setPQuantity(Tuning.BURG_SPAWN_PALA_RATE);
        setMaxLanzen(Tuning.BURG_MAX_LANZEN);
        setMaxRitter(Tuning.BURG_MAX_RITTER);
        setMaxPala(Tuning.BURG_MAX_PALA);
        setLife(Tuning.BURG_LIFE);
    } 
}