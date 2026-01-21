package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;


public class MenuScreen extends ScreenAdapter {
    MovingFonView backroundView;
    MyGdxGame mGG;
    ButtonView startView, settingView, exitView;
    TextView titleGame;

    public MenuScreen(MyGdxGame mGG) {
        this.mGG = mGG;

        backroundView = new MovingFonView(GameResources.BACK_FON, 0, 0);

        startView = new ButtonView(140, 646, 440, 70, mGG.commonBlackFont, GameResources.BUTTON_LONG_BG_IMG_PATH, "start");
        settingView = new ButtonView(140, 551, 440, 70, mGG.commonBlackFont, GameResources.BUTTON_LONG_BG_IMG_PATH, "settings");
        exitView = new ButtonView(140, 456, 440, 70, mGG.commonBlackFont, GameResources.BUTTON_LONG_BG_IMG_PATH, "exit");

        titleGame = new TextView(mGG.largeBlackFont, 180, 960, "Королевство не СПАТЬ");

    }

    public void handleInput() {
        if (Gdx.input.justTouched()) {
            mGG.touch = mGG.camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));


            if (startView.isHit(mGG.touch.x, mGG.touch.y)) {
                mGG.setScreen(new TapCatchScreen(mGG));
            }
            if (settingView.isHit(mGG.touch.x, mGG.touch.y)) {

            }
            if (exitView.isHit(mGG.touch.x, mGG.touch.y)) {
                Gdx.app.exit();
            }
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        handleInput();

        mGG.camera.update();
        mGG.batch.setProjectionMatrix(mGG.camera.combined);
        ScreenUtils.clear(Color.WHITE);

        mGG.batch.begin();

        backroundView.draw(mGG.batch);
        titleGame.draw(mGG.batch);
        startView.draw(mGG.batch);
        settingView.draw(mGG.batch);
        exitView.draw(mGG.batch);


        mGG.batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
