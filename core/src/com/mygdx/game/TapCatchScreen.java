package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

public class TapCatchScreen extends ScreenAdapter {
    private static final float PLAYER_WIDTH = 160f;
    private static final float PLAYER_HEIGHT = 40f;
    private static final float COIN_SIZE = 48f;
    private static final float BASE_SPEED = 260f;

    private final MyGdxGame mGG;
    private final MovingFonView backroundView;
    private final Rectangle player;
    private final Rectangle coin;
    private final Vector3 touch;
    private Texture playerTexture;
    private Texture coinTexture;
    private float coinSpeed;
    private int score;
    private int lives;
    private boolean gameOver;

    public TapCatchScreen(MyGdxGame mGG) {
        this.mGG = mGG;
        backroundView = new MovingFonView(GameResources.BACK_FON, 0, 0);
        player = new Rectangle((GameSettings.SCR_WIDTH - PLAYER_WIDTH) / 2f, 40f, PLAYER_WIDTH, PLAYER_HEIGHT);
        coin = new Rectangle(0, GameSettings.SCR_HEIGHT - COIN_SIZE, COIN_SIZE, COIN_SIZE);
        touch = new Vector3();
        coinSpeed = BASE_SPEED;
        score = 0;
        lives = 3;
        gameOver = false;
        createTextures();
        resetCoin();
    }

    private void createTextures() {
        Pixmap playerPixmap = new Pixmap((int) PLAYER_WIDTH, (int) PLAYER_HEIGHT, Pixmap.Format.RGBA8888);
        playerPixmap.setColor(new Color(0.15f, 0.55f, 0.95f, 1f));
        playerPixmap.fill();
        playerTexture = new Texture(playerPixmap);
        playerPixmap.dispose();

        Pixmap coinPixmap = new Pixmap((int) COIN_SIZE, (int) COIN_SIZE, Pixmap.Format.RGBA8888);
        coinPixmap.setColor(new Color(1f, 0.8f, 0.2f, 1f));
        coinPixmap.fillCircle((int) COIN_SIZE / 2, (int) COIN_SIZE / 2, (int) COIN_SIZE / 2);
        coinTexture = new Texture(coinPixmap);
        coinPixmap.dispose();
    }

    private void resetCoin() {
        coin.x = MathUtils.random(0f, GameSettings.SCR_WIDTH - coin.width);
        coin.y = GameSettings.SCR_HEIGHT + MathUtils.random(40f, 200f);
    }

    private void handleInput() {
        if (Gdx.input.isTouched()) {
            touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            mGG.camera.unproject(touch);
            player.x = MathUtils.clamp(touch.x - player.width / 2f, 0f, GameSettings.SCR_WIDTH - player.width);
        }

        if (gameOver && Gdx.input.justTouched()) {
            mGG.setScreen(mGG.menuScreen);
        }
    }

    private void update(float delta) {
        if (gameOver) {
            return;
        }

        coin.y -= coinSpeed * delta;
        if (coin.overlaps(player)) {
            score++;
            coinSpeed += 20f;
            resetCoin();
        } else if (coin.y + coin.height < 0f) {
            lives--;
            resetCoin();
            if (lives <= 0) {
                gameOver = true;
            }
        }
    }

    @Override
    public void render(float delta) {
        handleInput();
        update(delta);

        mGG.camera.update();
        mGG.batch.setProjectionMatrix(mGG.camera.combined);
        ScreenUtils.clear(Color.WHITE);

        mGG.batch.begin();
        backroundView.draw(mGG.batch);
        mGG.batch.draw(playerTexture, player.x, player.y, player.width, player.height);
        if (!gameOver) {
            mGG.batch.draw(coinTexture, coin.x, coin.y, coin.width, coin.height);
        }
        mGG.commonBlackFont.draw(mGG.batch, "Счет: " + score, 40, GameSettings.SCR_HEIGHT - 40);
        mGG.commonBlackFont.draw(mGG.batch, "Жизни: " + lives, 40, GameSettings.SCR_HEIGHT - 80);
        if (gameOver) {
            mGG.largeBlackFont.draw(mGG.batch, "Игра окончена", 360, GameSettings.SCR_HEIGHT / 2f + 40);
            mGG.commonBlackFont.draw(mGG.batch, "Коснитесь, чтобы вернуться в меню", 320, GameSettings.SCR_HEIGHT / 2f - 20);
        } else {
            mGG.commonBlackFont.draw(mGG.batch, "Коснитесь, чтобы двигать платформу", 300, 120);
        }
        mGG.batch.end();
    }

    @Override
    public void dispose() {
        if (playerTexture != null) {
            playerTexture.dispose();
        }
        if (coinTexture != null) {
            coinTexture.dispose();
        }
    }
}
