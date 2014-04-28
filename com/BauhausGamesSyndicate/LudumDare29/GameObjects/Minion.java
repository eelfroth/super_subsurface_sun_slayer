package com.BauhausGamesSyndicate.LudumDare29.GameObjects;

import com.BauhausGamesSyndicate.LudumDare29.GameScreen;
import com.BauhausGamesSyndicate.LudumDare29.overworld.Eingang;


/**
 *
 * @author Paul
 */
public abstract class Minion extends AbstractCharacter{
    
    public Minion(float x, float y, String name,boolean world, int steps, int specialsteps){
        super(x, y, name, world, steps, specialsteps);
       // setSpeed((float) (0.1f + Math.random()*.2f));
    }

    @Override
    public boolean isEvil() {
        return true;
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        
        //follow player
        if(onOverworld()) {
            if (GameScreen.onOverworld()){
                if (GameScreen.getPlayer().getX() > getX())
                    setAcceleration(1);
                else
                    setAcceleration(-1);
            }
            else{
                Eingang e = GameScreen.getOverworld().getEingang();
                if (e.getX() > getX())
                    setAcceleration(1);
                else
                    setAcceleration(-1);
            } 
        }
    }
    
    
}