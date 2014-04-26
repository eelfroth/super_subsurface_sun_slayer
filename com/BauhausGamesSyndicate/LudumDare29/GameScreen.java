package com.BauhausGamesSyndicate.LudumDare29;



import com.BauhausGamesSyndicate.LudumDare29.Underworld.Underworld;
import com.BauhausGamesSyndicate.LudumDare29.overworld.Overworld;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;

public class GameScreen implements Screen {
    private final SpriteBatch batch;
    private final BitmapFont font;
    private final Overworld overworld;
    private final Underworld underworld;
    private final FPSdiag fps;
    private final ShapeRenderer shr;
    private static TextureAtlas spritesheet;
    
    private static boolean world = false; //false: underworld, true: overworld
    private OrthographicCamera camera;
    private static Texture overlay;
    private Player player;
    
    private ShaderProgram shader;
    private FrameBuffer frameBuffer;
    private Mesh frameMesh;
    private Matrix4 worldMatrix;

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
        
        player = new Player(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
        
        //shader
        setupShader();
        
        //framebuffer
        setupFramebuffer();
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
        
        //1. render game world to framebuffer:
        frameBuffer.begin();
        {
            Gdx.gl.glClearColor(0, 0, 0, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

            camera.translate(Overworld.getCameraPos(), 0);
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
            camera.update();
            shr.setProjectionMatrix(camera.combined);
            batch.setProjectionMatrix(camera.combined);

            //overlay
            batch.begin();
            batch.draw(overlay, 0, 0);
            batch.end();
        }
        frameBuffer.end();
        
        //2. render framebuffer to frame;
        frameBuffer.getColorBufferTexture().bind();
        shader.begin();
        shader.setUniformMatrix("u_worldView", worldMatrix);
        shader.setUniformi("u_texture", 0);
        frameMesh.render(shader, GL20.GL_TRIANGLES);
        shader.end();
        
        //fps
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
    }
    
    private void setupShader() {
        ShaderProgram.pedantic = false;
        shader = new ShaderProgram(
                Gdx.files.internal("com/BauhausGamesSyndicate/LudumDare29/shaders/world.vert").readString(),
                Gdx.files.internal("com/BauhausGamesSyndicate/LudumDare29/shaders/world.frag").readString());
        if (!shader.isCompiled()) {
            Gdx.app.log("Problem loading shader:", shader.getLog());
        }
        
        worldMatrix = new Matrix4();
    }
    
    private void setupFramebuffer() {
        frameBuffer = new FrameBuffer(Pixmap.Format.RGB565 , Gdx.graphics.getWidth(), Gdx.graphics.getWidth(), false);
        frameMesh = new Mesh(true, 4, 6, VertexAttribute.Position(), VertexAttribute.ColorUnpacked(), VertexAttribute.TexCoords(0));
        frameMesh.setVertices(new float[] 
        {-1, -1, 0, 1, 1, 1, 1, 0, 0,
          1, -1, 0, 1, 1, 1, 1, 1, 0,
          1,  1, 0, 1, 1, 1, 1, 1, 1,
         -1,  1, 0, 1, 1, 1, 1, 0, 1});
        frameMesh.setIndices(new short[] {0, 1, 2, 2, 3, 0});
    }
}