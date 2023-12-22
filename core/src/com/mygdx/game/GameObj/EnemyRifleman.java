package com.mygdx.game.GameObj;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.GameParams;
import com.mygdx.game.TestGame;
import com.mygdx.game.interfaces.IShootable;
import com.mygdx.screens.GameScreen;

public class EnemyRifleman extends Enemy implements IShootable {
    boolean doubleShot;
    float timeSinceLastShoot;
    float reloadTime;
    public EnemyRifleman(float x, float y, float moveRange, Vector2 speed, int difficulty){
        super();
        this.hp = 60 + difficulty;
        this.meleeDmg = 10;
        this.bulletDmg = 10;
        this.texture = new Texture("enemyRifleman.png");
        this.bulletTexture = new Texture("bulletSmallRed.png");
        this.speed = new Vector2(speed);
        this.textureHeight = GameParams.ACTOR_SIZE;
        this.textureWidth = GameParams.ACTOR_SIZE;
        this.collisionHeight = GameParams.ACTOR_SIZE;
        this.collisionWidth = GameParams.ACTOR_SIZE;
        this.hitScore = GameParams.RIFLEMAN_HIT_SCORE;
        this.killScore = GameParams.RIFLEMAN_KILL_SCORE;
        this.colliseScore = GameParams.RIFLEMAN_HIT_SCORE * 3;
        this.x = x;
        this.y = y;
        super.spawnPosX = x;
        super.moveRange = moveRange;
        this.maxSpeed = GameParams.RIFLEMAN_MAX_SPEED;
        this.acceleration = GameParams.RIFLEMAN_ACCELERATION;
        this.speed.x = rnd.nextFloat(-maxSpeed, maxSpeed);
        this.bulletSpd = new Vector2(0,-20);
        this.collisionRect.x = this.x;
        this.collisionRect.y = this.y;
        this.collisionRect.height = collisionHeight;
        this.collisionRect.width = collisionWidth;
        this.doubleShot = false;
        this.reloadTime = 1f;
        this.timeSinceLastShoot = rnd.nextFloat(-0f, reloadTime);
        this.bulletSpread = 3;
        this.bulletSize = 1;
        this.shootSound = Gdx.audio.newSound(Gdx.files.internal("sounds/hurt.wav"));

    }
    @Override
    public void shoot(){
        if (isReadyToShoot()){
            if(doubleShot){
                GameScreen.bulletProducer.addBullet(new Bullet(this.x + 1,this.y + 1,this));
                doubleShot = false;
            }
            else
            {
                GameScreen.bulletProducer.addBullet(new Bullet(this.x + 4,this.y + 1,this));
                doubleShot = true;
            }
            timeSinceLastShoot = 0;
            this.shootSound.play(TestGame.soundVolume);
        }
    };
    boolean isReadyToShoot(){
        return (timeSinceLastShoot - reloadTime >= 0);
    }
    @Override
    public void die(int killerTeam) {

    }


    @Override
    public void render(SpriteBatch batch) {
        super.render(batch);
    }

    @Override
    public void spawn() {

    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        this.timeSinceLastShoot += deltaTime;
        shoot();
    }
}
