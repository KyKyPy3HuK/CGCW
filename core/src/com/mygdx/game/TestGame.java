package com.mygdx.game;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.screens.GameScreen;
import com.mygdx.screens.MenuScreen;

public class TestGame extends Game {
	public SpriteBatch batch;

	@Override
	public void create () {
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