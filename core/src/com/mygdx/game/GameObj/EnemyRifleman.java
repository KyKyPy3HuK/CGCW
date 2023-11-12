package com.mygdx.game.GameObj;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.GameParams;
import com.mygdx.game.interfaces.IShootable;

public class EnemyRifleman extends Enemy implements IShootable {


    public EnemyRifleman(float x, float y, Vector2 speed){
        super();
        this.hp = 100;
        this.texture = new Texture("enemyRifleman.png");
        this.speed = new Vector2(speed);
        this.textureHeight = GameParams.ACTOR_SIZE;
        this.textureWidth = GameParams.ACTOR_SIZE;
        this.hitScore = GameParams.RIFLEMAN_HIT_SCORE;
        this.killScore = GameParams.RIFLEMAN_KILL_SCORE;
        this.x = x;
        this.y = y;
        this.collisingRect = new Rectangle(this.x,this.y,textureWidth,textureHeight);
    }
    @Override
    public void shoot(){

    };

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
