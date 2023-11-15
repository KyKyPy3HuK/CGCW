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
    public Vector2 bulletSpd;
    public Vector2 speed;
    public int team;

    public ActorStats(){
        hp = 0;
        hpRegen = 0;
        bulletDmg = 0;
        meleeDmg = 0;
        bulletSpd = new Vector2(0,0);
        team = 0;
        speed = new Vector2(0,0);
        moveRange = 0;
        speedAclr = 0;
    }
}
