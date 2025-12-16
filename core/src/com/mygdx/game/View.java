package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Disposable;

public class View implements Disposable {
    float x, y;
    public float weight, height;
    Texture texture;

    public View(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public View(float x, float y, float height, float weight) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.weight = weight;
    }


    public boolean isHit(float tx, float ty) {
        return tx >= x && tx <= x + weight & ty <= y + height && ty >= y;
    }




    @Override
    public void dispose() {

    }





}
