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
        heightmap[1] = 327;
        heightmap[2] = 353;
        heightmap[3] = 376;
        heightmap[4] = 416;
        heightmap[5] = 453;
        heightmap[6] = 473;
        heightmap[7] = 501;
        heightmap[8] = 515;
        heightmap[9] = 514;
        heightmap[10]= 473;
        heightmap[11] = 452;
        heightmap[12] = 443;
        heightmap[13] = 444;
        heightmap[14] = 478;
        heightmap[15] = 501;
        heightmap[16] = 509;
        heightmap[17] = 505;
        heightmap[18] = 497;
        heightmap[19] = 495;
        heightmap[20] = 499;
        heightmap[21] = 502;
        heightmap[22] = 492;
        heightmap[23] = 478;
        heightmap[24] = 463;
        heightmap[25] = 433;
        heightmap[26] = 420;
        heightmap[27] = 392;
        heightmap[28] = 372;
        heightmap[29] = 355;
        heightmap[30] = 346;
        heightmap[31] = 318;
        heightmap[32] = 300;
        heightmap[33] = 300;
        heightmap[34] = 300;
        heightmap[35] = 341;
        heightmap[36] = 354;
        heightmap[37] = 364;
        heightmap[38] = 361;
        heightmap[39] = 371;
        heightmap[40] = 374;
        heightmap[41] = 396;
        heightmap[42] = 397;
        heightmap[43] = 396;
        heightmap[44] = 396;
        heightmap[45] = 393;
        heightmap[46] = 442;
        heightmap[47] = 484;
        heightmap[48] = 495;
        heightmap[49] = 499;
        heightmap[50] = 494;
        heightmap[51] = 465;
        heightmap[52] = 431;
        heightmap[53] = 367;
        heightmap[54] = 343;
        heightmap[55] = 344;
        heightmap[56] = 338;
        heightmap[57] = 324;
        heightmap[58] = 317;
        heightmap[59] = 316;
        heightmap[60] = 316;
        heightmap[61] = 316;
        heightmap[62] = 308;
        heightmap[63] = 300;
        heightmap[64] = 300;
        
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
        return heightmap[i]+1080-Chunk.HEIGHT;
    }
    
    /**
     * returns the interpolatet height
     * @param x
     * @return 
     */
    public static int getHeight(int x){
        return getHeightmapValue(x*heightmap.length/getMapWidth());
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
