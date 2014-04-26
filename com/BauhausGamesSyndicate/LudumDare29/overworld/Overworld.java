package com.BauhausGamesSyndicate.LudumDare29.overworld;


import com.BauhausGamesSyndicate.LudumDare29.GameObjects.AbstractEntity;
import com.BauhausGamesSyndicate.LudumDare29.GameObjects.Minion;
import com.BauhausGamesSyndicate.LudumDare29.GameScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
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
    private static Chunk[] chunks; 
    private static Sprite background;
    private final Eingang eingang;

    public Overworld() {        
        chunks = new Chunk[4];//max 4 backgroudn tiles
        for (int i = 0; i < chunks.length; i++) {
            chunks[i] = new Chunk(i);
        }
        
        eingang = new Eingang();
        
        
        background = new Sprite(new Texture(Gdx.files.internal("com/BauhausGamesSyndicate/LudumDare29/assets/bg.jpg")));
        background.scale(4);
        
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
        heightmap[65] = 300;
        heightmap[66] = 288;
        heightmap[67] = 258;
        heightmap[68] = 251;
        heightmap[69] = 258;
        heightmap[70] = 300;
        heightmap[71] = 306;
        heightmap[72] = 297;
        heightmap[73] = 300;
        heightmap[74] = 326;
        heightmap[75] = 335;
        heightmap[76] = 340;
        heightmap[77] = 351;
        heightmap[78] = 377;
        heightmap[79] = 390;
        heightmap[80] = 407;
        heightmap[81] = 425;
        heightmap[82] = 428;
        heightmap[83] = 444;
        heightmap[84] = 432;
        heightmap[85] = 413;
        heightmap[86] = 423;
        heightmap[87] = 432;
        heightmap[88] = 432;
        heightmap[89] = 435;
        heightmap[90] = 437;
        heightmap[91] = 425;
        heightmap[92] = 426;
        heightmap[93] = 432;
        heightmap[94] = 430;
        heightmap[95] = 423;
        heightmap[96] = 408;
        heightmap[97] = 393;
        heightmap[98] = 390;
        heightmap[99] = 392;
        heightmap[100] = 390;
        heightmap[101] = 395;
        heightmap[102] = 389;
        heightmap[103] = 366;
        heightmap[104] = 357;
        heightmap[105] = 345;
        heightmap[106] = 354;
        heightmap[107] = 360;
        heightmap[108] = 367;
        heightmap[109] = 370;
        heightmap[110] = 378;
        heightmap[111] = 368;
        heightmap[112] = 329;
        heightmap[113] = 282;
        heightmap[114] = 281;
        heightmap[115] = 279;
        heightmap[116] = 286;
        heightmap[117] = 298;
        heightmap[118] = 297;
        heightmap[119] = 272;
        heightmap[120] = 257;
        heightmap[121] = 253;
        heightmap[122] = 251;
        heightmap[123] = 261;
        heightmap[124] = 279;
        heightmap[125] = 299;
        heightmap[126] = 312;
        heightmap[127] = 326;
        heightmap[128] = 300;
        
        //minnions in liste füllen
        for (int i = 0; i < 10; i++){
            entityList.add(new Minion(Gdx.graphics.getWidth()/2f));
        }
        
        //place towns
        for (int i = 0; i < 200; i++){
            entityList.add(
                new City(this, (int) (Math.random() * getMapWidth()), (int) (Chunk.HEIGHT/2*Math.random()))
            );
        }
    }
    
    public void update(float delta){
        cameraPos = cameraPos % (Chunk.WIDTH*chunks.length);
        
        //update entitys
        for (int i = 0; i < entityList.size(); i++) {
            entityList.get(i).update(delta);
        }
        
    }
    
    public void render(GameScreen gs){
        gs.getBatch().begin();
        
        int y = Gdx.graphics.getHeight()-Chunk.HEIGHT; 
        gs.getCamera().translate(-Overworld.getCameraPos()/2, -y);
        gs.getCamera().update();
        gs.getBatch().setProjectionMatrix(gs.getCamera().combined);
        
        //background
         for (int i = 0; i < getMapWidth()/background.getWidth(); i++) {
            int x = (int) (i*background.getWidth());
//            int m=getMapWidth();
//            if (x < -m)
//                x += m;
//            else
//                x = x % m;
            
        if (x+background.getWidth() > Overworld.getCameraPos()/2 && x < Gdx.graphics.getWidth()+Overworld.getCameraPos()/2)
            gs.getBatch().draw(background, x, 0);
        }
         
        gs.getCamera().translate(Overworld.getCameraPos()/2, y);
        gs.getCamera().update();
        gs.getBatch().setProjectionMatrix(gs.getCamera().combined);

        
        //middleground
        for (Chunk chunk : chunks) {
            chunk.render(gs);
        }
        
        //render entitys
        for( AbstractEntity m: entityList){
            m.render(gs);
        }
        
        //eingang.render(gs);
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
     * @param x the point where you want the map height
     * @return 
     */
    public static int getHeight(int x){
        int y1 = getHeightmapValue(x*heightmap.length/getMapWidth());
        int y2 = getHeightmapValue((x+resolution())*heightmap.length/getMapWidth());
        float m=(y2-y1)/(float)(resolution());
        
        int deltaX = x%resolution();
        //y=m*x;
        return (int) (getHeightmapValue(x*heightmap.length/getMapWidth())+ resolution()*m*(deltaX/(float) resolution()));
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
    
    public Eingang getEingang() {
        return eingang;
    }

    
    public void addEntity(AbstractEntity entity){
        entityList.add(entity);
    }
}
