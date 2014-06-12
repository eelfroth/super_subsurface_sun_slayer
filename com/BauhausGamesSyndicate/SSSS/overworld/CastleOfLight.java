package com.BauhausGamesSyndicate.SSSS.overworld;

import com.BauhausGamesSyndicate.SSSS.GameObjects.AbstractEntity;
import com.BauhausGamesSyndicate.SSSS.GameObjects.Bauer;
import com.BauhausGamesSyndicate.SSSS.Tuning;

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
 