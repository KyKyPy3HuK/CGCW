package com.mygdx.game.GameObj;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class ActorStats {
    // main parameters
    public float acceleration;
    public float maxSpeed;
    public int moveRange;
    public int ammo;
    public int hp;
    public int hpRegen;
    public Vector2 speed;
    public int team;

    // shooting and damage
    public float bulletSize;
    public int bulletDmg;
    public int shootingRate;
    public int meleeDmg;
    public Vector2 bulletSpd;

    public ActorStats() {
        this.acceleration = 0;
        this.moveRange = 0;
        this.ammo = 0;
        this.hp = 0;
        this.hpRegen = 0;
        this.speed = new Vector2(0,0);
        this.maxSpeed = 0;
        this.team = 0;
        this.bulletSize = 0;
        this.bulletDmg = 0;
        this.shootingRate = 0;
        this.meleeDmg = 0;
        this.bulletSpd = new Vector2(0,0);
    }

}