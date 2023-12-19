package com.mygdx.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.GameObj.*;
import com.mygdx.game.GameParams;
import com.mygdx.game.TestGame;
import com.mygdx.game.producers.BulletProducer;
import com.mygdx.game.producers.EnemyProducer;
import com.mygdx.game.producers.FXProducer;
import com.mygdx.game.producers.ItemProducer;

public class GameScreen implements Screen {
    TestGame game;
    Music music;
    float stateTime;
    //Screen
    private Camera camera;
    private Viewport viewport;
    Stage stage;
    //Graphics
    private SpriteBatch spriteBatch;
    private Texture bgTexture;
    private com.badlogic.gdx.scenes.scene2d.ui.Label score,
                                                    health,
                                                    ammo;
    //Timing
    private Texture[] backgrounds;
    private float[] bgOffsets = {0,0,0};
    private float bgMaxSpeed;

    //World
    private final int WORLD_HEIGHT = GameParams.WORLD_HEIGHT;
    private final int WORLD_WIDTH = GameParams.WORLD_WIDTH;

    //Game objects & producers
    private Player player;
    public static BulletProducer bulletProducer = new BulletProducer();
    public static EnemyProducer enemyProducer = new EnemyProducer();
    public static ItemProducer itemProducer = new ItemProducer();
    public static FXProducer fxProducer = new FXProducer();

    // constructors
    public GameScreen(TestGame game){

        this.game = game;
        music = Gdx.audio.newMusic(Gdx.files.internal("music/game.mp3"));
        music.setLooping(true);
        stateTime = 0;

        camera = new OrthographicCamera();
        viewport = new StretchViewport(WORLD_WIDTH,WORLD_HEIGHT,camera);
        spriteBatch = game.batch;
        stage = new Stage(viewport,spriteBatch);

        com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle labelStyle = new com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle();
        BitmapFont font = new BitmapFont();
        labelStyle.font = font;
        labelStyle.fontColor = Color.SKY;


        score = new com.badlogic.gdx.scenes.scene2d.ui.Label ("text", labelStyle);
        score.setFontScale(0.2f);
        score.setPosition(2,5);
        stage.addActor(score);

        ammo = new com.badlogic.gdx.scenes.scene2d.ui.Label ("text", labelStyle);
        ammo.setFontScale(0.2f);
        ammo.setPosition(2,10);
        stage.addActor(ammo);

        health = new com.badlogic.gdx.scenes.scene2d.ui.Label ("text", labelStyle);
        health.setFontScale(0.2f);
        health.setPosition(2,0);
        stage.addActor(health);

        backgrounds = new Texture[3];
        backgrounds[0] = new Texture("bgBase.png");
        backgrounds[1] = new Texture("bgLayerFar.png");
        backgrounds[2] = new Texture("bgLayerNear.png");
        bgMaxSpeed = (float)(WORLD_HEIGHT) / 4;

        player = new Player(20, 20, new Vector2(2.0F,2.0f));
        enemyProducer.addEnemy(new EnemyRifleman(35,50,10, new Vector2(0,-10)));
        itemProducer.addItem(new MedKit(10,100,new Vector2(0,-10)));
        itemProducer.addItem(new AmmoBonus(10,100,new Vector2(0,-20)));
        itemProducer.addItem(new SpeedBonus(30,100,new Vector2(0,-10)));

    }


    // methods
    @Override
    public void show() {
        music.play();
        music.setVolume(0.4f);
    }

