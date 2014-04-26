package com.BauhausGamesSyndicate.LudumDare29;

//import com.badlogic.gdx.graphics.g2d.Sprite;
import com.BauhausGamesSyndicate.LudumDare29.AbstractEntity;
        
public abstract class AbstractCharacter extends AbstractEntity {
    public float speed;
    public float acceleration;
    public float accFactor;
    public float velocity;
    public static float friction;
    
    public int life;

    public AbstractCharacter(float x, float y, String name){
        super(x, y, name);
        life     = 100;
        
        speed     = 0;
        velocity  = 0;
        accFactor = 0.2f;
        acceleration = 0;
        friction = 0.01f;
        
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
        if(life <= 0) return true;
        return false;
    }
    
    public void update(float delta){
       
    }
}
