package com.mygdx.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.GameParams;
import com.mygdx.game.TestGame;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

public class MenuScreen implements Screen {
    //public Music music;
    Texture bgTexture;
    TestGame testGame;
    SpriteBatch batch;
    Stage stage;
    Viewport viewport;
    OrthographicCamera camera;
    int row_height = Gdx.graphics.getWidth() / 12;
    int col_width = Gdx.graphics.getWidth() / 12;
    private final int WORLD_HEIGHT = 1000;
    private final int WORLD_WIDTH = 700;
    private final float STAGE_SCALE = 0.7f;
    private final float BTNS_OFFSET_X = 230;
    private final float BTNS_OFFSET_Y = 50;

    TextButton playBtn;
    TextButton optionsBtn;
    TextButton exitBtn;

    public MenuScreen(TestGame game){

        this.testGame = game;
        camera = new OrthographicCamera();
        viewport = new StretchViewport(WORLD_WIDTH,WORLD_HEIGHT,camera);
        batch = testGame.batch;
        stage = new Stage(viewport,batch);
        Gdx.input.setInputProcessor(stage);
        bgTexture = new Texture("menuBg.png");
    }

    @Override
    public void show() {
        if (!testGame.music.isPlaying()){
            testGame.music.play();
            testGame.music.setVolume(TestGame.musicVolume);
            testGame.music.setPosition(2.5f);
        }
        if (TestGame.musicVolume < 1f){
            testGame.music.pause();
        }


        Skin skin = new Skin(Gdx.files.internal(GameParams.SKIN_STR));

        optionsBtn = new TextButton("Options", skin);
        exitBtn = new TextButton("Exit",skin);

        playBtn = new TextButton("Play",skin);
        playBtn.setTransform(true);
        //playBtn.setSize(col_width*4,row_height);
        //playBtn.scaleBy(STAGE_SCALE);
        playBtn.setPosition(BTNS_OFFSET_X,BTNS_OFFSET_Y * 6);
        stage.addActor(playBtn);

        optionsBtn.setTransform(true);
        //optionsBtn.setSize(col_width*4,row_height);
        //optionsBtn.scaleBy(STAGE_SCALE);
        optionsBtn.setPosition(BTNS_OFFSET_X,BTNS_OFFSET_Y * 4);
        stage.addActor(optionsBtn);

        exitBtn.setTransform(true);
        //exitBtn.setSize(col_width*4,row_height);
       // exitBtn.scaleBy(STAGE_SCALE);
        exitBtn.setPosition(BTNS_OFFSET_X,BTNS_OFFSET_Y * 2);
        stage.addActor(exitBtn);

    }

    @Override
    public void render(float v) {
        batch.begin();
        batch.draw(bgTexture,0,0, WORLD_WIDTH,WORLD_HEIGHT);
        batch.end();
        if (playBtn.isPressed() || Gdx.input.isKeyJustPressed(Input.Keys.ENTER)){
            testGame.music.stop();
            testGame.setScreen(new GameScreen(testGame, TestGame.currentDifficulty, TestGame.currentGameMode));

        } else if (exitBtn.isPressed() || Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
            System.exit(-1);
        }
        else if(optionsBtn.isPressed() || Gdx.input.isKeyJustPressed(Input.Keys.O)){
            testGame.setScreen(new SettingsScreen(testGame));
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
