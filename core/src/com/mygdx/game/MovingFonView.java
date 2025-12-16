package com.mygdx.game;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MovingFonView extends View {
    Texture texture;
    int texture1;

    public MovingFonView(String pathToTexture, float height, float weight) {
        super(0, 0, height, weight);
        texture1 = 0;
        texture = new Texture(pathToTexture);

    }


    public void draw(SpriteBatch batch) {
        batch.draw(texture, 0, texture1, GameSettings.SCR_WIDTH, GameSettings.SCR_HEIGHT);
    }

}
