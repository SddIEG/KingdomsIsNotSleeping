package com.mygdx.game;

import static com.mygdx.game.GameSettings.SCR_HEIGHT;
import static com.mygdx.game.GameSettings.SCR_WIDTH;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.ScreenUtils;

public class MyGdxGame extends Game {
    public SpriteBatch batch;
    public BitmapFont commonBlackFont, largeBlackFont;
    public OrthographicCamera camera;
    public Vector3 touch;
   public MenuScreen menuScreen;

    @Override
    public void create() {
        Box2D.init();
        commonBlackFont = FontBuilder. generate (24, Color.BLACK, GameResources.FONTS_BATH);
        largeBlackFont = FontBuilder. generate (48, Color.WHITE, GameResources.FONTS_BATH);

        batch = new SpriteBatch();
        camera = new OrthographicCamera();

        menuScreen = new MenuScreen(this);
        camera.setToOrtho(false, SCR_WIDTH ,SCR_HEIGHT);
        setScreen(menuScreen);
    }


    @Override
    public void dispose() {
        batch.dispose();

    }
}
