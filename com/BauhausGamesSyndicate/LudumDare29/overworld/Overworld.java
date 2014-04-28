package com.BauhausGamesSyndicate.LudumDare29.overworld;


import com.BauhausGamesSyndicate.LudumDare29.AbstractWorld;
import com.BauhausGamesSyndicate.LudumDare29.GameObjects.AbstractEntity;
import com.BauhausGamesSyndicate.LudumDare29.GameScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import java.util.ArrayList;

/**
 *
 * @author Benedikt Vogler
 * @author Paul Flechsig
 * @author Jacob Bauer
 */
public class Overworld extends AbstractWorld{
    private static int[] heightmap;
    
    // x - Positionen der Städte
    private static int[] citymapX;
    private static int[] citymapY;
    private static int[] forestmapX;
    private static int[] forestmapY;
    private final int anzCitys   = 8;
    private final int anzForests = 11;
    
    private static final ArrayList<AbstractEntity> entityList = new ArrayList<>(400);//max 400 sprites
    private static int cameraPos = 0;
    private static Chunk[] chunks; 
    private static Sprite background;
    private final Eingang eingang;
    

    public Overworld() { 
        super(GameScreen.setupShader( 
                Gdx.files.internal("com/BauhausGamesSyndicate/LudumDare29/shaders/world.vert").readString(),
                Gdx.files.internal("com/BauhausGamesSyndicate/LudumDare29/shaders/world.frag").readString()), 
                new Matrix4());
        matrix.scale(3.0f, 1.2f, 1.0f);
        
        chunks = new Chunk[4];//max 4 backgroudn tiles
        for (int i = 0; i < chunks.length; i++) {
            chunks[i] = new Chunk(i);
        }
        
        eingang = new Eingang();

        background = new Sprite(new Texture(Gdx.files.internal("com/BauhausGamesSyndicate/LudumDare29/assets/bg.jpg")));
        //background.scale(4);
        
        //heightmap generieren
        Overworld.heightmap = new int[256];
        Overworld.citymapX = new int[anzCitys];
        Overworld.citymapY = new int[anzCitys];
        Overworld.forestmapX = new int[anzForests];
        Overworld.forestmapY = new int[anzForests];
//        for (int x = 0; x < heightmap.length; x++) {
//            heightmap[x] = (int) (Math.random()*Chunk.HEIGHT/2);
//        }
        citymapX[0] = 530;
        citymapY[0] = 400;
        citymapX[1] = 880;
        citymapY[1] = 450;
        citymapX[2] = 1200;
        citymapY[2] = 500;
        citymapX[3] = 1100;
        citymapY[3] = 360;
        citymapX[4] = 2940;
        citymapY[4] = 485;
        citymapX[5] = 3400;
        citymapY[5] = 320;
        citymapX[6] = 3600;
        citymapY[6] = 300;
        citymapX[7] = 3850;
        citymapY[7] = 330;
        
        forestmapX[0] = 300;
        forestmapY[0] = 430;
        forestmapX[1] = 1000;
        forestmapY[1] = 440;
        forestmapX[2] = 2550;
        forestmapY[2] = 420;
        forestmapX[3] = 1200;
        forestmapY[3] = 340;
        forestmapX[4] = 3000;
        forestmapY[4] = 400;
        forestmapX[5] = 3400;
        forestmapY[5] = 220;
        forestmapX[6] = 3600;
        forestmapY[6] = 200;
        forestmapX[7] = 3850;
        forestmapY[7] = 230;
        forestmapX[8] = 3500;
        forestmapY[8] = 200;
        forestmapX[9] = 3200;
        forestmapY[9] = 250;
        forestmapX[10] = 3650;
        forestmapY[10] = 270;
        
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
        heightmap[31] = 360;
        heightmap[32] = 380;
        heightmap[33] = 385;
        heightmap[34] = 370;
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
        
        heightmap[129] = 284;
        heightmap[130] = 263;
        heightmap[131] = 230;
        heightmap[132] = 200;
        heightmap[133] = 163;
        heightmap[134] = 129;
        heightmap[135] = 118;
        heightmap[136] = 121;
        heightmap[137] = 133;
        heightmap[138] = 132;
        heightmap[139] = 153;
        heightmap[140] = 165;
        heightmap[141] = 188;
        heightmap[142] = 218;
        heightmap[143] = 238;
        heightmap[144] = 261;
        heightmap[145] = 294;
        heightmap[146] = 338;
        heightmap[147] = 356;
        heightmap[148] = 386;
        heightmap[149] = 415;
        heightmap[150] = 439;
        heightmap[151] = 464;
        heightmap[152] = 490;
        heightmap[153] = 515;
        heightmap[154] = 537;
        heightmap[155] = 555;
        heightmap[156] = 541;
        heightmap[157] = 505;
        heightmap[158] = 483;
        heightmap[159] = 465;
        heightmap[160] = 471;
        heightmap[161] = 440;
        heightmap[162] = 436;
        heightmap[163] = 460;
        heightmap[164] = 471;
        heightmap[165] = 490;
        heightmap[166] = 537;
        heightmap[167] = 546;
        heightmap[168] = 526;
        heightmap[169] = 502;
        heightmap[170] = 478;
        heightmap[171] = 450;
        heightmap[172] = 426;
        heightmap[173] = 398;
        heightmap[174] = 370;
        heightmap[175] = 369;
        heightmap[176] = 380;
        heightmap[177] = 364;
        heightmap[178] = 347;
        heightmap[179] = 329;
        heightmap[180] = 315;
        heightmap[181] = 306;
        heightmap[182] = 296;
        heightmap[183] = 293;
        heightmap[184] = 294;
        heightmap[185] = 289;
        heightmap[186] = 275;
        heightmap[187] = 278;
        heightmap[188] = 271;
        heightmap[189] = 283;
        heightmap[190] = 290;
        heightmap[191] = 295;
        heightmap[192] = 300;
        
        heightmap[193] = 279;
        heightmap[194] = 249;
        heightmap[195] = 216;
        heightmap[196] = 201;
        heightmap[197] = 195;
        heightmap[198] = 195;
        heightmap[199] = 204;
        heightmap[200] = 204;
        heightmap[201] = 204;
        heightmap[202] = 183;
        heightmap[203] = 148;
        heightmap[204] = 212;
        heightmap[205] = 259;
        heightmap[206] = 316;
        heightmap[207] = 365;
        heightmap[208] = 370;
        heightmap[209] = 378;
        heightmap[210] = 404;
        heightmap[211] = 467;
        heightmap[212] = 525;
        heightmap[213] = 580;
        heightmap[214] = 618;
        heightmap[215] = 662;
        heightmap[216] = 671;
        heightmap[217] = 672;
        heightmap[218] = 673;
        heightmap[219] = 658;
        heightmap[220] = 649;
        heightmap[221] = 640;
        heightmap[222] = 630;
        heightmap[223] = 620;
        heightmap[224] = 612;
        heightmap[225] = 606;
        heightmap[226] = 611;
        heightmap[227] = 596;
        heightmap[228] = 556;
        heightmap[229] = 446;
        heightmap[230] = 410;
        heightmap[231] = 406;
        heightmap[232] = 406;
        heightmap[233] = 404;
        heightmap[234] = 399;
        heightmap[235] = 390;
        heightmap[236] = 386;
        heightmap[237] = 373;
        heightmap[238] = 356;
        heightmap[239] = 344;
        heightmap[240] = 341;
        heightmap[241] = 346;
        heightmap[242] = 349;
        heightmap[243] = 354;
        heightmap[244] = 364;
        heightmap[245] = 372;
        heightmap[246] = 376;
        heightmap[247] = 373;
        heightmap[248] = 369;
	heightmap[248] = 359;
        heightmap[249] = 329;
        heightmap[250] = 306;
        heightmap[251] = 292;
        heightmap[252] = 285;
        heightmap[253] = 282;
        heightmap[254] = 292;
        heightmap[255] = 300;

        
        //place towns
        for (int i = 0; i < anzCitys; i++){
            
            entityList.add(
                new Bauernhof(this, citymapX[i], citymapY[i])
            );
            // (int) (Chunk.HEIGHT/4*Math.random()+Chunk.HEIGHT/4)
        }
        
        //place Forrests
        for (int i = 0; i < anzForests; i++){
            entityList.add(
                new Forrest(this, forestmapX[i], forestmapY[i])
            );
        }
        // add Bauernhof 2
        entityList.add(new Bauernhof2(this, 4000, 300));
        
        // add CastleOfLight
        entityList.add(new CastleOfLight(this, 10306, 520));
    }
    
