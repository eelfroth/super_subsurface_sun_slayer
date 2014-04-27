
package com.BauhausGamesSyndicate.LudumDare29.GameObjects;

import com.BauhausGamesSyndicate.LudumDare29.GameScreen;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 *
 * @author Benedikt Vogler
 * @author Paul Flechsig
 * @author Jacob Bauer
 */
public abstract class AbstractEntity{
    private boolean world;//false: underworld, true: overworld
    private boolean flagRemoveFromUnderworld;
    private boolean flagRemoveFromOverworld;
    private float x;
    private float y;
    private float life;
    private int step;
    private int steps;
    private float timer = 0;
    private int steptime = 200;//ms
    
    private TextureRegion[] specialTextures;
    private TextureRegion[] standardAnimation;
    private boolean flip = false;
    private boolean special =false;
    

    /**
     * 
     * @param x
     * @param y
     * @param name name of sprite in spritesheet
     * @param world true when on overworld, false udnerworld
     * @param steps the amount of animation steps for walking
     * @param specialSteps  the amount of animation steps for the special
     */
    public AbstractEntity(float x, float y, String name, boolean world, int steps, int specialSteps) {
        life    = 100;
        this.x = x;
        this.y = y;
        this.world = world;
        this.steps = steps;
        
        standardAnimation = new TextureRegion[steps];
        for (int i = 0; i < steps; i++) {
            standardAnimation[i] = GameScreen.getSpritesheet().findRegion(name+""+Integer.toString(i)); 
            if (standardAnimation[i]==null)
                System.err.println(name+""+Integer.toString(i));
        }
        
        specialTextures = new TextureRegion[specialSteps];
            
         for (int i = 0; i < specialSteps; i++) {
            specialTextures[i] = GameScreen.getSpritesheet().findRegion(name+""+Integer.toString(i)+"s"); 
            //if (specialTextures[i]==null)
           //     System.err.println(name+""+Integer.toString(i)+"s");
        }
        
    }
    
    public float getLife(){
        return this.life;
    }
    
    public void setLife(float life){
        this.life = life;
    }
    
    public void drainLife(float life){
        this.life -= life;
    }
    
    public void update(float delta){
        timer+=delta;
        
        if (timer>steptime){
                step++;
                timer %= steptime;
        }
        
        if (!special){
            if (step >= standardAnimation.length)
                step=0;
        } else {
            if (step >= specialTextures.length)
                step=0;
        }
    };
    
    public void render(GameScreen gs){
        TextureRegion tex;
        if (special)
            tex = specialTextures[step];
        else
            tex = standardAnimation[step];
        
        if (flip != tex.isFlipX())
           tex.flip(true, false);
        gs.getBatch().draw(tex, x - getWidth()/2, y-56);
    }

    public boolean onOverworld() {
        return world;
    }
    
    public void switchWorld(){
        world = !world;
    }
    
    public boolean flagRemoveFromUnderworldSet() {
        return flagRemoveFromUnderworld;
    }

    public boolean flagRemoveFromOverworldSet() {
        return flagRemoveFromOverworld;
    }

    public void setFlagRemoveFromUnderworld() {
        this.flagRemoveFromUnderworld = true;
    }

    public void setFlagRemoveFromOverworld() {
        this.flagRemoveFromOverworld = true;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
    
    public int getWidth() {
        return standardAnimation[step].getRegionWidth();
    }
    
    public int getHeight() {
        return standardAnimation[step].getRegionHeight();
    }

    /**
     * Time till next sprite
     * @param steptime 
     */
    public void setSteptime(int steptime) {
        this.steptime = steptime;
    }
    
    /**
     * True if lef-siided
     * @param flip
     */
    public void setFlipHorizontal(boolean flip){
        this.flip=flip;
    }

    public boolean playingSpecial() {
        return special;
    }

    public void playSpacial(boolean special) {
        if (this.special!=special) {
            timer=0;
            step=0;
        }
        this.special = special;
    }
    
    public void setStep(int step) {
        this.step = step % steps;
    }
    
}
