package com.BauhausGamesSyndicate.LudumDare29;



import com.BauhausGamesSyndicate.LudumDare29.GameObjects.Player;
import com.BauhausGamesSyndicate.LudumDare29.Underworld.Underworld;
import com.BauhausGamesSyndicate.LudumDare29.overworld.Overworld;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class GameScreen implements Screen {
    private final SpriteBatch batch;
    private final BitmapFont font;
    private static Overworld overworld;
    private static Underworld underworld;
    private final FPSdiag fps;
    private final ShapeRenderer shr;
    private static TextureAtlas spritesheet;
    
    private static boolean world = false; //false: underworld, true: overworld
    private OrthographicCamera camera;
    private static Texture overlay;
    private final Player player;

    public GameScreen() {
        spritesheet = new TextureAtlas(Gdx.files.internal("com/BauhausGamesSyndicate/LudumDare29/assets/spritesheet.txt"));
        overlay = new Texture(Gdx.files.internal("com/BauhausGamesSyndicate/LudumDare29/assets/overlay.png"));         
        
        batch = new SpriteBatch();    
        font = new BitmapFont();
        font.setColor(Color.RED);
        fps = new FPSdiag(50, 200);
        shr = new ShapeRenderer();
        
        //y-up
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        
        shr.setProjectionMatrix(camera.combined);
        batch.setProjectionMatrix(camera.combined);
        
        //game data
        overworld = new Overworld();
        underworld = new Underworld();
        
        player = new Player(860, 500);
    }

    public void setWorld(boolean world){
        GameScreen.world = world;
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
        player.update(delta);
        Overworld.setCameraPos((int) (player.getX()-Gdx.graphics.getWidth()/2));
        
        
        //render
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        camera.translate(Overworld.getCameraPos(), 0);
        float angle=Overworld.getCameraPos()*360/(float) Overworld.getMapWidth();
        camera.rotate(-angle);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        shr.setProjectionMatrix(camera.combined);
        

        
        if (world)
            overworld.render(this);
        else
            underworld.render(this);
        batch.begin();
        player.render(this);
        batch.end();
        
        camera.translate(-Overworld.getCameraPos(), 0);
        camera.rotate(angle);
        camera.update();
        shr.setProjectionMatrix(camera.combined);
        batch.setProjectionMatrix(camera.combined);
        
        
        fps.render(shr, font);
        
         //overlay
        batch.begin();
        batch.draw(overlay, 0, 0);
        batch.end();
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

    public OrthographicCamera getCamera() {
        return camera;
    }

    /**
     *  false: underworld, true: overworld
     * @return 
     */
    public static boolean onOverworld() {
        return world;
    }

    public static void switchWorld(){
        world = !world;
        if (!world)
            underworld.enter();
    }

    public static Overworld getOverworld() {
        return overworld;
    }
}