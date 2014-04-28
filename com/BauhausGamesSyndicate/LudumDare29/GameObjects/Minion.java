package com.BauhausGamesSyndicate.LudumDare29.GameObjects;

import com.BauhausGamesSyndicate.LudumDare29.GameScreen;
import com.BauhausGamesSyndicate.LudumDare29.overworld.Eingang;
import com.badlogic.gdx.Gdx;


/**
 *
 * @author Paul
 */
public abstract class Minion extends AbstractCharacter{
    private boolean leaveScreen = false;
    
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
            
            //if(GameScreen.getPlayer().isDescending())
            //    descend();
        }
        else if(getWalkOnCeilingHax() && !isRising()){
            setX(getX()%6282);
            if ((getX() < 1 || getX() > 6281)) {
               if(!leaveScreen) 
                   leaveScreen = true;
               else {
                   leaveScreen = false;
                   //setX((float)Math.sin(getX()/1000)*570+960+20);
                   //setY((float)Math.cos(getX()/1000)*520+960 -10);
                   setX(960);
                   setY(540-15);
                   rise();
               }
            }
            if (3141 > getX() && 0 < getX())
                setAcceleration(-1);
            else //if (3141 <= getX() && 6281 > getX())
                setAcceleration(1);
            
        }
    }
    
    @Override
    public void  onDescend(){
        //nothing
    }

    @Override
    public void onRise(){
        //nothing
    }
    
    public void setLeaveScreen(boolean b) {
        leaveScreen = b;
    }
    
}