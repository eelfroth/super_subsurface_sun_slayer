package com.BauhausGamesSyndicate.LudumDare29.overworld;

import com.BauhausGamesSyndicate.LudumDare29.GameObjects.AbstractEntity;
import com.BauhausGamesSyndicate.LudumDare29.GameObjects.Bauer;
import com.BauhausGamesSyndicate.LudumDare29.Tuning;

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
    }
}
