package com.BauhausGamesSyndicate.LudumDare29;

/*
 * This is free and unencumbered software released into the public domain.
*
*Anyone is free to copy, modify, publish, use, compile, sell, or
*distribute this software, either in source code form or as a compiled
*binary, for any purpose, commercial or non-commercial, and by any
*means.
*
*In jurisdictions that recognize copyright laws, the author or authors
*of this software dedicate any and all copyright interest in the
*software to the public domain. We make this dedication for the benefit
*of the public at large and to the detriment of our heirs and
*successors. We intend this dedication to be an overt act of
*relinquishment in perpetuity of all present and future rights to this
*software under copyright law.
*
*THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
*EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
*MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
*IN NO EVENT SHALL THE AUTHORS BE LIABLE FOR ANY CLAIM, DAMAGES OR
*OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
*ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
*OTHER DEALINGS IN THE SOFTWARE.
*
*For more information, please refer to <http://unlicense.org>
 */



import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import java.util.ArrayList;

/**
 *
 * @author Benedikt Vogler
 */
public class Map {
    private Tile[][] data;
    private final ArrayList<AbstractEntity> entityList = new ArrayList<>();


    public Map() {
        this.data = new Tile[40][32];
        for (Tile[] x : data) {
            for (int y = 0; y < x.length; y++) {
                x[y] = new Tile(
                    new Color((float) Math.random()/2, (float) Math.random()/2, (float) Math.random()/2, 1)
                    );
            }
        }
    }
    
    public void render(ShapeRenderer sh){
        sh.begin(ShapeRenderer.ShapeType.Filled);
        for (int x = 0; x < data.length; x++) {
            for (int y = 0; y < data[x].length; y++) {
                Color color = data[x][y].getColor();
                sh.setColor(color);

                sh.rect(x*20, y*20, 20, 20);
    }
        }
        sh.end();
    }
    
}
