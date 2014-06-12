package com.BauhausGamesSyndicate.SSSS.GameObjects;

import com.BauhausGamesSyndicate.SSSS.GameScreen;
import com.BauhausGamesSyndicate.SSSS.Tuning;


/**
 *
 * @author Benedikt Vogler
 * @author Paul Flechsig
 * @author Jacob Bauer
 */
public class Slender extends Minion {
    private boolean hasCorpse = false;

    public Slender(boolean world) {
        super(
            3141f+3141f/2,
            0,
            "slender",
            world,
            7,
            7
        );
        setAccFactor(Tuning.SLENDER_ACCELERATION_FACTOR);
        setFriction(Tuning.SLENDER_FRICTION);
        setLife(Tuning.SLENDER_LIFE);
    }
    @Override
    public void update(float delta) {
        super.update(delta);
        
        if(onOverworld()) {
        Leiche corpse = collideWithCorpse(delta);
        if(corpse != null) {
            corpse.setFlagRemoveFromOverworld();
            hasCorpse = true;
        }
        }else{
            if(hasCorpse){
            setFlagRemoveFromOverworld();
            GameScreen.getUnderworld().giveMoney(Tuning.MONEY_PER_CORPSE);
            }
        }
        
        if(hasCorpse) {
            setAcceleration(-1);
            if(2078 < getX() && 2150 > getX()){
                descend();
        }
            playSpecial(true);
            this.setFlipHorizontal(true);
        }
        else 
            setAcceleration(1);
    }
    
    @Override
    public void fight(AbstractCharacter enemy, float delta) {
        //slender does nothing
    }
    
    @Override
    public void  onDescend(){
        
    }
    
    @Override
    public void onDeath() {
        super.onDeath();
        //nothing
    }
}
