package com.BauhausGamesSyndicate.LudumDare29.overworld;


import com.BauhausGamesSyndicate.LudumDare29.AbstractEntity;
import com.BauhausGamesSyndicate.LudumDare29.GameScreen;
import com.BauhausGamesSyndicate.LudumDare29.Minion;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import java.util.ArrayList;

/**
 *
 * @author Benedikt Vogler
 */
public class Overworld {
    private static int[] heightmap;
    private static final ArrayList<AbstractEntity> entityList = new ArrayList<>();
    private static int cameraPos = 0;
    private static int anzMinions = 10;
    private static Chunk[] chunks; 
    private static Texture background;
    private final Eingang eingang;

    public Overworld() {        
        chunks = new Chunk[4];//max 4 backgroudn tiles
        for (int i = 0; i < chunks.length; i++) {
            chunks[i] = new Chunk(i);
        }
        
        eingang = new Eingang();
        
        
        background = new Texture(Gdx.files.internal("com/BauhausGamesSyndicate/LudumDare29/assets/bg.jpg"));
        
        //heightmap generieren
        Overworld.heightmap = new int[256];
//        for (int x = 0; x < heightmap.length; x++) {
//            heightmap[x] = (int) (Math.random()*Chunk.HEIGHT/2);
//        }
        heightmap[0] = 300;
        heightmap[0] = 327;
        heightmap[1] = 353;
        heightmap[2] = 376;
        heightmap[3] = 416;
        heightmap[4] = 453;
        heightmap[5] = 473;
        heightmap[6] = 501;
        heightmap[7] = 515;
        heightmap[8] = 514;
        heightmap[9] = 473;
        heightmap[10] = 452;
        heightmap[11] = 443;
        heightmap[12] = 444;
        heightmap[13] = 478;
        heightmap[14] = 501;
        heightmap[15] = 509;
        heightmap[16] = 505;
        heightmap[17] = 497;
        heightmap[18] = 495;
        heightmap[19] = 499;
        heightmap[20] = 502;
        heightmap[21] = 492;
        heightmap[22] = 478;
        heightmap[23] = 463;
        heightmap[24] = 433;
        heightmap[25] = 420;
        heightmap[26] = 392;
        heightmap[27] = 372;
        heightmap[28] = 355;
        heightmap[29] = 346;
        heightmap[30] = 318;
        heightmap[31] = 300;
        heightmap[32] = 300;
        heightmap[33] = 300;
        heightmap[34] = 341;
        heightmap[35] = 354;
        heightmap[36] = 364;
        heightmap[37] = 361;
        heightmap[38] = 371;
        heightmap[39] = 374;
        heightmap[40] = 396;
        heightmap[41] = 397;
        heightmap[42] = 396;
        heightmap[43] = 396;
        heightmap[44] = 393;
        heightmap[45] = 442;
        heightmap[46] = 484;
        heightmap[47] = 495;
        heightmap[48] = 499;
        heightmap[49] = 494;
        heightmap[50] = 465;
        heightmap[51] = 431;
        heightmap[52] = 367;
        heightmap[53] = 343;
        heightmap[54] = 344;
        heightmap[55] = 338;
        heightmap[56] = 324;
        heightmap[57] = 317;
        heightmap[58] = 316;
        heightmap[59] = 316;
        heightmap[60] = 316;
        heightmap[61] = 308;
        heightmap[62] = 300;
        heightmap[63] = 300;
        
        //minnions in liste füllen
        for (int i = 0; i <= anzMinions; i++){
            entityList.add(new Minion(Gdx.graphics.getWidth()/2f, Gdx.graphics.getHeight()/2f ) );
        }
    }
    
    public void update(float delta){
        cameraPos = cameraPos % (Chunk.WIDTH*chunks.length);
        
        for( AbstractEntity m: entityList){
            m.update(delta);
        }
        
        
    }
    
    public void render(GameScreen gs){
        gs.getBatch().begin();
        
        gs.getCamera().translate(-Overworld.getCameraPos()/2, 0);
        gs.getCamera().update();
        gs.getBatch().setProjectionMatrix(gs.getCamera().combined);
        
        //background
         int y = Gdx.graphics.getHeight()-Chunk.HEIGHT; 
         for (int i = 0; i < getMapWidth()/background.getWidth(); i++) {
            int x = i*background.getWidth();
//            int m=getMapWidth();
//            if (x < -m)
//                x += m;
//            else
//                x = x % m;
            
            if (x+background.getWidth() > Overworld.getCameraPos()/2 && x < Gdx.graphics.getWidth()+Overworld.getCameraPos()/2)
               gs.getBatch().draw(background, x, y);
        }
         
        gs.getCamera().translate(Overworld.getCameraPos()/2, 0);
        gs.getCamera().update();
        gs.getBatch().setProjectionMatrix(gs.getCamera().combined);

        
        //middleground
        for (Chunk chunk : chunks) {
            chunk.render(gs);
        }
         
        for( AbstractEntity m: entityList){
            m.render(gs);
        }
        
        eingang.render(gs);
        gs.getBatch().end();
        
        
        ShapeRenderer sh = gs.getShapeRenderer();
        sh.begin(ShapeRenderer.ShapeType.Line);
        for (int i = 0; i < heightmap.length; i++) {
            sh.line(i*resolution(), getHeightmapValue(i), (i+1)*resolution(), getHeightmapValue(i+1));
        }
        sh.end();
        
    }
    
/**
 * The resolution of the heightmap
     * @return 
 */
    public static int resolution(){
        return getMapWidth()/heightmap.length;
    }
    
   /**
    * Round
    * @param sample get a sample
    * @return 
    */
    public static int getHeightmapValue(int sample){
        int m = heightmap.length;
        int i = (sample < 0) ? (m - (Math.abs(sample) % m) ) %m : (sample % m);
        return heightmap[i];
    }
    
    /**
     * returns the interpolatet height
     * @param x
     * @return 
     */
    public static int getHeight(int x){
        return getHeightmapValue(x*64/getMapWidth()*resolution());
    }
    
    public static int getMapWidth(){
        return Chunk.WIDTH*chunks.length;
    }

    public static int getCameraPos() {
        return cameraPos;
    }

    public static void setCameraPos(int cameraPos) {
        Overworld.cameraPos = cameraPos;
    }
    

}
