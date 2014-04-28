/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.BauhausGamesSyndicate.LudumDare29.overworld;

import com.BauhausGamesSyndicate.LudumDare29.GameObjects.AbstractEntity;
import com.BauhausGamesSyndicate.LudumDare29.GameObjects.Bauer;
import com.BauhausGamesSyndicate.LudumDare29.GameObjects.Lanze;
import com.BauhausGamesSyndicate.LudumDare29.GameObjects.Pala;
import com.BauhausGamesSyndicate.LudumDare29.GameObjects.Ritter;
import com.BauhausGamesSyndicate.LudumDare29.Tuning;

/**
 *
 * @author Paul
 */
public class AbstractSpawn extends AbstractEntity {
    private float timer;
    private final Overworld overworld;
    
    private int overallQuantity;
    
    private int BauerQuantity;
    private int LanzeQuantity;
    private int RitterQuantity;
    private int PalaQuantity;
    
    
    public AbstractSpawn(Overworld overworld, int x, int y, String name) {
        super(x, y, name, true, 1,1);  

        if((int)(Math.random()*10) > 5){
            this.setFlipHorizontal(true);
        }
        this.overworld = overworld;
        BauerQuantity   = 0;
        LanzeQuantity   = 0;
        RitterQuantity  = 0;
        PalaQuantity    = 0;
        overallQuantity = 0;
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
        timer+=delta;
        if (timer>=Tuning.TIME_TILL_SPAWN) {
            timer = 0;
            spawnEnemy();
        }
    }

    private void spawnEnemy() {
       
        for(int i=0; i < BauerQuantity/100 * overallQuantity ; i++){
            Bauer enemy = new Bauer(getX(),getY(), onOverworld());
            overworld.addEntity(enemy);
        }
        for(int i=0; i < LanzeQuantity/100 * overallQuantity ; i++){
            Lanze enemy = new Lanze(getX(),getY(), onOverworld());
            overworld.addEntity(enemy);
        }
        for(int i=0; i < RitterQuantity/100 * overallQuantity ; i++){
            Ritter enemy = new Ritter(getX(),getY(), onOverworld());
            overworld.addEntity(enemy);
        }
        for(int i=0; i < PalaQuantity/100 * overallQuantity ; i++){
            Pala enemy = new Pala(getX(),getY(), onOverworld());
            overworld.addEntity(enemy);
        }
    }
}
