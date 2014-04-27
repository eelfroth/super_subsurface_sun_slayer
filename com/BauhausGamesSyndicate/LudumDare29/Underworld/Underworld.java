package com.BauhausGamesSyndicate.LudumDare29.Underworld;

import com.BauhausGamesSyndicate.LudumDare29.GameScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;

/**
 *
 * @author Benedikt Vogler
 */
public class Underworld {
    private final Texture texture;
    private float dt;
    private final float timeTillNextBuy = 0.5f;

    public Underworld() {
        this.texture = new Texture(Gdx.files.internal("com/BauhausGamesSyndicate/LudumDare29/assets/underworld.jpg"));
    }
    
    
    /**
     * Etwas was passieren sollen wenn die Utnerwelt betreten wird.
     */
    public void enter(){
    
    }
    
    public void render(GameScreen gs){
        gs.getBatch().draw(texture, 0, 0);
        gs.getFont().setColor(new Color(1,1,1,1));
        gs.getFont().draw(gs.getBatch(), "Corpses:"+GameScreen.getMoney(), Gdx.graphics.getHeight()-500, Gdx.graphics.getWidth()-300);
    }
    
    public void update(float delta){
        if (Gdx.input.isKeyPressed(Keys.SPACE)){
            dt+=delta;
            if (dt > timeTillNextBuy && GameScreen.getPlayer().getMenupoint()==2){
                GameScreen.buyWarg();
                dt=0;
            }
        }
    }
    
}
