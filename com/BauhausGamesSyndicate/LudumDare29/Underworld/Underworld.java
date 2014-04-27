package com.BauhausGamesSyndicate.LudumDare29.Underworld;

import com.BauhausGamesSyndicate.LudumDare29.AbstractWorld;
import com.BauhausGamesSyndicate.LudumDare29.GameObjects.AbstractEntity;
import com.BauhausGamesSyndicate.LudumDare29.GameObjects.Fledermaus;
import com.BauhausGamesSyndicate.LudumDare29.GameObjects.Slender;
import com.BauhausGamesSyndicate.LudumDare29.GameObjects.Warg;
import com.BauhausGamesSyndicate.LudumDare29.GameScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
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
    private final int timeTillNextBuy = 500;
    private final ArrayList<AbstractEntity> entityList = new ArrayList<>();
    private int money = 100;
    private int wargsTospawn;
    private int slenderTospawn;
    private int batTospawn;
    private final OrthographicCamera camera;
    private final Sound coinsound;
    private float rotation;

    public Underworld(GameScreen gs) {
        super(GameScreen.setupShader( 
                Gdx.files.internal("com/BauhausGamesSyndicate/LudumDare29/shaders/under.vert").readString(),
                Gdx.files.internal("com/BauhausGamesSyndicate/LudumDare29/shaders/under.frag").readString()), 
                new Matrix4());
        
        this.texture = new Texture(Gdx.files.internal("com/BauhausGamesSyndicate/LudumDare29/assets/underworld.jpg"));
        coinsound = Gdx.audio.newSound(Gdx.files.internal("com/BauhausGamesSyndicate/LudumDare29/assets/coin.wav"));
        
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        
        rotation = 0.0f;
        
        //debug: spawn fledermäuse am start
        for(int i=0; i<23; i++) {
            buyBat();
        }
    }
    
    
    /**
     * Etwas was passieren sollen wenn die Utnerwelt betreten wird.
     */
    public void enter(){
    
    }
    
    @Override
    public void render(GameScreen gs){
        camera.translate(0,Gdx.graphics.getHeight()/2.25f);
        camera.rotate(rotation);
        camera.update();
        gs.getBatch().setProjectionMatrix(camera.combined);
        
        gs.getBatch().draw(texture, 0, 0);
        
        camera.translate(0,-Gdx.graphics.getHeight()/2.25f);
        camera.rotate(-rotation);
        camera.update();
        gs.getBatch().setProjectionMatrix(camera.combined);
        
        gs.getFont().setColor(new Color(1,1,1,1));
        gs.getFont().draw(gs.getBatch(), "Corpses:"+getMoney(), Gdx.graphics.getHeight()-500, Gdx.graphics.getWidth()-300);
        
        for (int i = 0; i < entityList.size(); i++) {
           entityList.get(i).render(gs);
        }
    }
    
    @Override
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
            money--;
            wargsTospawn++;
            Warg warg = new Warg(false);
            warg.rise();
            entityList.add(warg);
            coinsound.play();
        }
    }
    
    public void buySlender(){
        if (money>0) {
            money--;
            slenderTospawn++;
            Slender slender = new Slender(false);
            slender.rise();
            entityList.add(slender);
            coinsound.play();
        }
    }
    public void buyBat(){
        if (money>0) {
            money--;
            batTospawn++;
            Fledermaus bat = new Fledermaus(false);
            bat.rise();
            entityList.add(bat);
            coinsound.play();
        }
    }
    
    public int getMoney() {
        return money;
    }
    
    public void addEntity(AbstractEntity entity){
        entityList.add(entity);
    }
    
    public void dispose(){
        coinsound.dispose();
    }

    public void rotate(float f) {
        //bgmatrix.rotate(0,0,1, f);
        rotation += f;
        
        
    }
}
