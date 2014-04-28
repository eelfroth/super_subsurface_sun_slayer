package com.BauhausGamesSyndicate.LudumDare29.GameObjects;

import com.BauhausGamesSyndicate.LudumDare29.GameScreen;
import com.badlogic.gdx.Gdx;
import static java.lang.Math.floor;
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
    private float y_pos;

    public Fledermaus(boolean world) {
       super(
           GameScreen.onOverworld()?GameScreen.getOverworld().getEingang().getX():(Gdx.graphics.getWidth()/2)+50,
           Gdx.graphics.getHeight()/2,
           "fledermaus",
           world,
           4,
           1
       );
       
       canDescend = true;
       setSteptime(50);
       
       //startwerte zugällig, damit die fledermäuse unterschiedlich fliegen;
       y_sin = ((float)Math.random())*1000f;
       x_sin = ((float)Math.random())*1000f;
       y_pos = ((float)Math.random())*100f;
       setStep((int)(Math.random()*4));
    }  

    @Override
    public void fight(AbstractCharacter enemy, float delta) {
        //playSpacial(true);
    }
    
    @Override
    public void update(float delta) {
        super.update(delta);
        
        //(debug) unendlich leben
        setLife(1003577);
        
        //den x- und y-koordinaten werden sinuskurven addiert
        if(onOverworld()) {
            y_sin += delta*0.167;
            setY((getY() + (float)sin(y_sin/147f)*200.0f) + 200.0f + y_pos);
            x_sin += delta*0.167;
            setX(getX() + (float)sin(x_sin/211f)*11f);
        }
        else if(!isRaising()){
            y_sin += delta*0.167;
            setY((Gdx.graphics.getHeight()/2) + ((float)sin(y_sin/147f)*350.0f));
            x_sin += delta*0.167;
            setX((Gdx.graphics.getWidth()/2) + (float)sin(x_sin/211f)*350.0f);
        }
        
        if(GameScreen.getPlayer().isRaising() && !onOverworld()) {
                rise();
        }
        
        //debug shit
        boolean debug = false;
        if(debug) {
            StringBuffer result = new StringBuffer();
            result.append("\t");
            result.append(y_sin);
            result.append("\t");
            result.append((float)sin(y_sin/100)*100);
            String mynewstring = result.toString();
            Gdx.app.log("", mynewstring);
        }
    }
}
