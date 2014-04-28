package com.BauhausGamesSyndicate.LudumDare29.GameObjects;

import com.BauhausGamesSyndicate.LudumDare29.overworld.Eingang;

/**
 *
 * @author Benedikt Vogler
 */
public class Bauer extends AbstractCharacter {

    public Bauer(float x, float y, boolean world) {
        super(x, y, "zivi", world, 1, 1);
    }

    @Override
    public boolean isEvil() {
        return false;
    }

    @Override
    public void fight(AbstractCharacter enemy, float delta) {
        //bauer macht nichts
        // von wegen der sorgt für das Essen in der Gesellschaft!
        
        //Bauer:
        //Ich mache was ich will!
        doAnything();
    }

    private void doAnything() {
        for(int i=0; i<23; i++) {
            /* SMOKE_WEED_EVERYDAY */
        }
    } 
}
