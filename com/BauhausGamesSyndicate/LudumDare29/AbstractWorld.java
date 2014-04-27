package com.BauhausGamesSyndicate.LudumDare29;

import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Matrix4;

/**
 *
 * @author Benedikt Vogler
 * @author Paul Flechsig
 * @author Jacob Bauer
 */
public abstract class AbstractWorld {
    public ShaderProgram shader;
    public Matrix4 matrix;

    public AbstractWorld(ShaderProgram _shader, Matrix4 _matrix) {
        shader = _shader;
        matrix = _matrix;
    }

    public void render(GameScreen gs){
        
    }
    
    public void update(float delta){
        
    }
}

