package com.BauhausGamesSyndicate.LudumDare29;



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
    private SpriteBatch batch;
    private BitmapFont font;
    private Overworld map;
    private FPSdiag fps;
    private ShapeRenderer shr;
    
    private ShaderProgram shader;
    private FrameBuffer frameBuffer;
    private Mesh frameMesh;
    
    private static TextureAtlas spritesheet;

    public GameScreen() {
        batch = new SpriteBatch();    
        font = new BitmapFont();
        font.setColor(Color.RED);
        spritesheet = new TextureAtlas(Gdx.files.internal("com/BauhausGamesSyndicate/LudumDare29/spritesheet.txt"));
        map = new Overworld();
        fps = new FPSdiag(50, 200);
        shr = new ShapeRenderer();
        
        //y-down
        OrthographicCamera hudCamera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        hudCamera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        shr.setProjectionMatrix(hudCamera.combined);
        
        //shader stuff (this is only for testing)
        String vertexShader = "attribute vec4 a_position;    \n" + 
                      "attribute vec4 a_color;\n" +
                      "attribute vec2 a_texCoord0;\n" + 
                      "uniform mat4 u_worldView;\n" + 
                      "varying vec4 v_color;" + 
                      "varying vec2 v_texCoords;" + 
                      "void main()                  \n" + 
                      "{                            \n" + 
                      "   v_color = vec4(1, 1, 1, 1); \n" + 
                      "   v_texCoords = a_texCoord0; \n" + 
                      "   gl_Position =  u_worldView * a_position;  \n"      + 
                      "}                            \n" ;
        String fragmentShader = "#ifdef GL_ES\n" +
                        "precision mediump float;\n" + 
                        "#endif\n" + 
                        "varying vec4 v_color;\n" + 
                        "varying vec2 v_texCoords;\n" + 
                        "uniform sampler2D u_texture;\n" + 
                        "void main()                                  \n" + 
                        "{                                            \n" + 
                        "  gl_FragColor = v_color * texture2D(u_texture, v_texCoords);\n" +
                        "}";

       shader = new ShaderProgram(vertexShader, fragmentShader);
       
       //Frame Buffer
       frameBuffer = new FrameBuffer(Pixmap.Format.RGB565 , Gdx.graphics.getWidth(), Gdx.graphics.getWidth(), false);
       
        frameMesh = new Mesh(true, 4, 6, VertexAttribute.Position(), VertexAttribute.  ColorUnpacked(), VertexAttribute.TexCoords(0));
        frameMesh.setVertices(new float[] 
        {-0.5f, -0.5f, 0, 1, 1, 1, 1, 0, 1,
        0.5f, -0.5f, 0, 1, 1, 1, 1, 1, 1,
        0.5f, 0.5f, 0, 1, 1, 1, 1, 1, 0,
        -0.5f, 0.5f, 0, 1, 1, 1, 1, 0, 0});
        frameMesh.setIndices(new short[] {0, 1, 2, 2, 3, 0});
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
        map.update(delta);
        
        
        
        //render
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        frameBuffer.begin();
        map.render(this);
        frameBuffer.end();
        
        //batch.begin();
        //batch.draw(frameBuffer.getColorBufferTexture(), -100,-100);
        //batch.end();
        Texture texture = frameBuffer.getColorBufferTexture();
        texture.bind();
        shader.begin();
        shader.setUniformMatrix("u_worldView", new Matrix4());
        shader.setUniformi("u_texture", 0);
        frameMesh.render(shader, GL20.GL_TRIANGLES);
        shader.end();
        
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
