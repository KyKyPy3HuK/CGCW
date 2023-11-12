package com.mygdx.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.GameObj.Player;
import com.mygdx.game.GameParams;
import com.mygdx.game.producers.BulletProducer;

public class MainScreen implements Screen {
    //Screen
    private Camera camera;
    private Viewport viewport;
    //Graphics
    private SpriteBatch spriteBatch;
    private Texture bgTexture;

    //Timing
    private Texture[] backgrounds;
    private float[] bgOffsets = {0,0,0};
    private float bgMaxSpeed;

    //World
    private final int WORLD_HEIGHT = GameParams.WORLD_HEIGHT;
    private final int WORLD_WIDTH = GameParams.WORLD_WIDTH;

    //Game objects & prodicers
    private Player player;
    public static BulletProducer bulletProducer = new BulletProducer();
    public MainScreen(){
        camera = new OrthographicCamera();
        viewport = new StretchViewport(WORLD_WIDTH,WORLD_HEIGHT,camera);
        backgrounds = new Texture[3];
        backgrounds[0] = new Texture("bgBase.png");
        backgrounds[1] = new Texture("bgLayerFar.png");
        backgrounds[2] = new Texture("bgLayerNear.png");
        bgMaxSpeed = (float)(WORLD_HEIGHT) / 4;
        spriteBatch = new SpriteBatch();
        player = new Player(20, 20, new Vector2(2.0F,2.0f));
    }

    @Override
    public void show() {

    }

    public void  update(float deltaTime){
        player.update(deltaTime);
        bulletProducer.update(deltaTime);
    }

    @Override
    public void render(float delta) {
        this.update(delta);

        spriteBatch.begin();

        // прокрутка фона
        renderBackground(delta);
        bulletProducer.render(spriteBatch);
        player.render(spriteBatch);
        spriteBatch.end();
    }
    private void renderBackground(float delta){
        bgOffsets[0] += delta * bgMaxSpeed / 4;
        bgOffsets[1] += delta * bgMaxSpeed / 2;
        bgOffsets[2] += delta * bgMaxSpeed / 1;

        for (int layer = 0; layer < bgOffsets.length; layer++){
            if (bgOffsets[layer] > WORLD_HEIGHT){
                bgOffsets[layer] = 0;
            }

            spriteBatch.draw(backgrounds[layer],0,-bgOffsets[layer], WORLD_WIDTH,WORLD_HEIGHT);
            spriteBatch.draw(backgrounds[layer],0,-bgOffsets[layer] + WORLD_HEIGHT, WORLD_WIDTH,WORLD_HEIGHT);
        }

    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height,true);
        spriteBatch.setProjectionMatrix(camera.combined);
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
