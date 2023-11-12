package com.mygdx.game.GameObj;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class ActorStats {

    float speedAclr;
    int moveRange;
    public int hp;
    public int hpRegen;
    public int bulletDmg;
    public int meleeDmg;
    public int bulletSpd;
    public Vector2 speed;
    public int team;

    public ActorStats(){
        hp = 100;
        hpRegen = 0;
        bulletDmg = 0;
        meleeDmg = 0;
        bulletSpd = 10;
        team = -1;
        speed = new Vector2(0,0);
        moveRange = 100;
        speedAclr = 0.5f;
    }
}
