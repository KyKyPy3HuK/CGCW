package com.mygdx.game;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.screens.GameScreen;

public class TestGame extends Game {
	SpriteBatch batch;
	GameScreen gameScreen;

	@Override
	public void create () {
		batch = new SpriteBatch();
		gameScreen = new GameScreen();
		setScreen(gameScreen);
	}

	@Override
	public void resize(int width, int height) {
		gameScreen.resize(width,height);
	}

	public void  update(){
	}

	@Override
	public void render () {
		this.update();
		super.render();

		Gdx.gl.glClearColor(0,0,0,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		gameScreen.render(0);

		batch.begin();
		batch.end();
	}

	@Override
	public void dispose () {
		gameScreen.dispose();
		super.dispose();
	}
}