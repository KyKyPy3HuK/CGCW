package com.mygdx.game.GameObj;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.GameParams;

abstract public class Item extends GameObject{
    static final float ITEM_SIZE = GameParams.ITEM_SIZE;
    ActorStats bonusStats;
}
