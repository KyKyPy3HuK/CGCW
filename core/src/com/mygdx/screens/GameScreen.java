package com.mygdx.screens;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.GameObj.*;
import com.mygdx.game.GameParams;
import com.mygdx.game.TestGame;
import com.mygdx.game.producers.BulletProducer;
import com.mygdx.game.producers.EnemyProducer;
import com.mygdx.game.producers.FXProducer;
import com.mygdx.game.producers.ItemProducer;

import java.util.Random;

public class GameScreen implements Screen {
    TestGame game;
    static Random rnd;
    int gameMode;
    float difficultyTimeCounter;
    float difficultyTick;
    public static int difficultyCounter;
    float lastEnemySpawnTime;
    Music music;
    //Screen
    private Camera camera;
    private Viewport viewport;
    Stage stage;
    //Graphics
    private SpriteBatch spriteBatch;
    private com.badlogic.gdx.scenes.scene2d.ui.Label difficultyLabel,
                                                    score,
                                                    health,
                                                    ammo;
    //Timing
    private Texture[] backgrounds;
    private float[] bgOffsets = {0,0,0};
    private float bgMaxSpeed;

    //World
    private final int WORLD_HEIGHT = GameParams.WORLD_HEIGHT;
    private final int WORLD_WIDTH = GameParams.WORLD_WIDTH;
    final float FONT_SCALE = 0.15f;

    //Game objects & producers
    private Player player;
    public static BulletProducer bulletProducer = new BulletProducer();
    public static EnemyProducer enemyProducer = new EnemyProducer();
    public static ItemProducer itemProducer = new ItemProducer();
    public static FXProducer fxProducer = new FXProducer();


    // constructors
    public GameScreen(TestGame game, int difficulty, int gameMode){
        rnd = new Random(System.currentTimeMillis());

        switch (difficulty){
            case (GameParams.DIFFICULTY_EASY):{
                difficultyTick = GameParams.EASY_GAME_TICK;
                difficultyCounter = GameParams.DIFFICULTY_EASY_DEFAULT_COUNTER;

                break;
            }
            case (GameParams.DIFFICULTY_MEDIUM):{
                difficultyTick = GameParams.MEDIUM_GAME_TICK;
                difficultyCounter = GameParams.DIFFICULTY_MEDIUM_DEFAULT_COUNTER;

                break;
            }
            case (GameParams.DIFFICULTY_HARD):{
                difficultyTick = GameParams.HARD_GAME_TICK;
                difficultyCounter = GameParams.DIFFICULTY_HARD_DEFAULT_COUNTER;

                break;
            }
            default:{
                difficultyTick = GameParams.MEDIUM_GAME_TICK;
                difficultyCounter = GameParams.DIFFICULTY_EASY_DEFAULT_COUNTER;
                break;
            }
        }
        difficultyTimeCounter = 0f;
        this.gameMode = gameMode;
        this.lastEnemySpawnTime = 0f;

        this.game = game;
        music = Gdx.audio.newMusic(Gdx.files.internal("music/game.mp3"));
        music.setLooping(true);

        camera = new OrthographicCamera();
        viewport = new StretchViewport(WORLD_WIDTH,WORLD_HEIGHT,camera);
        spriteBatch = game.batch;
        stage = new Stage(viewport,spriteBatch);



        com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle labelStyle = new com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle();
        Skin skin = new Skin(Gdx.files.internal(GameParams.SKIN_STR));
        skin.setScale(0.5f);
        difficultyLabel = new com.badlogic.gdx.scenes.scene2d.ui.Label("text", skin);
        difficultyLabel.setFontScale(0.5f);
        difficultyLabel.setPosition(42f, -15f);
        stage.addActor(difficultyLabel);

        score = new com.badlogic.gdx.scenes.scene2d.ui.Label ("text", skin);

        score.setFontScale(0.5f);
        score.setPosition(0.5f,-7f);
        stage.addActor(score);

        ammo = new com.badlogic.gdx.scenes.scene2d.ui.Label ("text", skin);
        ammo.setFontScale(0.5f);
        ammo.setPosition(0.5f,-11f);
        stage.addActor(ammo);

        health = new com.badlogic.gdx.scenes.scene2d.ui.Label ("text", skin);
        health.setFontScale(0.5f);
        health.setPosition(0.5f,-15f);
        stage.addActor(health);

        backgrounds = new Texture[3];
        backgrounds[0] = new Texture("bgBase.png");
        backgrounds[1] = new Texture("bgLayerFar.png");
        backgrounds[2] = new Texture("bgLayerNear.png");
        bgMaxSpeed = (float)(WORLD_HEIGHT) / 4;

        player = new Player(20, 20, new Vector2(2.0F,2.0f));
    }

