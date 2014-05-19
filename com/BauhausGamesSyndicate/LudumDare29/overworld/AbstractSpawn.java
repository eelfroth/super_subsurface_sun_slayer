/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.BauhausGamesSyndicate.LudumDare29.overworld;

import com.BauhausGamesSyndicate.LudumDare29.GameObjects.AbstractGameObject;
import com.BauhausGamesSyndicate.LudumDare29.GameObjects.Bauer;
import com.BauhausGamesSyndicate.LudumDare29.GameObjects.Lanze;
import com.BauhausGamesSyndicate.LudumDare29.GameObjects.Pala;
import com.BauhausGamesSyndicate.LudumDare29.GameObjects.Reiter;
import com.BauhausGamesSyndicate.LudumDare29.Tuning;

/**
 *
 * @author Paul
 */
public class AbstractSpawn extends AbstractGameObject {
    private float timer;
    private final Overworld overworld;
    
    private int overallQuantity;
    
    int maxlifetimeunits;
    
    private int maxBauern;
    private int maxLanzen;
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
        BauerQuantity   = 0;
        LanzeQuantity   = 0;
        RitterQuantity  = 0;
        PalaQuantity    = 0;
        overallQuantity = 0;
        maxBauern = 0;
        maxLanzen = 0;
        maxRitter = 0;
        maxPala   = 0;
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
                spawnEnemy();
            }
        }
    }

    private void spawnEnemy() {
       
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
