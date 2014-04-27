package com.BauhausGamesSyndicate.LudumDare29.GameObjects;

import com.BauhausGamesSyndicate.LudumDare29.overworld.Eingang;

/**
 *
 * @author Benedikt Vogler
 */
public class Bauer extends AbstractCharacter {

    public Bauer(float x, float y, boolean world, Eingang eingang) {
        super(x, y, "Bauer", world, 1, 1);
    }

    @Override
    public boolean isEvil() {
        return false;
    }

    @Override
    public void fight(AbstractCharacter enemy) {
        //bauer macht nichts
        // von wegen der sorgt für das Essen in der Gesellschaft!
    }
    
    
    
}
