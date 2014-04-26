package com.BauhausGamesSyndicate.LudumDare29;



import com.BauhausGamesSyndicate.LudumDare29.Underworld.Underworld;
import com.BauhausGamesSyndicate.LudumDare29.overworld.Overworld;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class GameScreen implements Screen {
    private final SpriteBatch batch;
    private final BitmapFont font;
    private final Overworld overworld;
    private final Underworld underworld;
    private final FPSdiag fps;
    private final ShapeRenderer shr;
    private static TextureAtlas spritesheet;
    private boolean world = true; //false: underworld, true: overworld
    private OrthographicCamera camera;

    public GameScreen() {
        spritesheet = new TextureAtlas(Gdx.files.internal("com/BauhausGamesSyndicate/LudumDare29/assets/spritesheet.txt"));
                
        batch = new SpriteBatch();    
        font = new BitmapFont();
        font.setColor(Color.RED);
        fps = new FPSdiag(50, 200);
        shr = new ShapeRenderer();
        
        //y-up
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.set(50 / 2, 50 / 2, 0);
        
        shr.setProjectionMatrix(camera.combined);
        batch.setProjectionMatrix(camera.combined);
        
        
        overworld = new Overworld();
        underworld = new Underworld();
    }


    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }


    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void render(float delta) {
        delta *= 1000;
        
        //update
        fps.update(delta);
        if (world)
            overworld.update(delta);
        else
            underworld.update(delta);
        
        
        
        //render
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        camera.translate(Overworld.getCameraPos(), 0);
        camera.update();
        
        shr.setProjectionMatrix(camera.combined);
        batch.setProjectionMatrix(camera.combined);
        camera.translate(-Overworld.getCameraPos(), 0);
        
        if (world)
            overworld.render(this);
        else
            underworld.render(this);
        
        fps.render(shr, font);
    }

    @Override
    public void show() {
    }

    @Override
    public void hide() {
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    public BitmapFont getFont() {
        return font;
    }

    public ShapeRenderer getShapeRenderer() {
        return shr;
    }

    public static TextureAtlas getSpritesheet() {
        return spritesheet;
    }
}