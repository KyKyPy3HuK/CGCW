package com.mygdx.game.GameObj;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.TestGame;
import com.mygdx.game.interfaces.IDamageable;
import com.mygdx.game.interfaces.IMortal;

public abstract class Actor extends GameObject implements IMortal, IDamageable {
    public int playerTeam;
    public int bulletDmg,
            meleeDmg;
    Vector2 bulletSpd;
    public Texture bulletTexture;
    public float bulletSize;
    public int bulletSpread;
    int hp;
    float acceleration;


    Sound shootSound;
    Sound hurtSound;
    Sound dieSound;
    Sound meleeHurtSound;
    Sound dieMeleeSound;

    public int getHp(){
        return this.hp;
    }
    public int getMeleeDmg(){
        return this.meleeDmg;
    }
    @Override
    public int takeBulletDamage(Bullet bullet) {
        this.hurtSound.play(TestGame.soundVolume);
        this.hp -= bullet.damage;
        return bullet.damage;
    }

    @Override
    public int takeMeleeDamage(Actor actor) {
        this.hp -= actor.getMeleeDmg();
        return actor.meleeDmg;
    }

    @Override
    public boolean isDead() {
        return this.hp <= 0;
    }
}
