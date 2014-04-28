package com.BauhausGamesSyndicate.LudumDare29;



import com.BauhausGamesSyndicate.LudumDare29.GameObjects.Player;
import com.BauhausGamesSyndicate.LudumDare29.Underworld.Underworld;
import com.BauhausGamesSyndicate.LudumDare29.overworld.Overworld;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;

public class GameScreen implements Screen {
    private final SpriteBatch batch;
    private final BitmapFont font;
    private static Overworld overworld;
    private static Underworld underworld;
    //private final FPSdiag fps;
    //private final ShapeRenderer shr;
    private static TextureAtlas spritesheet;
    
    private static AbstractWorld world;
    private final OrthographicCamera camera;
    private static Texture overlay;
    
    private FrameBuffer frameBuffer;
    private Mesh frameMesh;
    private final Texture debug_texture;
    private Sprite hudSprite;
    
    private boolean rotation;

    private static Player player;
    private static int money = 100;


    public GameScreen() {
        spritesheet = new TextureAtlas(Gdx.files.internal("com/BauhausGamesSyndicate/LudumDare29/assets/spritesheet.txt"));
        overlay = new Texture(Gdx.files.internal("com/BauhausGamesSyndicate/LudumDare29/assets/overlay.png"));
        debug_texture = new Texture(Gdx.files.internal("com/BauhausGamesSyndicate/LudumDare29/assets/mapping.png"));
        hudSprite = new Sprite(spritesheet.findRegion("overlord4"));
        hudSprite.setX(1980-hudSprite.getWidth());
  //      debug_texture = new Texture(Gdx.files.internal("com/BauhausGamesSyndicate/LudumDare29/assets/gruppennfohto.jpg"));
        
        
        batch = new SpriteBatch();    
        font = new BitmapFont();
        font.setColor(Color.RED);
        //fps = new FPSdiag(50, 200);
        //shr = new ShapeRenderer();
        
        //y-up
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.setToOrtho(false, 1920, 1080);
        //camera.zoom = 1/(Gdx.graphics.getWidth()/1920f);
        
        //shr.setProjectionMatrix(camera.combined);
        batch.setProjectionMatrix(camera.combined);
        
        //game data
        world = overworld = new Overworld();
        underworld = new Underworld(this);
        player = new Player(1020, 550);

        rotation = false;

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
        update(delta);
        
        
        //clear
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
                 
        //1. render game world to framebuffer:
        frameBuffer.begin();
        {
            batch.begin();
            if (world == overworld) {
                renderOverworld();
            }
            
            if (world == underworld) {
                renderUnderworld();
            }
            hudSprite.draw(batch);
            batch.end();
            
            //overlay
            renderOverlay();
        }
        frameBuffer.end();
        

        //2. render framebuffer to frame:
        renderFramebuffer(world);
        //fps
        //fps.render(shr, font);
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
        return world == overworld;
    }
    
    public static ShaderProgram setupShader(String vert, String frag) {
        //Gdx.app.log("Loading shader:", "[vert]\n"+vert+"\n[frag]\n"+frag);
        ShaderProgram shader;
        ShaderProgram.pedantic = false;
        shader = new ShaderProgram(vert, frag);
        if (!shader.isCompiled()) {
            Gdx.app.log("Problem loading shader:", shader.getLog());
        }
        
        return shader;
    }
    
    private void setupFramebuffer() {
        frameBuffer = new FrameBuffer(Pixmap.Format.RGBA8888 , Gdx.graphics.getWidth(), Gdx.graphics.getWidth(), false);
        
        //generate frameMesh
        int xQuads = 23;
        int yQuads = 23;

        float[] vertices = new float[xQuads*yQuads*36];
        short[] indices  = new short[xQuads*yQuads*6];
        
        float[] quadVertices = new float[36];
        short[] quadIndices;
        
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
    
    public static Underworld getUnderworld() {
        return underworld;
    }

    public static Player getPlayer() {
        return player;
    }

    private void renderOverlay() {
        batch.begin();
        Sprite sprite = new Sprite(overlay);
        sprite.scale(6);
        sprite.draw(batch);
        //batch.draw(overlay, 0, 0);
        batch.end();
    }

    private void renderOverworld() {
        //move camera
        camera.translate(Overworld.getCameraPos(), 0);
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        //render
        overworld.render(this);
        player.render(this);

        //move camera back
        camera.translate(-Overworld.getCameraPos(), 0);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
    }

    private void renderUnderworld() {
        underworld.render(this);
        player.render(this);
        
    }

    private void renderFramebuffer(AbstractWorld world) {

        float angle = Overworld.getCameraPos()*360/(float) Overworld.getMapWidth();
        if(rotation) world.matrix.rotate(0,0,1,-angle);
        
        world.shader.begin();
        world.shader.setUniformMatrix("u_worldView", world.matrix);
        world.shader.setUniformi("u_texture", 0);
        

        frameBuffer.getColorBufferTexture().bind();
        //debug_texture.bind();
        frameMesh.render(world.shader, GL20.GL_TRIANGLES);
        world.shader.end();
        if(rotation) world.matrix.rotate(0,0,1,angle);
    }

    public void update(float delta) {
        //fps.update(delta);
        overworld.update(delta);
        underworld.update(delta);
        
        player.update(delta);
        if(player.onOverworld()) world = overworld;
        else world = underworld;
        
        Overworld.setCameraPos((int) (player.getX()-960));
    }
}