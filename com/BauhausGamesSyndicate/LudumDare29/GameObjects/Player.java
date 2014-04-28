package com.BauhausGamesSyndicate.LudumDare29.GameObjects;

import com.BauhausGamesSyndicate.LudumDare29.GameScreen;
import com.BauhausGamesSyndicate.LudumDare29.Tuning;
import com.BauhausGamesSyndicate.LudumDare29.overworld.Eingang;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Sound;

/**
 *
 * @author Benedikt Vogler
 * @author Paul Flechsig
 * @author Jacob Bauer
 */
public class Player extends AbstractCharacter {
    
    //wohin fallen
    
    
    private int menupoint = 0;
    private static Sound growlsound;
    private static Sound stepsound;
    private float attacktimer;
    private float stepX;
    private float stepY;
    private int distanceToTravel;
    private float distanceTraveled;
    
    public Player(float x, float y) {
        super(x, y, "overlord", false,10,9);
        setAccFactor(0.05f);
        growlsound = Gdx.audio.newSound(Gdx.files.internal("com/BauhausGamesSyndicate/LudumDare29/assets/growlsingle.ogg"));
        stepsound = Gdx.audio.newSound(Gdx.files.internal("com/BauhausGamesSyndicate/LudumDare29/assets/step.wav"));
    }
    
    @Override
    public void update(float delta){   
        super.update(delta);
        setAcceleration(0);
        
        if (GameScreen.onOverworld()){
            if (Gdx.input.isKeyPressed(Keys.D)){
                setAcceleration(1);
            }
        
            if (Gdx.input.isKeyPressed(Keys.A)){
                setAcceleration(-1);
            }
            
            //go down?
            Eingang eingang = GameScreen.getOverworld().getEingang();
            if (
                Gdx.input.isKeyPressed(Keys.S) &&
                getX()+getWidth()/2 > eingang.getX() &&
                getX()+getWidth()/2 < eingang.getX()+eingang.getWidth()
                ){
                descend();
            }
        }else {//underworld
            
            if(!isRising()){
                //    setY(Gdx.graphics.getHeight()/20);
                
                
                
                if (distanceTraveled<distanceToTravel){//traveling
                    float dX = stepX*delta; 
                    float dY = stepY*delta; 
                    setX(getX()+dX);
                    setY(getY()+dY);
                    distanceTraveled += Math.sqrt(dX*dX+dY*dY);
                } else {
                    //rise?
                    if (menupoint==0 && Gdx.input.isKeyPressed(Keys.W)){
                        rise();
                    }
                    
                    if (Gdx.input.isKeyPressed(Keys.W)){
                        goTo(0);
                    } else if (Gdx.input.isKeyPressed(Keys.D)){
                        goTo(3);
                    }else if (Gdx.input.isKeyPressed(Keys.S)){
                        goTo(2);
                    }else if (Gdx.input.isKeyPressed(Keys.A)){
                        goTo(1);
                    }
                
                }
                

            }
        }
        
        if (attacktimer>0) {
            attacktimer-=delta;//currently attacking
            setVelocity(0);
        } else {
            if (GameScreen.onOverworld() && Gdx.input.isKeyPressed(Keys.SPACE)){
                attack();
            } else {
                playSpecial(false);
            }
        }
        
        //walkingsound
        if (getVelocity()<0.1f && getVelocity()>-0.1f){
            stepsound.stop();
        } else {
            stepsound.loop(0.7f);
        }
    }
    
    private void goTo(int id){
        if (menupoint!=0){//if not on throne go back to it first
            id=0;
        }
        
        if (id==2 && menupoint==0){
            flyTo(1, -2, 420);
        }else if (id==0 && menupoint==2){
            flyTo(-1, 2, 420);
        }  else if(id==1 && menupoint==0){
            flyTo(-2, 0.5f, 470);
        }else if(id==3 && menupoint==0){
            flyTo(2, 1, 520);
        }else if(id==0 && menupoint==3){
            flyTo(-2, -1, 520);
        } else if (id==0 && menupoint==1){
            flyTo(2, -0.5f, 470);
        } 
        
        menupoint = id;
    }

    public int getMenupoint() {
        return menupoint;
    } 

    @Override
    public void rise() {
        super.rise();
        GameScreen.getOverworld().getEingang().rise();
    }
    
    @Override
    public void descend(){
        super.descend();
        GameScreen.getOverworld().getEingang().descend();
    }

    
    /**
     * was soll passieren, wenn die SPielfigur angreift?
     */
    public void attack(){
        playSpecial(true);
        attacktimer = 2000;
        stepsound.stop();
        growlsound.play();
        
        int attXpos;
        int attackRadius =50;
        
        if (isFlipped())
            attXpos= -100;
        else
            attXpos= 100;
        
            
        for (AbstractEntity entity : GameScreen.getOverworld().getEntityList()) {
            if (
                entity instanceof AbstractCharacter &&
                !((AbstractCharacter)entity).isEvil() &&
                entity.getX()-getX() > attXpos - attackRadius &&
                entity.getX()-getX() < attXpos + attackRadius
                )
                entity.drainLife(100);
        }
        
    }
    
    @Override
    public boolean isEvil() {
        return true;
    }

    @Override
    public void fight(AbstractCharacter enemy, float delta) {
        //player does nothing
    }
    
    private void flyTo(float x, float y, int distance){
        this.stepX = x;
        this.stepY = y;
        this.distanceToTravel = distance;
        this.distanceTraveled=0;
    }
    
    @Override
    public void  onDescend(){
        //setX(1020);
        //setY(Tuning.SET);
    }

    @Override
    public void onRise(){
        //nothing
    }
    
    @Override
    public float getStartLocation() {
         return Tuning.PLAYER_UNDERWORLD_START_LOCATION_Y;
    }
}
