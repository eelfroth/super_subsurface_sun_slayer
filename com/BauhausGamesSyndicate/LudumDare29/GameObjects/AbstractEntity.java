
package com.BauhausGamesSyndicate.LudumDare29.GameObjects;

import com.BauhausGamesSyndicate.LudumDare29.GameScreen;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 *
 * @author Benedikt Vogler
 */
public abstract class AbstractEntity extends Sprite {
    private boolean world;//false: underworld, true: overworld
    private boolean flagRemoveFromUnderworld;
    private boolean flagRemoveFromOverworld;
    
    public AbstractEntity(float x, float y, String name, boolean world) {
        super(GameScreen.getSpritesheet().findRegion(name));
        setX(x);
        setY(y);
        this.world = world;
    }
    
    public abstract void update(float delta);
    
    public void render(GameScreen gs){
        draw(gs.getBatch());
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
    
    
    
    
}
