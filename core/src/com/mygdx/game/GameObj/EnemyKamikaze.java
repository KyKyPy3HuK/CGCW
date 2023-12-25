package com.mygdx.game.GameObj;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.GameParams;
import com.mygdx.game.TestGame;
import com.mygdx.game.interfaces.IShootable;
import com.mygdx.screens.GameScreen;

public class EnemyKamikaze extends Enemy {

    public EnemyKamikaze(float x, float y, float moveRange, Vector2 speed, int difficulty){
        super();
        this.hp = 30 + (difficulty / 2);
        this.meleeDmg = 30 + (difficulty / 10);
        this.texture = new Texture("enemyKamikaze.png");
        this.speed = new Vector2(speed);
        this.textureHeight = GameParams.ACTOR_SIZE;
        this.textureWidth = GameParams.ACTOR_SIZE;
        this.collisionHeight = GameParams.ACTOR_SIZE;
        this.collisionWidth = GameParams.ACTOR_SIZE;
        this.hitScore = GameParams.RIFLEMAN_HIT_SCORE;
        this.killScore = GameParams.RIFLEMAN_KILL_SCORE;
        this.colliseScore = GameParams.RIFLEMAN_HIT_SCORE * 1;
        this.x = x;
        this.y = y;
        super.spawnPosX = x;
        super.moveRange = moveRange;
        this.maxSpeed = GameParams.KAMIKAZE_MAX_SPEED;
        this.acceleration = GameParams.KAMIKAZE_ACCELERATION;
        this.speed.x = rnd.nextFloat(-maxSpeed, maxSpeed);
        this.bulletSpd = new Vector2(0,-20);
        this.collisionRect.x = this.x;
        this.collisionRect.y = this.y;
        this.collisionRect.height = collisionHeight;
        this.collisionRect.width = collisionWidth;
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
    }
}