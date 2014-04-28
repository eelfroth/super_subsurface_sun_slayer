/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.BauhausGamesSyndicate.LudumDare29;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 *
 * @author Benedikt Vogler
 */
class TitleScreen implements Screen {
    private Sprite background;
    private Sound jingle;
    private Music title;
    private final Game ld;

    public TitleScreen(Game ld) {
        background = new Sprite(new Texture(Gdx.files.internal("com/BauhausGamesSyndicate/LudumDare29/assets/tb0.png")));
        jingle = Gdx.audio.newSound(Gdx.files.internal("com/BauhausGamesSyndicate/LudumDare29/assets/jingle.ogg"));
        title = Gdx.audio.newMusic(Gdx.files.internal("com/BauhausGamesSyndicate/LudumDare29/assets/title.ogg"));
        this.ld = ld;

    }

    @Override
    public void render(float delta) {
        SpriteBatch batch = new SpriteBatch();
        batch.begin();
        background.draw(batch);
        batch.end();
        
        if (Gdx.input.isKeyPressed(Keys.ENTER) ||Gdx.input.isKeyPressed(Keys.SPACE))
            ld.setScreen(new GameScreen());
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
    }
    
}
