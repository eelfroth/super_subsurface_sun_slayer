package com.BauhausGamesSyndicate.SSSS.overworld;

import com.BauhausGamesSyndicate.SSSS.Tuning;

/**
 *
 * @author Benedikt Vogler
 * @author Paul Flechsig
 * @author Jacob Bauer
 */
public class Bauernhof extends AbstractSpawn{
    public Bauernhof(Overworld overworld, int x, int y){
        super(overworld, x, y, "bauernhof");
        setOverallQuantity(Tuning.BAUERNHOF_QUANTITY);
        setBQuantity(Tuning.BAUERNHOF_SPAWN_BAUER_RATE);
        setMaxBauern(Tuning.BAUERNHOF_MAX_BAUERN);
        setLife(Tuning.BAUERNHOF_LIFE);
    }
}
