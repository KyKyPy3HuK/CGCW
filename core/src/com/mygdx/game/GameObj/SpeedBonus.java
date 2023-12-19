package com.mygdx.game.GameObj;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class SpeedBonus extends Item{
    public SpeedBonus(float x, float y, Vector2 speed){
        this.texture = new Texture("speedBonus.png");
        this.bonusStats = new ActorStats();
        this.bonusStats.acceleration = 0.3f;
        this.bonusStats.maxSpeed = 0.8f;
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.textureHeight = ITEM_SIZE;
        this.textureWidth = ITEM_SIZE;
        this.collisionHeight = ITEM_SIZE;
        this.collisionWidth = ITEM_SIZE;
        this.collisionOffsetX = 0;
        this.collisionOffsetY = 0;
        this.collisionRect = new Rectangle(x,y,collisionWidth,collisionHeight);
        this.pickupSound = Gdx.audio.newSound(Gdx.files.internal("sounds/powerup.wav"));
        this.soundVolume = 0.4f;
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(texture,x,y,textureWidth,textureHeight);
    }
}
