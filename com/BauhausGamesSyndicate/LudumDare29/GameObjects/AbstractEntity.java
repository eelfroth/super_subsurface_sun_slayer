
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
    private int step;
    private float timer = 0;
    private int steptime = 1000;//ms
    
    private TextureRegion[] textures;
    private boolean flip = false;
    
    
    public AbstractEntity(float x, float y, String name, boolean world) {
        textures = new TextureRegion[3];
        textures[0] = GameScreen.getSpritesheet().findRegion(name);
        this.x = x;
        this.y = y;
        this.world = world;
    }

    public AbstractEntity(float x, float y, String name, boolean world, int steps) {
        this(x, y, name, world);
        textures = new TextureRegion[steps];
        for (int i = 0; i < steps; i++) {
            textures[i] = GameScreen.getSpritesheet().findRegion(name+""+Integer.toString(i)); 
            if (textures[i]==null)
                System.err.println(name+""+Integer.toString(i));
        }
    }

    
    public void update(float delta){
        timer+=delta;
        if (timer>steptime){
            step++;
            timer %= steptime;
        }
        if (step > textures.length)
            step=0;

    };
    
    public void render(GameScreen gs){
        gs.getBatch().draw(textures[step], x, y);
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
        return textures[step].getRegionWidth();
    }
    
    public int getHeight() {
        return textures[step].getRegionHeight();
    }

    /**
     * Time till next sprite
     * @param steptime 
     */
    public void setSteptime(int steptime) {
        this.steptime = steptime;
    }
    
    public void setFlipHorizontal(boolean flip){
        if (flip!=this.flip) textures[step].flip(flip, false);
        this.flip=flip;
    }
    
    
    
}
