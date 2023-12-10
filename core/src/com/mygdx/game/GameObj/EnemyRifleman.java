package com.mygdx.game.GameObj;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.GameParams;
import com.mygdx.game.interfaces.IShootable;
import com.mygdx.screens.GameScreen;

public class EnemyRifleman extends Enemy implements IShootable {
    boolean doubleShot;
    float timeSinceLastShoot;
    float reloadTime;
    public EnemyRifleman(float x, float y, Vector2 speed){
        super();
        this.hp = 100;
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
        this.bulletSpd = new Vector2(0,-20);
        this.collisionRect.x = this.x;
        this.collisionRect.y = this.y;
        this.collisionRect.height = collisionHeight;
        this.collisionRect.width = collisionWidth;
        this.doubleShot = false;
        this.timeSinceLastShoot = 0;
        this.reloadTime = 1f;
        this.bulletSpread = 3;
        this.bulletSize = 1;
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