    @Override
    public void update(float delta){
        cameraPos = cameraPos % (Chunk.WIDTH*chunks.length);
        
        //update entitys
        for (int i = 0; i < entityList.size(); i++) {
            entityList.get(i).update(delta);
        }

        //remove objects
        for (int i = 0; i < entityList.size(); i++) {
           if (entityList.get(i).flagRemoveFromOverworldSet())
               entityList.remove(i);
        }
    }
    
    @Override
    public void render(GameScreen gs){
        //int y = 1080-Chunk.HEIGHT; 
        gs.getCamera().translate(-Overworld.getCameraPos()/2, 0);//half speed for parralax
        gs.getCamera().update();
        gs.getBatch().setProjectionMatrix(gs.getCamera().combined);
        
        //background
         for (int i = 0; i < getMapWidth()/background.getWidth(); i++) {
            int x = (int) (i*background.getWidth());
            int m=getMapWidth();
            
            int cc = Overworld.getCameraPos()/4;//current chunk 0-3

            if (i >cc+1)
                x-=m/2;
        
            if (x < -m)
                x += m;
            else
                x = x % m;
            

            
        if (x+background.getWidth() > Overworld.getCameraPos()/2 && x < 1920+Overworld.getCameraPos()/2)
            gs.getBatch().draw(background, x, 0);
        }
         
        gs.getCamera().translate(Overworld.getCameraPos()/2, 0);
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

    public ArrayList<AbstractEntity> getEntityList() {
        return entityList;
    }
    
    
    public void renderShapes(ShapeRenderer sh){
        //eingang.render(gs);
        sh.begin(ShapeRenderer.ShapeType.Line);
        for (int i = 0; i < heightmap.length; i++) {
            sh.line(i*resolution(), getHeightmapValue(i), (i+1)*resolution(), getHeightmapValue(i+1));
        }
        sh.end();
    }
}
