package com.mygdx.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.GameParams;
import com.mygdx.game.TestGame;

public class SettingsScreen implements Screen {
    Texture bgTexture;
    TestGame testGame;
    SpriteBatch batch;
    Stage stage;
    Viewport viewport;
    OrthographicCamera camera;
    private final int WORLD_HEIGHT = 1000;
    private final int WORLD_WIDTH = 700;
    private final int CHECKBOX_OFFSET_Y = 50;
    
    Skin skin;

    CheckBox musicCheckbox;
    CheckBox soundCheckBox;

    ButtonGroup<CheckBox> gameModeGrp;
    CheckBox survivalCheckBox;
    CheckBox waveCheckBox;
    
    ButtonGroup<CheckBox> difficultyGrp;
    CheckBox easyCheckBox;
    CheckBox mediumCheckBox;
    CheckBox hardCheckBox;

    public SettingsScreen(TestGame game){
        this.testGame = game;
        camera = new OrthographicCamera();
        viewport = new StretchViewport(WORLD_WIDTH,WORLD_HEIGHT,camera);
        batch = testGame.batch;
        stage = new Stage(viewport,batch);
        Gdx.input.setInputProcessor(stage);
        bgTexture = new Texture("menuBg.png");
        
        skin  = new Skin(Gdx.files.internal(GameParams.SKIN_STR));

        musicCheckbox = new CheckBox(" music", skin);
        musicCheckbox.setPosition(0,CHECKBOX_OFFSET_Y * 10);
        stage.addActor(musicCheckbox);
        if (TestGame.musicVolume < 1){
            musicCheckbox.setChecked(false);
        }
        else {
            musicCheckbox.setChecked(true);
        }

        soundCheckBox = new CheckBox(" sounds", skin);
        soundCheckBox.setPosition(0,CHECKBOX_OFFSET_Y * 9);
        stage.addActor(soundCheckBox);
        if (TestGame.soundVolume < 1){
            soundCheckBox.setChecked(false);
        }
        else {
            soundCheckBox.setChecked(true);
        }

        gameModeGrp = new ButtonGroup<>();
        survivalCheckBox = new CheckBox(" survival",skin);
        survivalCheckBox.setPosition(0,CHECKBOX_OFFSET_Y * 7);
        waveCheckBox = new CheckBox(" waves",skin);
        waveCheckBox.setPosition(0,CHECKBOX_OFFSET_Y * 6);
        gameModeGrp.add(survivalCheckBox);
        gameModeGrp.add(waveCheckBox);
        stage.addActor(survivalCheckBox);
        stage.addActor(waveCheckBox);
        switch (TestGame.currentGameMode){
            case(GameParams.GAME_MODE_SURVIVAL):{
                gameModeGrp.setChecked(" survival");
                break;
            }
            case (GameParams.GAME_MODE_WAVES):{
                gameModeGrp.setChecked(" waves");
                break;
            }
            default:{
                gameModeGrp.setChecked(" survival");
                break;
            }
        }

        difficultyGrp = new ButtonGroup<>();
        easyCheckBox = new CheckBox(" easy",skin);
        easyCheckBox.setPosition(0,CHECKBOX_OFFSET_Y * 3);
        mediumCheckBox = new CheckBox(" medium", skin);
        mediumCheckBox.setPosition(0,CHECKBOX_OFFSET_Y * 2);
        hardCheckBox = new CheckBox(" hard", skin);
        hardCheckBox.setPosition(0,CHECKBOX_OFFSET_Y * 1);
        difficultyGrp.add(easyCheckBox);
        difficultyGrp.add(mediumCheckBox);
        difficultyGrp.add(hardCheckBox);
        stage.addActor(easyCheckBox);
        stage.addActor(mediumCheckBox);
        stage.addActor(hardCheckBox);

        switch (TestGame.currentDifficulty){
            case(GameParams.DIFFICULTY_EASY):{
                difficultyGrp.setChecked(" easy");
                break;
            }
            case (GameParams.DIFFICULTY_MEDIUM):{
                difficultyGrp.setChecked(" medium");
                break;
            }
            case (GameParams.DIFFICULTY_HARD):{
                difficultyGrp.setChecked(" hard");
                break;
            }
            default:{
                difficultyGrp.setChecked(" medium");
                break;
            }
        }
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        batch.begin();
        batch.draw(bgTexture,0,0, WORLD_WIDTH,WORLD_HEIGHT);
        batch.end();
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
            testGame.setScreen(new MenuScreen(testGame));
        }
        stage.act();
        stage.draw();
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
        int gameMode = gameModeGrp.getCheckedIndex();
        switch (gameMode){
            case (GameParams.GAME_MODE_SURVIVAL):{
                TestGame.currentGameMode = GameParams.GAME_MODE_SURVIVAL;
                break;
            }
            case (GameParams.GAME_MODE_WAVES):{
                TestGame.currentGameMode = GameParams.GAME_MODE_WAVES;
                break;
            }
            default:{
                TestGame.currentGameMode = GameParams.GAME_MODE_SURVIVAL;
                break;
            }
        }

        int difficulty = difficultyGrp.getCheckedIndex();
        switch (difficulty){
            case (GameParams.DIFFICULTY_EASY):{
                TestGame.currentDifficulty = GameParams.DIFFICULTY_EASY;
                break;
            }
            case (GameParams.DIFFICULTY_MEDIUM):{
                TestGame.currentDifficulty = GameParams.DIFFICULTY_MEDIUM;
                break;
            }
            case (GameParams.DIFFICULTY_HARD):{
                TestGame.currentDifficulty = GameParams.DIFFICULTY_HARD;
                break;
            }
            default:{
                TestGame.currentDifficulty = GameParams.DIFFICULTY_MEDIUM;
                break;
            }
        }

        if (musicCheckbox.isChecked()){
            TestGame.musicVolume = 1f;
        }
        else {
            TestGame.musicVolume = 0f;
            testGame.music.pause();
        }

        if (soundCheckBox.isChecked()){
            TestGame.soundVolume = 1f;
        }
        else {
            TestGame.soundVolume = 0f;
        }
    }

    @Override
    public void dispose() {

    }
}
