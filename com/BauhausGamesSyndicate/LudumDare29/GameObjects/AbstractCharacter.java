package com.BauhausGamesSyndicate.LudumDare29.GameObjects;

import com.BauhausGamesSyndicate.LudumDare29.GameScreen;
import com.BauhausGamesSyndicate.LudumDare29.Tuning;
import com.BauhausGamesSyndicate.LudumDare29.overworld.Chunk;
import com.BauhausGamesSyndicate.LudumDare29.overworld.Overworld;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;


public abstract class AbstractCharacter extends AbstractGameObject {
    private float acceleration;
    private float accFactor;
    private float velocity;
    private float friction;
    private int verticalOffset;
    
    private boolean canWalk;
    private boolean shouldRise;
    private boolean shouldDescend;
    
    private boolean fighting = false;
    private boolean drainedLife = false;
    
    private static Sound dieSoundGood;
    private static Sound dieSoundEvil;
    
    

    public AbstractCharacter(float x, float y, String name, boolean world, int steps, int specialSteps){
        super(x, y, name, world,steps, specialSteps);
        
        if (dieSoundGood==null)
            dieSoundGood = Gdx.audio.newSound(Gdx.files.internal("com/BauhausGamesSyndicate/LudumDare29/assets/dieing.wav"));
        
        if (dieSoundEvil==null)
            dieSoundEvil = Gdx.audio.newSound(Gdx.files.internal("com/BauhausGamesSyndicate/LudumDare29/assets/splatter3.wav"));
        
        verticalOffset = -(int) (Math.random()*Tuning.CHARACTER_VERTICAL_OFFSET);
        velocity  = 0;
        accFactor = Tuning.CHARACTER_ACCELERATION_FACTOR;
        acceleration = 0;
        friction = Tuning.CHARACTER_FRICTION;
        canWalk = true;
    }
    
    public void setCanWalk(boolean can){
        this.canWalk = can;
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
    
    @Override
    public void update(float delta){
        super.update(delta);
        
        if (GameScreen.onOverworld() && !shouldRise && !shouldDescend){
            setY(Overworld.getHeight((int) getX())+ verticalOffset);
        }
        
        if(canWalk){
            setVelocity    ((getVelocity()+ getAcceleration()* getAccFactor())     * (1 - getFriction()) );
            setX((getX() + getVelocity()*delta));
        }
        
        //flip graphic
        if(getVelocity()< -0.1f)
            this.setFlipHorizontal(true);
        if(getVelocity()> 0.1f)
            this.setFlipHorizontal(false);
        
        if (shouldRise){
            setY(getY()+delta*Tuning.CHARACTER_RISE_SPEED);
            
            if (getY() >= Chunk.HEIGHT){
                shouldRise=false;
                setX(GameScreen.getOverworld().getEingang().getX()+GameScreen.getOverworld().getEingang().getWidth()/2);
                GameScreen.getOverworld().addEntity(this);
                setFlagRemoveFromUnderworld();
                switchWorld();
                onRise();
            }
        } else if (shouldDescend){
            setY(getY()-(delta*Tuning.CHARACTER_DESCEND_SPEED));
            
            //entering underworld
            if(onOverworld()) {
                if (getY() < 0){
                    //setX(GameScreen.getOverworld().getEingang().getX() + GameScreen.getOverworld().getEingang().getWidth()/2);
                    GameScreen.getUnderworld().addEntity(this);
                    setFlagRemoveFromOverworld();
                    switchWorld();
                    setY(540);
                    setX(960);
                    setAcceleration(0);
                }
            } else {
                if (getY() < getStartLocation()){
                    shouldDescend=false;
                    //setX(UNDERWORLD_START_LOCATION_X);
                    onDescend();
                }
            }
            
        }
        
        //colission check
        fighting = collideWithEnemy(delta);
        
        if(!fighting)
            drainedLife = false;
         
    }
    
    public boolean collideWithEnemy(float delta){
        boolean colissionWithEnemy = false;
        for (AbstractGameObject entity : GameScreen.getOverworld().getEntityList()) {
            if (entity instanceof AbstractCharacter &&//can typecasting be made
                ((AbstractCharacter)entity).isEvil()!= this.isEvil() && //is not same fraction?
                entity.getX()+entity.getWidth() > getX()&&
                entity.getX() < getX()+entity.getWidth()){
                fight((AbstractCharacter) entity, delta);
                colissionWithEnemy=true;
            }
        }
        return colissionWithEnemy;        
    }
    
    public Leiche collideWithCorpse(float delta){
        Leiche colissionWithCorpse = null;
        for (AbstractGameObject entity : GameScreen.getOverworld().getEntityList()) {
            if (entity instanceof Leiche &&
                entity.getX()+entity.getWidth() > getX()&&
                entity.getX() < getX()+entity.getWidth()){
                colissionWithCorpse=(Leiche)entity;
            }
        }
        return colissionWithCorpse;        
    }
    
    public void rise(){
        shouldRise = true;
        deactivateWalkOnCeilingHax();
    }
    
    public void descend() {
        shouldDescend = true;
    }

    public boolean isRising() {
        return shouldRise;
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

    public abstract void onDescend();

    public abstract void onRise();

    public float getStartLocation() {
         return Tuning.CHARACTER_UNDERWORLD_START_LOCATION_Y;
    }
    
    /**
     * is character in contact with enemy?
     * @return 
     */
    public boolean isFighting(){
        return fighting;
    };
    
    public boolean hasDrainedLife(){
        return drainedLife;
    };

    @Override
    public void onDeath() {
        if (isEvil())
            dieSoundEvil.play();
        else
            dieSoundGood.play();
    }
}