    public void  update(float deltaTime){
        stage.act();
        detectCollisions();
        player.update(deltaTime);
        enemyProducer.update(deltaTime);
        bulletProducer.update(deltaTime);
        itemProducer.update(deltaTime);
        fxProducer.update(deltaTime);
        if (player.getHp() <= 0 || Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
            game.setScreen(new EndGameScreen(game,player.getScore()));
            this.dispose();
        }
        if (enemyProducer.getEnemyList().isEmpty()){
            enemyProducer.addEnemyRandom(GameParams.RIFLEMAN);
            enemyProducer.addEnemyRandom(GameParams.RIFLEMAN);
            enemyProducer.addEnemyRandom(GameParams.RIFLEMAN);
            enemyProducer.addEnemyRandom(GameParams.RIFLEMAN);
            enemyProducer.addEnemyRandom(GameParams.RIFLEMAN);
            enemyProducer.addEnemyRandom(GameParams.RIFLEMAN);
            enemyProducer.addEnemyRandom(GameParams.RIFLEMAN);
            enemyProducer.addEnemyRandom(GameParams.RIFLEMAN);
            enemyProducer.addEnemyRandom(GameParams.RIFLEMAN);
        }
        if (itemProducer.getItemsCount() == 0){
            itemProducer.addItem(new MedKit(10,100,new Vector2(0,-10)));
            itemProducer.addItem(new AmmoBonus(15,100,new Vector2(0,-15)));
            itemProducer.addItem(new ReloadBonus(15,100,new Vector2(0,-20)));
            itemProducer.addItem(new SpeedBonus(30,100,new Vector2(0,-10)));
        }
    }
    public void detectCollisions(){
        Item currentItem;
        Bullet currentBullet;
        Enemy currentEnemy;

        int bulletsCount = bulletProducer.getBulletList().size();
        int enemyCnt = enemyProducer.getEnemyList().size();

        // бонусы
        for (int i = 0; i < itemProducer.getItemsCount(); i++){
            currentItem = itemProducer.getItem(i);
            if (player.isIntersects(currentItem.getCollisionRect())){
                player.takeBonus(currentItem);
                itemProducer.removeItem(i);
                i--;
            }
        }

        // столкновения с врагами
        for (int i = 0; i < enemyCnt; i++){
            currentEnemy = enemyProducer.getEnemyList().get(i);
            if(player.isIntersects(currentEnemy.getCollisionRect())){
                player.takeMeleeDamage(currentEnemy);
                player.addScore(currentEnemy.takeMeleeDamage(player));
                player.reverseSpeed(currentEnemy.getX(),currentEnemy.getY());
            }
        }

        // обработка столкновения пуль
       for (int i = 0; i < bulletsCount; i++){
           currentBullet = bulletProducer.getBulletList().get(i);

           switch (currentBullet.playerTeam){
               case(GameParams.PLAYER_TEAM):{
                   for (int j = 0; j < enemyCnt; j++){
                       currentEnemy = enemyProducer.getEnemyList().get(j);
                       if (currentBullet.isIntersects(currentEnemy.getCollisionRect())){
                           if(i >= 0)  {
                               bulletProducer.getBulletList().remove(i);
                               player.addScore(currentEnemy.takeBulletDamage(currentBullet));
                               fxProducer.addFX(new FXAnimation(currentBullet.getHitAnimation(), currentBullet.getX(),currentBullet.getY(), currentBullet.bulletHitAnimationSize));
                               bulletsCount--;
                               i--;
                           }

                       }
                   }
                   break;
               }
               case (GameParams.ENEMY_TEAM):{
                   if (currentBullet.isIntersects(player.getCollisionRect())){
                       player.takeBulletDamage(currentBullet);
                       bulletProducer.getBulletList().remove(i);
                       fxProducer.addFX(new FXAnimation(currentBullet.getHitAnimation(), currentBullet.getX() - 2,currentBullet.getY() - 2, 4));

                       bulletsCount--;
                       i--;
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
        stateTime += delta;
        // прокрутка фона
        renderBackground(delta);
        itemProducer.render(spriteBatch);
        bulletProducer.render(spriteBatch);
        enemyProducer.render(spriteBatch);
        player.render(spriteBatch);
        fxProducer.render(spriteBatch);

        renderGUI(spriteBatch);

        spriteBatch.end();
        stage.draw();
    }

    private void renderGUI(SpriteBatch batch){
        ammo.setText(String.valueOf(player.getAmmo()));
        health.setText(String.valueOf(player.getHp()));
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
        music.stop();
        music.dispose();
        bulletProducer.dispose();
        enemyProducer.dispose();
        itemProducer.dispose();
        fxProducer.dispose();
    }
}
