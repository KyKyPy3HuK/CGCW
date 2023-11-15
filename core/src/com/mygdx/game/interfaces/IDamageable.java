package com.mygdx.game.interfaces;

import com.mygdx.game.GameObj.Actor;
import com.mygdx.game.GameObj.Bullet;

public interface IDamageable {
    int takeBulletDamage(Bullet bullet);
    int takeMeleeDamage(Actor actor);
}
