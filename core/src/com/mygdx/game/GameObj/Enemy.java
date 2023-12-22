package com.mygdx.game.GameObj;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.GameParams;
import com.mygdx.game.TestGame;
import com.mygdx.game.interfaces.ISpawnable;
import com.mygdx.screens.GameScreen;

abstract public class Enemy extends Actor implements ISpawnable {
    int hpRegen;
    public int hitScore;
    public int colliseScore;
    public int killScore;
    float moveRange;
    float acceleration = 0.5f;
    float maxSpeed = 10f;
    float spawnPosX;
    boolean isMovingRight = false;

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
        this.hurtSound = Gdx.audio.newSound(Gdx.files.internal("sounds/hitSmall.wav"));
        this.dieSound = Gdx.audio.newSound(Gdx.files.internal("sounds/blow.wav"));
        this.meleeHurtSound = Gdx.audio.newSound(Gdx.files.internal("sounds/hitMelee.wav"));
        this.dieMeleeSound = Gdx.audio.newSound(Gdx.files.internal("sounds/meleeDie.wav"));
    }


    @Override
    public void update(float deltaTime){
        super.update(deltaTime);
        if (this.y < 0){
            this.hp = -100;
        }
        if (isMovingRight){
            this.speed.x += acceleration;
        }
        else {
            this.speed.x -= acceleration;
        }

        if (this.x > this.moveRange + this.spawnPosX || this.x > GameParams.WORLD_WIDTH - collisionWidth){
            isMovingRight = false;
            speed.x -=  acceleration;
        }
        else if(this.x < spawnPosX - moveRange || this.x < 0){
            isMovingRight = true;
            speed.x += acceleration;
        }
        if (this.speed.x > maxSpeed){
            this.speed.x = maxSpeed;
        } else if (this.speed.x < -maxSpeed) {
            this.speed.x = -maxSpeed;
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
            float spawnChance = rnd.nextFloat(0,(float)Math.E);
            if(spawnChance + Math.log10(GameScreen.difficultyCounter * 2)< Math.E ){
                GameScreen.spawnBonus(this.x, this.y, this.speed.y);
            }

            dieSound.play(TestGame.soundVolume * 0.2f);
            return this.killScore + bullet.damage * this.hitScore;
        }
        hurtSound.play(TestGame.soundVolume * 0.7f);
        return this.hitScore * bullet.damage;
    }
    @Override
    public int takeMeleeDamage(Actor actor) {
        this.hp -= actor.meleeDmg;
        if (this.hp <= 0){
            GameScreen.spawnBonus(this.x, this.y, this.speed.y);

            dieMeleeSound.play(TestGame.soundVolume);
            return this.killScore + this.colliseScore * actor.meleeDmg;
        }
        meleeHurtSound.play(TestGame.soundVolume * 0.7f);
        return this.colliseScore * actor.meleeDmg;
    }
}
