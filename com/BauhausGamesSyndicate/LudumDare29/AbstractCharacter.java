package com.BauhausGamesSyndicate.LudumDare29;

//import com.badlogic.gdx.graphics.g2d.Sprite;
import com.BauhausGamesSyndicate.LudumDare29.AbstractEntity;
        
public abstract class AbstractCharacter extends AbstractEntity {
    public float speed;
    public int life;

    public AbstractCharacter(float x, float y, String name){
        super(x, y, name);
        life = 100;
    }
    
    public int getLife(){
        return life;
    }
    
    public void set(int life){
        this.life = life;
    }
    
    public boolean isDead(){
        if(life <= 0) return true;
        return false;
    }
    
    public float getSpeed(){
        return speed;
    }
    
    public void update(float x, float y){
 
    }
    public void render(GameScreen gs){
        
    }
}
