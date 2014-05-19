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
    private ShaderProgram shader;
    private Matrix4 matrix;

    public AbstractWorld(ShaderProgram _shader, Matrix4 _matrix) {
        shader = _shader;
        matrix = _matrix;
    }

    public abstract void render(GameScreen gs);
    
    public abstract void update(final float delta);

    /**
     * @return the shader
     */
    public ShaderProgram getShader() {
        return shader;
    }

    /**
     * @param shader the shader to set
     */
    public void setShader(ShaderProgram shader) {
        this.shader = shader;
    }

    public Matrix4 getMatrix() {
        return matrix;
    }

    public void setMatrix(Matrix4 matrix) {
        this.matrix = matrix;
    }
    
    
}

