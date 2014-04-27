package com.BauhausGamesSyndicate.LudumDare29.GameObjects;

import com.BauhausGamesSyndicate.LudumDare29.GameScreen;
import com.BauhausGamesSyndicate.LudumDare29.overworld.Chunk;
import com.BauhausGamesSyndicate.LudumDare29.overworld.Overworld;

public abstract class AbstractCharacter extends AbstractEntity {
    public float speed;
    public float acceleration;
    public float accFactor;
    public float velocity;
    public float friction;
    
    private boolean shouldRaise;
    private boolean shouldDescend;

    public AbstractCharacter(float x, float y, String name, boolean world, int steps, int specialSteps){
        super(x, y, name, world,steps, specialSteps);
        
        speed     = 0;
        velocity  = 0;
        accFactor = 0.03f;
        acceleration = 0;
        friction = 0.05f;
        
    }
    
    public void move(float delta){
        setAcceleration(getAcceleration() * getAccFactor()    );
        setVelocity    (getVelocity()     + getAcceleration() );
        setVelocity    (getVelocity()     * (1 - getFriction()) );
        setX((getX() + getVelocity()*delta));
    }
    
    public AbstractCharacter(float x, float y, String name, boolean world){
        this(x, y, name, world,1,1);
    }
    
    public float getFriction(){
        return this.friction;
    }
    
    public float getVelocity(){
        return this.velocity;
    }
    
    public void setVelocity(float v){
        this.velocity = v;
    }
    
    public float getAcceleration(){
        return this.acceleration;
    }
    
    public void setAcceleration(float acc){
        this.acceleration = acc;
    }
    
    public float getAccFactor(){
        return this.accFactor;
    }
    
    public void setAccFactor(float fac){
        this.accFactor = fac;
    }
    
    public void setSpeed(float speed){
        this.speed = speed;
    }
    
    public float getSpeed(){
        return speed;
    }
    
    public int getLife(){
        return life;
    }
    
    public void setLife(int life){
        this.life = life;
    }
    
    public boolean isDead(){
        return life <= 0;
    }
    
    @Override
    public void update(float delta){
        super.update(delta);
        if (GameScreen.onOverworld() && !shouldRaise && !shouldDescend){
            setY(Overworld.getHeight((int) getX()));
        }
        
        move(delta);
        
        //flip graphic
        if(getAcceleration()< 0)
            this.setFlipHorizontal(true);
        else
            this.setFlipHorizontal(false);
        
        if (shouldRaise){
            setY(getY()+delta/2);
            
            if (getY() >= Chunk.HEIGHT){
                shouldRaise=false;
                setX(GameScreen.getOverworld().getEingang().getX()+GameScreen.getOverworld().getEingang().getWidth()/2);
                GameScreen.getOverworld().addEntity(this);
                setFlagRemoveFromUnderworld();
                switchWorld();
            }
        }else if (shouldDescend){
            setY(getY()-delta/2);
            
            //entering underworld
            if (getY() < 0){
                shouldDescend=false;
                //setX(GameScreen.getOverworld().getEingang().getX() + GameScreen.getOverworld().getEingang().getWidth()/2);
                GameScreen.getUnderworld().addEntity(this);
                setFlagRemoveFromOverworld();
                switchWorld();
                setX(860);
                setY(500);
            }
        }
        
        //colission check
        for (AbstractEntity entity : GameScreen.getOverworld().getEntityList()) {
            if (entity instanceof AbstractCharacter &&//can typecasting be made
                ((AbstractCharacter)entity).isEvil()!=isEvil() && //is not same fraction?
                entity.getX()+entity.getWidth() > getX()&&
                entity.getX() < getX()+entity.getWidth()){
                acceleration=0;
                fight((AbstractCharacter) entity);
            }
        }
    }
    
    public void rise(){
        shouldRaise = true;
    }
    
    public void descend() {
        shouldDescend = true;
    }

    public boolean isRaising() {
        return shouldRaise;
    }

    public boolean isDescending() {
        return shouldDescend;
    }
    
    public abstract boolean isEvil();
    
    /**
     * What should happen during fighting
     * @param enemy the enemy you are fighting
     */
    public abstract void fight(AbstractCharacter enemy);
}
