package com.mygdx.game.GameObj;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.GameParams;
import com.mygdx.game.TestGame;

abstract public class Item extends GameObject{
    static final float ITEM_SIZE = GameParams.ITEM_SIZE;
    ActorStats bonusStats;
    Sound pickupSound;
    float soundVolume;
    public void playPickupSound(){
        this.pickupSound.play(this.soundVolume * TestGame.soundVolume);
    }
    @Override
    public void render(SpriteBatch batch) {
        batch.draw(texture,x,y,textureWidth,textureHeight);
    }
}