    public void difficultyUp(float deltaTime){
        difficultyTimeCounter += deltaTime;
        if (difficultyTimeCounter > difficultyTick){
            difficultyTimeCounter = 0;
            difficultyCounter++;
        }
    }
    public void  enemySpawn(float deltaTime){
        lastEnemySpawnTime +=deltaTime;
        switch (gameMode){
            case (GameParams.GAME_MODE_SURVIVAL):{
                    if(lastEnemySpawnTime > 1.5f * (Math.E - Math.log10(difficultyCounter * 2))){
                        player.addScore(difficultyCounter * 10 * (1 + TestGame.currentDifficulty));
                        enemyProducer.addEnemyRandom(difficultyCounter);
                        lastEnemySpawnTime = 0f;
                    }
                break;
            }
            case (GameParams.GAME_MODE_WAVES):{

            }
            default:{
                break;
            }
        }
    }
    public static void spawnBonus(float x, float y, float speedY){

            int arrSize = itemProducer.spawnArray.size();
            int spawnValue = rnd.nextInt(0,arrSize);
            int itemID = itemProducer.spawnArray.get(spawnValue);
            switch (itemID){
                case (GameParams.MED_BONUS):{
                    itemProducer.addItem(new MedKit(x,y,new Vector2(0f,speedY), difficultyCounter));
                    break;
                }
                case (GameParams.AMMO_BONUS):{
                    itemProducer.addItem(new AmmoBonus(x,y,new Vector2(0f,speedY)));
                    break;
                }
                case (GameParams.RELOAD_BONUS):{
                    itemProducer.addItem(new ReloadBonus(x,y,new Vector2(0f,speedY)));
                    break;
                }
                case (GameParams.SPEED_BONUS):{
                    itemProducer.addItem(new SpeedBonus(x,y,new Vector2(0f,speedY)));
                    break;
                }
                case (GameParams.BULLET_DAMAGE_BONUS):{
                    itemProducer.addItem(new BulletDamageBonus(x,y,new Vector2(0f,speedY)));
                    break;
                }
                case (GameParams.MELEE_DAMAGE_BONUS):{
                    itemProducer.addItem(new MeleeDamageBonus(x,y,new Vector2(0f,speedY)));
                    break;
                }
                case (GameParams.SCORE_BONUS):{
                    itemProducer.addItem(new ScoreBonus(x,y,new Vector2(0f,speedY)));
                    break;
                }

        }
    }
    // methods
    @Override
    public void show() {
        music.play();
        music.setVolume(TestGame.musicVolume * 0.4f);
    }

    public void  update(float deltaTime){
        difficultyUp(deltaTime);
        stage.act();
        detectCollisions();
        player.update(deltaTime);
        enemyProducer.update(deltaTime);
        bulletProducer.update(deltaTime);
        itemProducer.update(deltaTime);
        fxProducer.update(deltaTime);
        enemySpawn(deltaTime);
        if (player.getHp() <= 0 || Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
            game.setScreen(new EndGameScreen(game,player.getScore()));
            this.dispose();
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
                player.takeBonus(currentItem, difficultyCounter);
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
        ammo.setText(String.valueOf( "ammo: " + player.getAmmo()));
        ammo.setFontScale(FONT_SCALE);
        health.setText(String.valueOf("health: " + player.getHp()));
        health.setFontScale(FONT_SCALE);
        score.setText(String.valueOf( "score:  " + player.getScore()));
        score.setFontScale(FONT_SCALE);
        difficultyLabel.setText("dfclty: " + difficultyCounter);
        difficultyLabel.setFontScale(FONT_SCALE);
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
