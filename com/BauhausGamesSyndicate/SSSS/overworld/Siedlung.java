package com.BauhausGamesSyndicate.SSSS.overworld;

import com.BauhausGamesSyndicate.SSSS.Tuning;

/**
 *
 * @author Benedikt Vogler
 * @author Paul Flechsig
 * @author Jacob Bauer
 */
public class Siedlung extends AbstractSpawn{
    public Siedlung(Overworld overworld, int x, int y){
        super(overworld, x, y, "siedlung");
        setOverallQuantity(Tuning.SIEDLUNG_QUANTITY);
        setBQuantity(Tuning.SIEDLUNG_SPAWN_BAUER_RATE);
        setMaxBauern(Tuning.SIEDLUNG_MAX_BAUERN);
        setLife(Tuning.SIEDLUNG_LIFE);
    }
}