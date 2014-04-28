package com.BauhausGamesSyndicate.LudumDare29.overworld;

import com.BauhausGamesSyndicate.LudumDare29.GameObjects.AbstractEntity;
import com.BauhausGamesSyndicate.LudumDare29.GameObjects.Bauer;

/**
 *
 * @author Benedikt Vogler
 * @author Paul Flechsig
 * @author Jacob Bauer
 */
public class City extends AbstractSpawn{
    public City(Overworld overworld, int x, int y){
        super(overworld, x, y, "city");
        setOverallQuantity(Tuning.CITY_SPAWN_QUANTITY);
        setBQuantity(Tuning.CITY_SPAWN_BAUER_RATE);
    }
}
