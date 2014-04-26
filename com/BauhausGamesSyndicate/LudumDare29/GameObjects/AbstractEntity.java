
package com.BauhausGamesSyndicate.LudumDare29.GameObjects;

import com.BauhausGamesSyndicate.LudumDare29.GameScreen;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 *
 * @author Benedikt Vogler
 */
public abstract class AbstractEntity extends Sprite{
    
    public AbstractEntity(float x, float y, String name) {
        super(GameScreen.getSpritesheet().findRegion(name));
        setX(x);
        setY(y);
    }
    
    public abstract void update(float delta);
    
    public void render(GameScreen gs){
        draw(gs.getBatch());
    }
}
