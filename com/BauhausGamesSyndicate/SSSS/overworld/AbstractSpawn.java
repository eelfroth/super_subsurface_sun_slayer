package com.BauhausGamesSyndicate.SSSS.overworld;

import com.BauhausGamesSyndicate.SSSS.GameObjects.AbstractEntity;
import com.BauhausGamesSyndicate.SSSS.GameObjects.Bauer;
import com.BauhausGamesSyndicate.SSSS.GameObjects.Lanze;
import com.BauhausGamesSyndicate.SSSS.GameObjects.Pala;
import com.BauhausGamesSyndicate.SSSS.GameObjects.Reiter;
import com.BauhausGamesSyndicate.SSSS.Tuning;

/**
 *
 * @author Paul
 */
public class AbstractSpawn extends AbstractEntity {
    private float timer;
    private final Overworld overworld;
    
    private int overallQuantity;
    
    public int maxlifetimeunits;
    
    private int maxBauern =0;
    private int maxLanzen =0;
    private int maxRitter;
    private int maxPala;
    
    public int anzBauern;
    public int anzLanzen;
    public int anzRitter;
    public int anzPala;
    
    private int BauerQuantity;
    private int LanzeQuantity;
    private int RitterQuantity;
    private int PalaQuantity;
    
    
    public AbstractSpawn(Overworld overworld, int x, int y, String name) {
        super(x, y, name, true, 1,1);
        maxlifetimeunits = 20000;
        if((int)(Math.random()*10) > 5){
            this.setFlipHorizontal(true);
        }
        this.overworld = overworld;
        timer = (float)Math.random()*8000;
    }
    
    public void setMaxBauern(int max){
        this.maxBauern = max;
    }
    
    public void setMaxLanzen(int max){
        this.maxLanzen = max;
    }
    
    public void setMaxRitter(int max){
        this.maxRitter = max;
    }
    
    public void setMaxPala(int max){
        this.maxPala = max;
    }
    
    public void setBQuantity(int q){
        this.BauerQuantity = q;
    }
    
    public int getBQuantity(){
        return this.BauerQuantity;
    }
    
    public void setLQuantity(int q){
        this.LanzeQuantity = q;
    }
    
    public int getLQuantity(){
        return this.LanzeQuantity;
    }
 
    public void setRQuantity(int q){
        this.RitterQuantity = q;
    }
    
    public int getRQuantity(){
        return this.RitterQuantity;
    }
    
    public void setPQuantity(int q){
        this.PalaQuantity = q;
    }
    
    public int getPQuantity(){
        return this.PalaQuantity;
    }
    
    public void setOverallQuantity(int q){
        this.overallQuantity = q;
    }
    
    public int getOverallQuantity(){
        return this.overallQuantity;
    }
    
    @Override
    public void update(float delta){
        if(isDead()){
            playSpecial(true);
            return;
        }
        if(maxlifetimeunits > 0){
            timer+=delta;
            if (timer>=Tuning.TIME_TILL_SPAWN) {
                timer = 0;
                spawnUnit();
            }
        }
    }

    private void spawnUnit() {
       
        if(BauerQuantity != 0){
            for(int i=0; i < overallQuantity*BauerQuantity; i++){
                if(anzBauern < maxBauern){
                    Bauer enemy = new Bauer(getX()+(float)Math.random()*200,getY(), onOverworld(), this);
                    overworld.addEntity(enemy);
                    anzBauern++;
                    maxlifetimeunits--;
                }
            }
        }
        if(LanzeQuantity != 0){
            for(int i=0; i < overallQuantity*LanzeQuantity ; i++){
                if(anzLanzen < maxLanzen){
                    Lanze enemy = new Lanze(getX()+(float)Math.random()*200,getY(), onOverworld(), this);
                    overworld.addEntity(enemy);
                    anzLanzen++;
                    maxlifetimeunits--;
                }
            }
        }
        if(RitterQuantity != 0){
            for(int i=0; i < overallQuantity*RitterQuantity ; i++){
                if(anzRitter < maxRitter){
                    Reiter enemy = new Reiter(getX()+(float)Math.random()*200,getY(), onOverworld(), this);
                    overworld.addEntity(enemy);
                    anzRitter++;
                    maxlifetimeunits--;
                }
            }
        }
        if(PalaQuantity != 0){
            for(int i=0; i < overallQuantity*PalaQuantity ; i++){
                if(anzPala < maxPala){
                    Pala enemy = new Pala(getX()+(float)Math.random()*200,getY(), onOverworld(), this);
                    overworld.addEntity(enemy);
                    anzPala++;
                    maxlifetimeunits--;
                }
            }
        }
    }

    @Override
    public void onDeath() {
    }
}
