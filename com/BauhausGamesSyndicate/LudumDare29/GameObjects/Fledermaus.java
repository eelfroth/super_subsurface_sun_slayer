package com.BauhausGamesSyndicate.LudumDare29.GameObjects;

import com.BauhausGamesSyndicate.LudumDare29.GameScreen;
import com.BauhausGamesSyndicate.LudumDare29.Tuning;
import com.badlogic.gdx.Gdx;
import static java.lang.Math.sin;

/**
 *
 * @author Benedikt Vogler
 * @author Paul Flechsig
 * @author Jacob Bauer
 */
public class Fledermaus extends Minion{
    private float y_sin;
    private float x_sin;
    private float x_pos;
    private float y_pos;

    public Fledermaus(boolean world) {
       super(
           (960)+50,
           Gdx.graphics.getHeight()/2,
           "fledermaus",
           world,
           4,
           1
       );
       
        setAccFactor(Tuning.BAT_ACCELERATION_FACTOR);
        setFriction(Tuning.BAT_FRICTION);
        setLife(Tuning.BAT_LIFE);
       
       setSteptime(50);
       
       //startwerte zugällig, damit die fledermäuse unterschiedlich fliegen;
       y_sin = ((float)Math.random())*1000f;
       x_sin = ((float)Math.random())*1000f;
       y_pos = ((float)Math.random())*100f;
       x_pos = 0f;
       setStep((int)(Math.random()*4));
    }  

    @Override
    public void fight(AbstractCharacter enemy, float delta) {
        //playSpecial(true);
    }
    
    @Override
    public void update(float delta) {
        super.update(delta);
        deactivateWalkOnCeilingHax();
        setLeaveScreen(false);
        
        //(debug) unendlich leben
        setLife(1003577);
        
        //den x- und y-koordinaten werden sinuskurven addiert
        if(onOverworld()) {
            if(!isDescending()) {
                y_sin += delta*0.167;
                setY((getY() + (float)sin(y_sin/147f)*200.0f) + 200.0f + y_pos);
                }
                x_sin += delta*0.167;
                setAcceleration((float)sin(x_sin/211f)*0.1f);
        }
        else {
            if(!isRising()){
                y_sin += delta*0.167;
                setY((Gdx.graphics.getHeight()/2) + ((float)sin(y_sin/147f)*350.0f));
            }
            x_sin += delta*0.167;

            //setX((Gdx.graphics.getWidth()/2) + (float)sin(x_sin/211f)*350.0f);
            setAcceleration((float)sin(x_sin/211f)*0.1f);
        }
        
        if(GameScreen.getPlayer().isRising() && !onOverworld()) {
                rise();
        }
        
        //debug shit
        boolean debug = false;
        if(debug) {
            StringBuffer result = new StringBuffer();
            result.append(getX());
            result.append("\t");
            String mynewstring = result.toString();
            Gdx.app.log("", mynewstring);
        }
    }
    
    @Override
    public void  onDescend(){
        setX(1020);
        //setY(550);
    }
    
    @Override
    public float getStartLocation() {
         return Tuning.PLAYER_UNDERWORLD_START_LOCATION_Y;
    }

    
}
