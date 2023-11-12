package com.mygdx.game;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.GameObj.Asteroid;
import com.mygdx.game.GameObj.AsteroidProducer;
import com.mygdx.game.GameObj.Player;
import com.mygdx.screens.MainScreen;

public class TestGame extends Game {
	SpriteBatch batch;
	MainScreen gameScreen;

	@Override
	public void create () {
		batch = new SpriteBatch();
		gameScreen = new MainScreen();
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