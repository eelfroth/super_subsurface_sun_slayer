package com.BauhausGamesSyndicate.LudumDare29;



import com.BauhausGamesSyndicate.LudumDare29.GameObjects.Player;
import com.BauhausGamesSyndicate.LudumDare29.Underworld.Underworld;
import com.BauhausGamesSyndicate.LudumDare29.overworld.Overworld;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
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
    private static Sprite tutorial;
    private boolean tutorialvisible = true;
    
    private FrameBuffer frameBuffer;
    private Mesh frameMesh;
    
    private final Texture debug_texture;
    private final Sprite hudSpriteR;
    private final Sprite hudSpriteLbg;
    
    private static Music underworldMusic;
    private static Music overworldMusic;

    private static Player player;
    private static int money = 100;
    private boolean shaderActivated = true;
    private boolean shaderkeyup;

    
     

    public GameScreen() {
        spritesheet = new TextureAtlas(Gdx.files.internal("com/BauhausGamesSyndicate/LudumDare29/assets/spritesheet.txt"));
        overlay = new Texture(Gdx.files.internal("com/BauhausGamesSyndicate/LudumDare29/assets/overlay.png"));
        debug_texture = new Texture(Gdx.files.internal("com/BauhausGamesSyndicate/LudumDare29/assets/mapping.png"));
        
        tutorial = new Sprite(new Texture(Gdx.files.internal("com/BauhausGamesSyndicate/LudumDare29/assets/tutorial.png")));
        tutorial.setX(541);
        tutorial.setY(300);
        
        hudSpriteR = new Sprite(spritesheet.findRegion("HUDR"));
        hudSpriteR.setX(1980-hudSpriteR.getWidth());
        hudSpriteR.setY(1080-hudSpriteR.getHeight());
        
        hudSpriteLbg = new Sprite(spritesheet.findRegion("HUDLbg"));
        hudSpriteLbg.setX(0);
        hudSpriteLbg.setY(1080-hudSpriteR.getHeight());
        
  //      debug_texture = new Texture(Gdx.files.internal("com/BauhausGamesSyndicate/LudumDare29/assets/gruppennfohto.jpg"));
        
        GameScreen.underworldMusic = Gdx.audio.newMusic(Gdx.files.internal("com/BauhausGamesSyndicate/LudumDare29/assets/underworldLoop.ogg"));
        underworldMusic.setLooping(true);
        underworldMusic.play();
        GameScreen.overworldMusic = Gdx.audio.newMusic(Gdx.files.internal("com/BauhausGamesSyndicate/LudumDare29/assets/overworldLoop.ogg"));
        overworldMusic.setLooping(true);
        
        batch = new SpriteBatch();    
        font = new BitmapFont();
        font.setColor(Color.RED);
        font.scale(2);
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


        //framebuffer
        if(shaderActivated) setupFramebuffer();
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
           
        if (world == overworld) {
            //1. render game world to framebuffer:
            if (shaderActivated) frameBuffer.begin();
            batch.begin();
            
                renderOverworld();
                
            batch.end();
            if (shaderActivated) frameBuffer.end(); 
            
            //2. render framebuffer to frame:
            if (shaderActivated) renderFramebuffer(world);
        } else {
            batch.begin();
            renderUnderworld();
        }
        
        if (world == overworld)
            batch.begin();
        
        //overlay & hud
        renderOverlay(batch);
        if (tutorialvisible)
            tutorial.draw(batch);
        
        hudSpriteR.draw(batch);
        font.setColor(new Color(1,1,1,1));
        font.draw(batch, Integer.toString(underworld.getMoney()), 1750, 960);
        hudSpriteLbg.draw(batch);
        font.draw(batch, Integer.toString((int) player.getLife()), 60, 930);
        
        batch.end();
            
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

    private void renderOverlay(SpriteBatch batch) {
        Sprite sprite = new Sprite(overlay);
        sprite.scale(6);
        sprite.draw(batch);
        //batch.draw(overlay, 0, 0);
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

        //float angle = Overworld.getCameraPos()*360/(float) Overworld.getMapWidth();
       // if(rotation) world.matrix.rotate(0,0,1,-angle);
        
        world.shader.begin();
        world.shader.setUniformMatrix("u_worldView", world.matrix);
        world.shader.setUniformi("u_texture", 0);
        

        frameBuffer.getColorBufferTexture().bind();
        //debug_texture.bind();
        frameMesh.render(world.shader, GL20.GL_TRIANGLES);
        world.shader.end();
        //if(rotation) world.matrix.rotate(0,0,1,angle);
    }

    public void update(float delta) {
        //fps.update(delta);
        if (Gdx.input.isKeyPressed(Input.Keys.S) ||
            Gdx.input.isKeyPressed(Input.Keys.W) ||
            Gdx.input.isKeyPressed(Input.Keys.D) ||
            Gdx.input.isKeyPressed(Input.Keys.A) ||
            Gdx.input.isKeyPressed(Input.Keys.SPACE) ||
            Gdx.input.isKeyPressed(Input.Keys.ENTER) ||
            Gdx.input.isKeyPressed(Input.Keys.Q) ||
            Gdx.input.isKeyPressed(Input.Keys.E)
            ){
            tutorialvisible = false;
        }
        
        if (shaderkeyup && Gdx.input.isKeyPressed(Input.Keys.C)){
            shaderActivated = !shaderActivated;
            shaderkeyup=false;
            
        }
        
        if (!Gdx.input.isKeyPressed(Input.Keys.C))
            shaderkeyup=true;
        
        
        
        overworld.update(delta);
        underworld.update(delta);
        
        player.update(delta);
        if(player.onOverworld())
            world = overworld;
        else
            world = underworld;
        
        Overworld.setCameraPos((int) (player.getX()-960));
    }
    
    public static void switchWorld(boolean world){
        if (world){
            underworldMusic.stop();
            overworldMusic.play();
        }else {
            overworldMusic.stop();
            underworldMusic.play();
        }
    }
}