package com.mygdx.game.GameObj;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.GameParams;
import com.mygdx.game.interfaces.ISpawnable;

abstract public class Enemy extends Actor implements ISpawnable {

    int meleeDmg;
    int bulletDmg;
    int bulletSpd;
    int hpRegen;
    public int hitScore;
    public int colliseScore;
    public int killScore;

    float moveRange;
    public Enemy(ActorStats stats){
        this.hp = stats.hp;
        this.speed = stats.speed;
        this.playerTeam = 2;
        this.meleeDmg = stats.meleeDmg;
        this.bulletDmg = stats.bulletDmg;
        this.bulletSpd= stats.bulletSpd;
        this.hpRegen = stats.hpRegen;
        this.moveRange = stats.moveRange;
    }
    public Enemy(){
        super.playerTeam = GameParams.ENEMY_TEAM;
    }
    @Override
    public void update(float deltaTime){
        super.update(deltaTime);
        if (this.y < 0){
            this.hp = -100;
        }
    }
    @Override
    public void render(SpriteBatch batch) {
        batch.draw(this.texture,this.x,this.y,this.textureWidth,this.textureHeight);
    }

    @Override
    public int takeBulletDamage(Bullet bullet) {
        this.hp -= bullet.damage;
        if (this.hp <= 0){
            return this.killScore + this.hitScore;
        }
        return this.hitScore;
    }
}
