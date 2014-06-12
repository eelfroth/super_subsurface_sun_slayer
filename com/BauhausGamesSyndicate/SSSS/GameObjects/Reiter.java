package com.BauhausGamesSyndicate.SSSS.GameObjects;

import com.BauhausGamesSyndicate.SSSS.GameScreen;
import com.BauhausGamesSyndicate.SSSS.Tuning;
import com.BauhausGamesSyndicate.SSSS.overworld.AbstractSpawn;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;

/**
 *
 * @author Paul
 */
public class Reiter extends AbstractCharacter {
    private int dTimer;
    private final int dTimerMax = 500;
    private final float reach;
    private final AbstractSpawn home;
    private static FileHandle attacksound;
    private final Sound privateAttacksound;
    private boolean isPlaying = false;
    
    public Reiter(float x, float y, boolean world, AbstractSpawn home) {
        super(x, y, "reiter", world,4,3);
        
        if (attacksound==null)
            attacksound = Gdx.files.internal("com/BauhausGamesSyndicate/SSSS/assets/swclang1.wav");
        privateAttacksound = Gdx.audio.newSound(attacksound);
        
        this.home = home;
        dTimer = 0;
        setAcceleration(-1);
        reach = 1000 + (float)Math.random()*100;
        setAccFactor(Tuning.PALA_ACCELERATION_FACTOR + (float) (Math.random()*0.1));
        setFriction(Tuning.PALA_FRICTION);
        setLife(Tuning.PALA_LIFE);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        
        
        if(!isFighting()) {
            if(getX() < home.getX()- reach/2 ||
               getX() > home.getX() + reach/2){
               if(getX() > home.getX())
                   setAcceleration(-1);
               else
                   setAcceleration(1);
            }
            if(GameScreen.getPlayer().getX() > home.getX() - reach/2 && // wenn player in Heimat eindringt
               GameScreen.getPlayer().getX() < home.getX() + reach/2){
               setX(getX() + getAcceleration()*2); // wengl durchdrehen!
               if(GameScreen.getPlayer().getX() > getX()) // und auf player zugehen
                   setAcceleration(1);
               else
                   setAcceleration(-1);

            }else{// change direction sometimes
                dTimer += delta;
                if(dTimer >= dTimerMax){
                    dTimer %= dTimerMax;
                    if((int)(Math.random()*50) < 5){
                        setAcceleration(getAcceleration()*(-1));
                    }
                }
            }    
            if(getAcceleration()<-0.1f)
                setFlipHorizontal(true);
            if(getAcceleration()> 0.1f)
                setFlipHorizontal(false);
        }
    }
    
    
    @Override
    public boolean isEvil() {
        return false;
    }
    
    @Override
    public void fight(AbstractCharacter enemy, float delta) {
        playSpecial(true);
        if(!hasDrainedLife()) {
            enemy.drainLife(Tuning.REITER_DAMAGE_PER_ATTACK);
        }
        
        if (
            getAnimationStep()==0
            &&
            !isPlaying){
            privateAttacksound.play();
            isPlaying=true;
        }
        
        if (getAnimationStep()!=0)
            isPlaying=false;
        
        setAcceleration(0);
    }
    
    @Override
    public void  onDescend(){
        activateWalkOnCeilingHax();
    }

    @Override
    public void onRise(){
        //nothing
    }
    
    @Override
    public void onDeath() {
        super.onDeath();
        home.drainLife(1);
    }
}