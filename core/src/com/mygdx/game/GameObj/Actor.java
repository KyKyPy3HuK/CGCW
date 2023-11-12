package com.mygdx.game.GameObj;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.interfaces.IMortal;

public abstract class Actor extends GameObject implements IMortal {
    static final int PLAYER_TEAM = 0;
    static final int ALLY_TEAM = 1;
    static final int ENEMY_TEAM = 2;
    public int playerTeam;
    public static final int ACTOR_SIZE = 6;
    public int bulletDmg,
            meleeDmg;
    Vector2 bulletSpd;
    public Texture bulletTexture;
    public int bulletSize;
    public int bulletSpread;
    int hp;

}
