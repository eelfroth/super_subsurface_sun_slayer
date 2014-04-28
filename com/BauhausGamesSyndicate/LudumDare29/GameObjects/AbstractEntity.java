
package com.BauhausGamesSyndicate.LudumDare29.GameObjects;

import com.BauhausGamesSyndicate.LudumDare29.GameScreen;
import com.BauhausGamesSyndicate.LudumDare29.Tuning;
import com.badlogic.gdx.Gdx;
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
    private int step;//current animation
    private int steps;//amount of steps
    private float timer = 0;
    private int steptime = 200;//ms
    private float rotation;
    private boolean walkOnCeilingHax;
    
    private final TextureRegion[] specialTextures;
    private final TextureRegion[] standardAnimation;
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
        life = Tuning.ENTITY_LIFE;
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
        
        rotation = 0;
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
        if (special){
            tex = specialTextures[step];
            if (tex==null) Gdx.app.error("Sprites", "special texture "+step+" missing");
        }
        else {
            tex = standardAnimation[step];
            if (tex==null) Gdx.app.error("Sprites", "standard texture "+step+" missing");
        }
        
        if (flip != tex.isFlipX())
           tex.flip(true, false);
        //activateWalkOnCeilingHax();
        if(walkOnCeilingHax){
            float a=x/1000;
            //gs.getBatch().getProjectionMatrix().rotate(0, 0, 1, a);
            //Matrix4 mat = gs.getBatch().getProjectionMatrix();
            //gs.getBatch().getProjectionMatrix().rotate(1, 1, 0, a);
            gs.getBatch().draw(tex,(float)Math.sin(a)*570+Gdx.graphics.getWidth()/2+20,(float)Math.cos(a)*520+Gdx.graphics.getHeight()/2 -10);
            //gs.getBatch().getProjectionMatrix().rotate(1,1,0,-a);
            //gs.getBatch().setProjectionMatrix(mat);
        }
        else {
            //projectionMatrix.rotate(0, 0, 1, rotation);
            //gs.getBatch().setProjectionMatrix(projectionMatrix);
            gs.getBatch().draw(tex, x - getWidth()/2, y-56);
            //projectionMatrix.rotate(0, 0, 1, -rotation);
        }
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

    public void playSpecial(boolean special) {
        if (this.special!=special) {
            timer=0;
            step=0;
        }
        this.special = special;
    }
    
    public void setStep(int step) {
        this.step = step % steps;
    }
    
    public float getRotation() {
        return rotation;
    }
    
    public void setRotation(float r) {
        rotation = r;
    }
    
    public boolean getWalkOnCeilingHax() {
       return walkOnCeilingHax;
    }
    
    public void activateWalkOnCeilingHax() {
       walkOnCeilingHax = true;
       //projectionMatrix.setToOrtho2D(-Gdx.graphics.getWidth()/2, -Gdx.graphics.getHeight()/2, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }
    
    public void deactivateWalkOnCeilingHax() {
       walkOnCeilingHax = false;
    }

    public boolean isFlipped() {
        return flip;
    }
    
    
}
