package com.BauhausGamesSyndicate.LudumDare29.Underworld;

import com.BauhausGamesSyndicate.LudumDare29.AbstractWorld;
import com.BauhausGamesSyndicate.LudumDare29.GameObjects.AbstractEntity;
import com.BauhausGamesSyndicate.LudumDare29.GameObjects.Fledermaus;
import com.BauhausGamesSyndicate.LudumDare29.GameObjects.Slender;
import com.BauhausGamesSyndicate.LudumDare29.GameObjects.Warg;
import com.BauhausGamesSyndicate.LudumDare29.GameScreen;
import com.BauhausGamesSyndicate.LudumDare29.Tuning;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Matrix4;
import java.util.ArrayList;

/**
 *
 * @author Benedikt Vogler
 * @author Paul Flechsig
 * @author Jacob Bauer
 */
public class Underworld extends AbstractWorld{
    private final Texture texture;
    private float dt;
    private final int timeTillNextBuy = Tuning.TIME_BETWEEN_BUY;
    private final ArrayList<AbstractEntity> entityList = new ArrayList<>(400);//max 400 sprites
    private int money = 100;
    private final Sound buySound;
    private final Sprite[] icons;

    public Underworld(GameScreen gs) {
        super(GameScreen.setupShader( 
                Gdx.files.internal("com/BauhausGamesSyndicate/LudumDare29/shaders/under.vert").readString(),
                Gdx.files.internal("com/BauhausGamesSyndicate/LudumDare29/shaders/under.frag").readString()), 
                new Matrix4());
        
        this.texture = new Texture(Gdx.files.internal("com/BauhausGamesSyndicate/LudumDare29/assets/underworld.jpg"));
        
        
        icons = new Sprite[6];
        icons[0] = new Sprite(GameScreen.getSpritesheet().findRegion("icobat0"));
        icons[0].setX(1600);
        icons[0].setY(600);
        icons[1] = new Sprite(GameScreen.getSpritesheet().findRegion("icowarg0"));
        icons[1].setX(1500);
        icons[1].setY(100);
        icons[2] = new Sprite(GameScreen.getSpritesheet().findRegion("icoslender0"));
        icons[2].setX(180);
        icons[2].setY(600);
        icons[3] = new Sprite(GameScreen.getSpritesheet().findRegion("icobat1"));
        icons[3].setX(1600);
        icons[3].setY(600);
        icons[4] = new Sprite(GameScreen.getSpritesheet().findRegion("icowarg1"));
        icons[4].setX(1500);
        icons[4].setY(100);
        icons[5] = new Sprite(GameScreen.getSpritesheet().findRegion("icoslender1"));
        icons[5].setX(180);
        icons[5].setY(600);
        
        
        buySound = Gdx.audio.newSound(Gdx.files.internal("com/BauhausGamesSyndicate/LudumDare29/assets/coin.wav"));
        
        
        //debug: spawn fledermäuse am start
        for(int i=0; i<23; i++) {
            //buyBat();
        }
    }
    
    
    /**
     * Etwas was passieren sollen wenn die Utnerwelt betreten wird.
     */
    public void enter(){
    
    }
    
    @Override
    public void render(GameScreen gs){
        gs.getCamera().translate(0,0);
        gs.getCamera().update();
        gs.getBatch().setProjectionMatrix(gs.getCamera().combined);
        
        gs.getBatch().draw(texture, 0, 0);
        
        gs.getCamera().translate(0,0);
        gs.getCamera().update();
        gs.getBatch().setProjectionMatrix(gs.getCamera().combined);
                
        for (AbstractEntity entity : entityList) {
            entity.render(gs);
        }
        
        if (GameScreen.getPlayer().getMenupoint() == 3 && Gdx.input.isKeyPressed(Keys.SPACE))
            icons[3].draw(gs.getBatch());    
        else
            icons[0].draw(gs.getBatch());
        if (GameScreen.getPlayer().getMenupoint() == 2 && Gdx.input.isKeyPressed(Keys.SPACE))
            icons[4].draw(gs.getBatch());    
        else
            icons[1].draw(gs.getBatch());
        if (GameScreen.getPlayer().getMenupoint() == 1 && Gdx.input.isKeyPressed(Keys.SPACE))
            icons[5].draw(gs.getBatch());    
        else
            icons[2].draw(gs.getBatch());
    }
    
    @Override
    public void update(float delta){
        //update objects
        for (int i = 0; i < entityList.size(); i++) {
           entityList.get(i).update(delta);
        }
        
        //remove objects
        for (int i = 0; i < entityList.size(); i++) {
           if (entityList.get(i).isFlagRemoveFromUnderworldSet())
               entityList.remove(i);
        }
        
        
        if (!GameScreen.onOverworld() && Gdx.input.isKeyPressed(Keys.SPACE)){
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
            money-= Tuning.WARG_KOSTEN;
            Warg warg = new Warg(false);
            warg.activateWalkOnCeilingHax();
            //warg.rise();
            entityList.add(warg);
            buySound.play();
        }
    }
    
    public void buySlender(){
        if (money>0) {
            money-=Tuning.SLENDER_KOSTEN;
            Slender slender = new Slender(false);
            slender.activateWalkOnCeilingHax();
            //slender.rise();
            entityList.add(slender);
            buySound.play();
        }
    }
    public void buyBat(){
        if (money>0) {
            money-=Tuning.BAT_KOSTEN;
            Fledermaus bat = new Fledermaus(false);
            //bat.rise();
            entityList.add(bat);
            buySound.play();
        }
    }
    
    public int getMoney() {
        return money;
    }
    
    public void giveMoney( int m) {
        money += m;
    }
    
    public void addEntity(AbstractEntity entity){
        entityList.add(entity);
    }
    
    public void dispose(){
        buySound.dispose();
    }

}
