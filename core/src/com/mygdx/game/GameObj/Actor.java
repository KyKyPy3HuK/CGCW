package com.mygdx.game.GameObj;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.interfaces.IDamageable;
import com.mygdx.game.interfaces.IMortal;

public abstract class Actor extends GameObject implements IMortal, IDamageable {

    public int playerTeam;
    public int bulletDmg,
            meleeDmg;
    Vector2 bulletSpd;
    public Texture bulletTexture;
    public int bulletSize;
    public int bulletSpread;
    int hp;

    @Override
    public int takeBulletDamage(Bullet bullet) {

        this.hp -= bullet.damage;
        return bullet.damage;
    }

    @Override
    public int takeMeeleeDamage(Actor actor) {
        this.hp -= actor.meleeDmg;
        return actor.meleeDmg;
    }

    @Override
    public boolean isDead() {
        return this.hp <= 0;
    }
}
