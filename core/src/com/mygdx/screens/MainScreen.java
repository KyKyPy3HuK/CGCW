package com.mygdx.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.GameObj.Bullet;
import com.mygdx.game.GameObj.Enemy;
import com.mygdx.game.GameObj.EnemyRifleman;
import com.mygdx.game.GameObj.Player;
import com.mygdx.game.GameParams;
import com.mygdx.game.producers.BulletProducer;
import com.mygdx.game.producers.EnemyProducer;

import java.awt.*;

public class MainScreen implements Screen {
    //Screen
    private Camera camera;
    private Viewport viewport;
    Stage stage;
    //Graphics
    private SpriteBatch spriteBatch;
    private Texture bgTexture;
    private com.badlogic.gdx.scenes.scene2d.ui.Label score;
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
    public static EnemyProducer enemyProducer = new EnemyProducer();


    // constructors
    public MainScreen(){

        camera = new OrthographicCamera();
        viewport = new StretchViewport(WORLD_WIDTH,WORLD_HEIGHT,camera);
        spriteBatch = new SpriteBatch();
        stage = new Stage(viewport,spriteBatch);

        com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle labelStyle = new com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle();
        BitmapFont font = new BitmapFont();
        labelStyle.font = font;
        labelStyle.fontColor = Color.SKY;

        score = new com.badlogic.gdx.scenes.scene2d.ui.Label ("text", labelStyle);
        stage.addActor(score);

        backgrounds = new Texture[3];
        backgrounds[0] = new Texture("bgBase.png");
        backgrounds[1] = new Texture("bgLayerFar.png");
        backgrounds[2] = new Texture("bgLayerNear.png");
        bgMaxSpeed = (float)(WORLD_HEIGHT) / 4;

        player = new Player(20, 20, new Vector2(2.0F,2.0f));
        enemyProducer.addEnemy(new EnemyRifleman(35,50, new Vector2(0,-10)));
    }


    // methods
    @Override
    public void show() {

    }

    public void  update(float deltaTime){
        stage.act();
        player.update(deltaTime);
        enemyProducer.update(deltaTime);
        bulletProducer.update(deltaTime);
        detectCollisions();
        if (enemyProducer.getEnemyList().isEmpty()){
            enemyProducer.addEnemy(new EnemyRifleman(35,50, new Vector2(0,-10)));
        }
    }
    public void detectCollisions(){
        Bullet currentBullet;
        Enemy currentEnemy;

        int bulletsCount = bulletProducer.getBulletList().size();
        int enemyCnt = enemyProducer.getEnemyList().size();

       for (int i = 0; i < bulletsCount; i++){
           currentBullet = bulletProducer.getBulletList().get(i);


           switch (currentBullet.playerTeam){
               case(GameParams.PLAYER_TEAM):{
                   for (int j = 0; j < enemyCnt; j++){
                       currentEnemy = enemyProducer.getEnemyList().get(j);
                       if (currentBullet.isIntersects(currentEnemy.getCollisionRect())){
                           bulletProducer.getBulletList().remove(i);
                           player.addScore(currentEnemy.takeBulletDamage(currentBullet));
                           bulletsCount--;
                           i--;
                       }
                   }
                   break;
               }
               case (GameParams.ENEMY_TEAM):{
                   if (currentBullet.isIntersects(player.getCollisionRect())){
                       //player.takeBulletDamage(currentBullet);
                   }
                   break;
               }
           }



       }

    }
    @Override
    public void render(float delta) {
        this.update(delta);

        spriteBatch.begin();

        // прокрутка фона
        renderBackground(delta);
        bulletProducer.render(spriteBatch);
        enemyProducer.render(spriteBatch);
        player.render(spriteBatch);

        renderGUI(spriteBatch);

        spriteBatch.end();
        stage.draw();
    }

    private void renderGUI(SpriteBatch batch){
        score.setText(String.valueOf(player.getScore()));
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
