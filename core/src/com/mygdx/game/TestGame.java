package com.mygdx.game;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.screens.GameScreen;
import com.mygdx.screens.MenuScreen;

public class TestGame extends Game {
	public Music music;

	// global variables
	public static float musicVolume = 0f;
	public static float soundVolume = 0f;
	public static int currentDifficulty = GameParams.DIFFICULTY_MEDIUM;
	public static int currentGameMode = GameParams.GAME_MODE_SURVIVAL;
	public SpriteBatch batch;
	public  int difficulty;
	@Override
	public void create () {
		music = Gdx.audio.newMusic(Gdx.files.internal("music/menu.mp3"));
		music.setLooping(true);
		difficulty = 0;
		batch = new SpriteBatch();
		//this.setScreen(new GameScreen(this));
		this.setScreen(new MenuScreen(this));
	}

	@Override
	public void resize(int width, int height) {
	}

	public void  update(){
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0,0,0,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		this.update();
		super.render();
	}

	@Override
	public void dispose () {
		batch.dispose();
		super.dispose();
	}
}