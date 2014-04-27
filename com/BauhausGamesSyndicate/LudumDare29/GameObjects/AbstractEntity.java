
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
    public int life;
    private int step;
    private float timer = 0;
    private int steptime = 200;//ms
    
    private TextureRegion[] specialTextures;
    private TextureRegion[] walkTextures;
    private boolean flip = false;
    private boolean special =false;
    
     public AbstractEntity(float x, float y, String lebend, String tot, boolean world) {
        walkTextures = new TextureRegion[2];
        walkTextures[0] = GameScreen.getSpritesheet().findRegion(lebend);
        walkTextures[1] = GameScreen.getSpritesheet().findRegion(tot);
        life     = 100;
        this.x = x;
        this.y = y;
        this.world = world;
    }
    
    /**
     * 
     * @param x
     * @param y
     * @param name name of sprite in spritesheet
     * @param world true when on overworld, false udnerworld
     */
    public AbstractEntity(float x, float y, String name, boolean world) {
        walkTextures = new TextureRegion[1];
        walkTextures[0] = GameScreen.getSpritesheet().findRegion(name);
        life     = 100;
        this.x = x;
        this.y = y;
        this.world = world;
    }

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
        this(x, y, name, world);
        walkTextures = new TextureRegion[steps];
        for (int i = 0; i < steps; i++) {
            walkTextures[i] = GameScreen.getSpritesheet().findRegion(name+""+Integer.toString(i)); 
            if (walkTextures[i]==null)
                System.err.println(name+""+Integer.toString(i));
        }
        
        specialTextures = new TextureRegion[specialSteps];
            
         for (int i = 0; i < specialSteps; i++) {
            specialTextures[i] = GameScreen.getSpritesheet().findRegion(name+""+Integer.toString(i)+"s"); 
//            if (specialTextures[i]==null)
//                System.err.println(name+""+Integer.toString(i)+"s");
        }
        life = 100;
    }
    
    public int getLife(){
        return this.life;
    }
    
    public void setLife(int life){
        this.life = life;
    }
    
    public void setTextureRegion(String name){
        walkTextures[0] = GameScreen.getSpritesheet().findRegion(name);
    }
    public void update(float delta){
        timer+=delta;
        
        if (timer>steptime){
                step++;
                timer %= steptime;
        }
        
        if (!special){
            if (step >= walkTextures.length)
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
            if(life <= 0)
                tex = walkTextures[1];
            else 
                tex = walkTextures[0];
        
        if (flip != tex.isFlipX())
           tex.flip(true, false);
        gs.getBatch().draw(tex, x, y-56);
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
        return walkTextures[step].getRegionWidth();
    }
    
    public int getHeight() {
        return walkTextures[step].getRegionHeight();
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
    
}
