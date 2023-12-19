package com.mygdx.screens;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.TestGame;

import java.awt.*;

public class EndGameScreen implements Screen {
    int row_height = Gdx.graphics.getWidth() / 12;
    int col_width = Gdx.graphics.getWidth() / 12;
    private final int WORLD_HEIGHT = 1000;
    private final int WORLD_WIDTH = 700;
    private final float STAGE_SCALE = 1.4f;
    private final float BTNS_OFFSET_X = 80;
    private final float BTNS_OFFSET_Y = 55;
    int score;
    Camera camera;
    Viewport viewport;
    SpriteBatch batch;
    Stage stage;
    TestGame testGame;
    com.badlogic.gdx.scenes.scene2d.ui.Label scoreLabel;
    TextButton continueButton;



    public EndGameScreen(TestGame game, int score){
        Skin skin = new Skin(Gdx.files.internal("Skins/btn.json"));

        this.testGame = game;
        camera = new OrthographicCamera();
        viewport = new StretchViewport(WORLD_WIDTH,WORLD_HEIGHT,camera);
        batch = testGame.batch;
        stage = new Stage(viewport,batch);
        Gdx.input.setInputProcessor(stage);
        scoreLabel = new Label("You are dead :( Your score: " + score , skin);
        continueButton = new TextButton("Continue",skin);

    }

    @Override
    public void show() {
        stage.addActor(scoreLabel);
        continueButton.setTransform(true);
        continueButton.setTransform(true);
        continueButton.setSize(col_width*4,row_height);
        continueButton.scaleBy(STAGE_SCALE);
        continueButton.setPosition(BTNS_OFFSET_X,BTNS_OFFSET_Y * 4);
        stage.addActor(continueButton);
        scoreLabel.setFontScale(STAGE_SCALE * STAGE_SCALE);
        scoreLabel.setPosition(BTNS_OFFSET_X + 50,BTNS_OFFSET_Y * 8);

        Sound loseSound = Gdx.audio.newSound(Gdx.files.internal("sounds/lose.wav"));
        loseSound.play();
    }

    @Override
    public void render(float v) {
        if (continueButton.isPressed() || Gdx.input.isKeyJustPressed(Input.Keys.ENTER) || Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
            testGame.setScreen(new MenuScreen(testGame));
        }
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int i, int i1) {

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
