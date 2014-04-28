package com.BauhausGamesSyndicate.LudumDare29.GameObjects;

import com.BauhausGamesSyndicate.LudumDare29.GameScreen;
import com.BauhausGamesSyndicate.LudumDare29.overworld.Chunk;
import com.BauhausGamesSyndicate.LudumDare29.overworld.Overworld;

public abstract class AbstractCharacter extends AbstractEntity {
    private float acceleration;
    private float accFactor;
    private float velocity;
    private float friction;
    
    private boolean canWalk;
    private boolean shouldRaise;
    private boolean shouldDescend;

    public AbstractCharacter(float x, float y, String name, boolean world, int steps, int specialSteps){
        super(x, y, name, world,steps, specialSteps);
        
        velocity  = 0;
        accFactor = 0.03f;
        acceleration = 0;
        friction = 0.05f;
        canWalk = true;
    }
    
    public void setCanWalk(boolean can){
        this.canWalk = can;
    }
    
    public boolean getCanWalk(){
        return this.canWalk;
    }
    
    
    public void move(float delta){
        setAcceleration(getAcceleration()   );
        setVelocity    (getVelocity()     + getAcceleration()* getAccFactor()   );
        setVelocity    (getVelocity()     * (1 - getFriction()) );
        setX((getX() + getVelocity()*delta));
    }
    
    public AbstractCharacter(float x, float y, String name, boolean world){
        this(x, y, name, world,1,1);
    }
    
    public float getFriction(){
        return this.friction;
    }
    
    public void setFriction(float friction){
        this.friction = friction;
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
         
    public boolean isDead(){
        return getLife() <= 0;
    }
    
    @Override
    public void update(float delta){
        super.update(delta);
        if (GameScreen.onOverworld() && !shouldRaise && !shouldDescend){
            setY(Overworld.getHeight((int) getX()));
        }
        if (isDead()){
            setFlagRemoveFromOverworld();
        }
        if(getCanWalk())
            move(delta);
        
        //flip graphic
        if(getVelocity()< -0.1f)
            this.setFlipHorizontal(true);
        if(getVelocity()> 0.1f)
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
            setY(getY()-(delta/8));
            
            //entering underworld
            if (getY() < 0){
                shouldDescend=false;
                //setX(GameScreen.getOverworld().getEingang().getX() + GameScreen.getOverworld().getEingang().getWidth()/2);
                GameScreen.getUnderworld().addEntity(this);
                setFlagRemoveFromOverworld();
                switchWorld();
                setX(1020);
                setY(550);
            }
        }
        
        //colission check
        /*
        if(collide()){ 
            setCanWalk(false);
            // fight((AbstractCharacter) entity, delta);
        }
        */ 
    }
    
    public boolean collide(){
        for (AbstractEntity entity : GameScreen.getOverworld().getEntityList()) {
            if (entity instanceof AbstractCharacter &&//can typecasting be made
                ((AbstractCharacter)entity).isEvil()!= this.isEvil() && //is not same fraction?
                entity.getX()+entity.getWidth() > getX()&&
                entity.getX() < getX()+entity.getWidth())
                return true;
        }
        return false;        
    }
    
    public void rise(){
        shouldRaise = true;
    }
    
    public void descend() {
        shouldDescend = true;
    }

    public boolean isRising() {
        return shouldRaise;
    }

    public boolean isDescending() {
        return shouldDescend;
    }
    
    public abstract boolean isEvil();
    
    /**
     * What should happen during fighting
     * @param enemy the enemy you are fighting
     * @param delta
     */
    public abstract void fight(AbstractCharacter enemy, float delta);
}
