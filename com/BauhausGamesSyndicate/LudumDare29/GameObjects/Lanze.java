package com.BauhausGamesSyndicate.LudumDare29.GameObjects;

import com.BauhausGamesSyndicate.LudumDare29.GameScreen;
import com.BauhausGamesSyndicate.LudumDare29.Tuning;
import com.BauhausGamesSyndicate.LudumDare29.overworld.AbstractSpawn;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;

/**
 *
 * @author Benedikt Vogler
 * @author Paul Flechsig
 * @author Jacob Bauer
 */
public class Lanze extends AbstractCharacter {
    private boolean arrived;
    private int dTimer;
    private int dTimerMax = 500;
    private float homeX;
    private float reach = 600;
    private AbstractSpawn home;
    private static FileHandle attacksound;
    private final Sound privateAttacksound;
    private boolean isPlaying;
    
    public Lanze(float x, float y, boolean world, AbstractSpawn home) {
        super(x, y, "lanze", world,4,3);

        if (attacksound==null)
            attacksound = Gdx.files.internal("com/BauhausGamesSyndicate/LudumDare29/assets/swclang1.wav");
        privateAttacksound = Gdx.audio.newSound(attacksound);
        
        arrived = false;
        homeX = x;
        this.home = home;
        dTimer = 0;
        setAcceleration(-1);
        setAccFactor(Tuning.LANZE_ACCELERATION_FACTOR + (float) (Math.random()*0.1));
        setFriction(Tuning.LANZE_FRICTION);
        setLife(Tuning.LANZE_LIFE);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        /*
        dTimer += delta;
        if(dTimer >= dTimerMax){
            dTimer %= dTimerMax;
            if(Math.random()*20 < 5)
                setAcceleration(getAcceleration()*(-1));
        }
        */
        
        if(!isFighting()) {
            if(getX() < homeX - reach/2 ||
               getX() > homeX + reach/2){
               if(getX() > homeX)
                   setAcceleration(-1);
               else
                   setAcceleration(1);
            }
            if(GameScreen.getPlayer().getX() > homeX - reach/2 && // wenn player in Heimat eindringt
               GameScreen.getPlayer().getX() < homeX + reach/2){
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
        //playSpacial(true);
        if(!hasDrainedLife())
            enemy.drainLife(Tuning.LANZE_DAMAGE_PER_ATTACK);
        
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
        home.anzLanzen -= 1;
    }
}
