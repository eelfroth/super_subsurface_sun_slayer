package com.BauhausGamesSyndicate.LudumDare29.Underworld;

import com.BauhausGamesSyndicate.LudumDare29.GameObjects.AbstractEntity;
import com.BauhausGamesSyndicate.LudumDare29.GameObjects.Bat;
import com.BauhausGamesSyndicate.LudumDare29.GameObjects.Slender;
import com.BauhausGamesSyndicate.LudumDare29.GameObjects.Warg;
import com.BauhausGamesSyndicate.LudumDare29.GameScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import java.util.ArrayList;

/**
 *
 * @author Benedikt Vogler
 */
public class Underworld {
    private final Texture texture;
    private float dt;
    private final int timeTillNextBuy = 500;
    private final ArrayList<AbstractEntity> entityList = new ArrayList<>();
    private int money = 100;
    private int wargsTospawn;
    private int slenderTospawn;
    private int batTospawn;

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
        gs.getFont().draw(gs.getBatch(), "Corpses:"+getMoney(), Gdx.graphics.getHeight()-500, Gdx.graphics.getWidth()-300);
        
        for (int i = 0; i < entityList.size(); i++) {
           entityList.get(i).render(gs);
        }
    }
    
    public void update(float delta){
        //update objects
        for (int i = 0; i < entityList.size(); i++) {
           entityList.get(i).update(delta);
        }
        
        //remove objects
        for (int i = 0; i < entityList.size(); i++) {
           if (entityList.get(i).flagRemoveFromUnderworldSet())
               entityList.remove(i);
        }
        
        
        if (Gdx.input.isKeyPressed(Keys.SPACE)){
            dt+=delta;
            if (dt > timeTillNextBuy && GameScreen.getPlayer().getMenupoint()==2){
                buyWarg();
                dt=0;
            }
            if (dt > timeTillNextBuy && GameScreen.getPlayer().getMenupoint()==1){
                buySlender();
                dt=0;
            }
            if (dt > timeTillNextBuy && GameScreen.getPlayer().getMenupoint()==3){
                buyBat();
                dt=0;
            }
        }
    }
    
    public void buyWarg(){
        if (money>0) {
            money--;
            wargsTospawn++;
            Warg warg = new Warg(false);
            warg.raise();
            entityList.add(warg);
        }
    }
    
    public void buySlender(){
        if (money>0) {
            money--;
            slenderTospawn++;
            Slender slender = new Slender(false);
            slender.raise();
            entityList.add(slender);
        }
    }
    public void buyBat(){
        if (money>0) {
            money--;
            batTospawn++;
            Bat bat = new Bat(false);
            bat.raise();
            entityList.add(bat);
        }
    }
    
    public int getMoney() {
        return money;
    }
    
    public void addEntity(AbstractEntity entity){
        entityList.add(entity);
    }
}
