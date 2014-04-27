package com.BauhausGamesSyndicate.LudumDare29;



import com.BauhausGamesSyndicate.LudumDare29.GameObjects.Player;
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
import static java.lang.Math.floor;
import java.util.ArrayList;
import java.util.List;

public class GameScreen implements Screen {
    private static int wargsTospawn;
    private static int slenderTospawn;
    private static int batTospawn;
    private final SpriteBatch batch;
    private final BitmapFont font;
    private static Overworld overworld;
    private static Underworld underworld;
    private final FPSdiag fps;
    private final ShapeRenderer shr;
    private static TextureAtlas spritesheet;
    
    private static boolean world = false; //false: underworld, true: overworld
    private final OrthographicCamera camera;
    private static Texture overlay;
    
    private ShaderProgram shader;
    private FrameBuffer frameBuffer;
    private Mesh frameMesh;
    private Matrix4 worldMatrix;
    private final Texture debug_texture;
    private float world_streckfaktor;
    private final float world_ypos;

    private static Player player;
    private static int money = 100;
    private boolean rotation;


    public GameScreen() {
        spritesheet = new TextureAtlas(Gdx.files.internal("com/BauhausGamesSyndicate/LudumDare29/assets/spritesheet.txt"));
        overlay = new Texture(Gdx.files.internal("com/BauhausGamesSyndicate/LudumDare29/assets/overlay.png"));
        debug_texture = new Texture(Gdx.files.internal("com/BauhausGamesSyndicate/LudumDare29/assets/mapping.png"));
        
        
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

        //parable settings:
        world_streckfaktor = 0.25f;
        world_ypos = +0.0f;
        rotation = false;
        
        //shader
        setupShader();
        
        //framebuffer
        setupFramebuffer();
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
        overworld.update(delta);
        underworld.update(delta);
        
        player.update(delta);
        Overworld.setCameraPos((int) (player.getX()-Gdx.graphics.getWidth()/2));
        
        
        //render
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        
                
        //1. render game world to framebuffer:
        
        frameBuffer.begin();
        {
            camera.translate(Overworld.getCameraPos(), 0);
            //camera.rotate(-angle);
            camera.update();
            batch.setProjectionMatrix(camera.combined);
            shr.setProjectionMatrix(camera.combined);

            batch.begin();
            {
                if (world)
                    overworld.render(this);
                else
                    underworld.render(this);
                player.render(this);
            }
            batch.end();

            
            camera.translate(-Overworld.getCameraPos(), 0);
            //camera.rotate(angle);
            camera.update();
            shr.setProjectionMatrix(camera.combined);
            batch.setProjectionMatrix(camera.combined);
        }
        frameBuffer.end();
        

        //2. render framebuffer to frame:
        
        
        
        
        shader.begin();
        frameBuffer.getColorBufferTexture().bind();
        //debug_texture.bind();
        
        //camera.rotate(-angle);
        //batch.setProjectionMatrix(camera.combined);
        //shr.setProjectionMatrix(camera.combined);
        float angle = Overworld.getCameraPos()*360/(float) Overworld.getMapWidth();
        if(rotation) worldMatrix.rotate(0,0,1,-angle);
        
        shader.setUniformMatrix("u_worldView", worldMatrix);
        shader.setUniformi("u_texture", 0);
        shader.setUniformf("f_stauchfaktor", world_streckfaktor);
        shader.setUniformf("f_ypos", world_ypos);
        //shader.setUniformf("f_resfactor", (float)Gdx.graphics.getWidth()/(float)Gdx.graphics.getHeight());
        /*
        StringBuffer result = new StringBuffer();
        result.append((float)Gdx.graphics.getHeight()/(float)Gdx.graphics.getWidth());
        String mynewstring = result.toString();
        Gdx.app.log("f_resfactor:", mynewstring);
          */  
        frameMesh.render(shader, GL20.GL_TRIANGLES);
        
        //camera.rotate(angle);
        //batch.setProjectionMatrix(camera.combined);
        //shr.setProjectionMatrix(camera.combined);
        
        if(rotation) worldMatrix.rotate(0,0,1,angle);
        shader.end();
        
        
        
        //overlay
        batch.begin();
        batch.draw(overlay, 0, 0);
        batch.end();
        
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
        if (!world)
            underworld.enter();
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
        
        //generate frameMesh
        int xQuads = 19;
        int yQuads = 10;

        float[] vertices = new float[xQuads*yQuads*36];
        short[] indices  = new short[xQuads*yQuads*6];
        
        float[] quadVertices = new float[36];
        short[] quadIndices = new short[6];
        
        boolean backwards = true;
        
        for(int y = 0; y < yQuads; y++) {
            for(int x = 0; x < xQuads; x++) {
                if(backwards) {
                    quadVertices = new float []
                    {(x/(float)xQuads)                    *2-1, (y/(float)yQuads)                    *2-1, 0, 1, 1, 1, 1, (x/(float)xQuads)                    , (y/(float)yQuads),
                     (x/(float)xQuads + (1/(float)xQuads))*2-1, (y/(float)yQuads)                    *2-1, 0, 1, 1, 1, 1, (x/(float)xQuads + (1/(float)xQuads)), (y/(float)yQuads),
                     (x/(float)xQuads + (1/(float)xQuads))*2-1, (y/(float)yQuads + (1/(float)yQuads))*2-1, 0, 1, 1, 1, 1, (x/(float)xQuads + (1/(float)xQuads)), (y/(float)yQuads + (1/(float)yQuads)),
                     (x/(float)xQuads)                    *2-1, (y/(float)yQuads + (1/(float)yQuads))*2-1, 0, 1, 1, 1, 1, (x/(float)xQuads)                    , (y/(float)yQuads + (1/(float)yQuads))};
                }
                System.arraycopy(quadVertices, 0, vertices, 36*(x+xQuads*y), 36);

                short i = (short)((x+xQuads*y)*4);
                quadIndices = new short[]
                {(short) (0 + i), (short) (1 + i), (short) (2 + i), (short) (2 + i), (short) (3 + i), (short) (0 + i)};  
                System.arraycopy(quadIndices, 0, indices, 6*(x+xQuads*y), 6);
            }
            //backwards = !backwards;
        }
        
        frameMesh = new Mesh(true, xQuads*yQuads*4, xQuads*yQuads*6, VertexAttribute.Position(), VertexAttribute.ColorUnpacked(), VertexAttribute.TexCoords(0));
        frameMesh.setVertices(vertices);
        frameMesh.setIndices(indices);
        /*frameMesh.setVertices(new float []
                    {-0.1f-1, -0.1f, 0, 1, 1, 1, 1, 0, 0,
                     0.1f-1, -0.1f, 0, 1, 1, 1, 1, 1, 0,
                     0.1f-1, 0.1f, 0, 1, 1, 1, 1, 1, 1,
                     -0.1f-1, 0.1f, 0, 1, 1, 1, 1, 0, 1});
        frameMesh.setIndices(new short[] {0,1,2,2,3,0});*/
        
        boolean debug=false;
        
        //debug
        if (debug){
            StringBuffer result = new StringBuffer();
            for (int i = 0; i < vertices.length; i++) {
               if((i)%9 == 0) result.append("\n");
               if((i)%36 == 0)  {
                   result.append("Quad ");
                   result.append(i/36);
                   result.append(":\n");
               }
               result.append( vertices[i] );
               result.append("\t");

            }
            String mynewstring = result.toString();
            Gdx.app.log("frameMesh vertices:", mynewstring + "\n");

            result = new StringBuffer();
            for (int i = 0; i < indices.length; i++) {
               //if((i)%9 == 0) result.append("\n");
               if((i)%6 == 0)  {
                   result.append("\nQuad ");
                   result.append(i/6);
                   result.append(":\n");
               }
               result.append( indices[i] );
               result.append("\t");

            }
            mynewstring = result.toString();
            Gdx.app.log("frameMesh indices:", mynewstring);
        }
    }

    public static Overworld getOverworld() {
        return overworld;
    }

    public static Player getPlayer() {
        return player;
    }
    
    public static void buyWarg(){
        money--;
        wargsTospawn++;
    }
    
    public static void buySlender(){
        money--;
        slenderTospawn++;
    }
    public static void buyBat(){
        money--;
        batTospawn++;
    }

    public static int getMoney() {
        return money;
    }
    
    
}