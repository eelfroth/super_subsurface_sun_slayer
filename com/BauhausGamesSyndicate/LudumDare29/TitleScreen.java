/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.BauhausGamesSyndicate.LudumDare29;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector3;

/**
 *
 * @author Benedikt Vogler
 */
class TitleScreen implements Screen {
    private static TextureAtlas spritesheet;
    private Sprite background;
    private Sound jingle;
    private Music title;
    private final Game ld;
    private Sprite currentBurg;
    private final SpriteBatch batch;
    private final OrthographicCamera camera;
    private float timer = 0;
    private int step = 0;

    public TitleScreen(Game ld) {
        spritesheet = new TextureAtlas(Gdx.files.internal("com/BauhausGamesSyndicate/LudumDare29/assets/title/spritesheet.txt"));
        background = new Sprite(spritesheet.findRegion("titelbildschirm"));
        
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1920, 1080);
        
        jingle = Gdx.audio.newSound(Gdx.files.internal("com/BauhausGamesSyndicate/LudumDare29/assets/title/jingle.ogg"));
        title = Gdx.audio.newMusic(Gdx.files.internal("com/BauhausGamesSyndicate/LudumDare29/assets/title/title.ogg"));
        this.ld = ld;
        currentBurg = new Sprite(spritesheet.findRegion("tb0"));
        currentBurg.setX(940);
        currentBurg.setY(440);

    }

    @Override
    public void render(float delta) {
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        
        background.draw(batch);
        currentBurg.draw(batch);
        batch.end();
        
        if (Gdx.input.isKeyPressed(Keys.ENTER) ||
            Gdx.input.isKeyPressed(Keys.SPACE)){
            ld.setScreen(new GameScreen());
            dispose();
        }
        
        if (Gdx.input.isKeyPressed(Keys.F)){
            Gdx.graphics.setDisplayMode(Gdx.graphics.getDesktopDisplayMode());
        }
        
        if (Gdx.input.isKeyPressed(Keys.ESCAPE)){
            dispose();
            Gdx.app.exit();
        }
        
        delta*=1000;
         timer+=delta;
        
         if (timer>200){
            step++;
            timer=0;
         }

        if (step > 7){
            step=0;
        }
        
        currentBurg = new Sprite(spritesheet.findRegion("tb"+step));
        currentBurg.setX(940);
        currentBurg.setY(440);
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
        jingle.play();
        title.play();
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        title.stop();
        title.dispose();
    }
    
}
