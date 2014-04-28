package com.BauhausGamesSyndicate.LudumDare29.GameObjects;

import com.BauhausGamesSyndicate.LudumDare29.GameScreen;
import com.badlogic.gdx.Gdx;

/**
 *
 * @author Benedikt Vogler
 * @author Paul Flechsig
 * @author Jacob Bauer
 */
public class Warg extends Minion {

    public Warg(boolean world) {
        super(
            GameScreen.onOverworld()?GameScreen.getOverworld().getEingang().getX():Gdx.graphics.getWidth()/2,
            50,
            "warg",
            world,
            3,
            1
        );
        //setAccFactor(0.02f);
    }
    
    @Override
    public void fight(AbstractCharacter enemy, float delta) {
        //playSpacial(true);
        enemy.drainLife(delta/4);
    }
}
