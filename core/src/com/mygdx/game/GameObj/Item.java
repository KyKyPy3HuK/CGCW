package com.mygdx.game.GameObj;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.GameParams;

abstract public class Item extends GameObject{
    static final float ITEM_SIZE = GameParams.ITEM_SIZE;
    ActorStats bonusStats;
    Sound pickupSound;
    float soundVolume;
    public void playPickupSound(){
        this.pickupSound.play(this.soundVolume);
    }
}
