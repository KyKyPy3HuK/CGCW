package com.mygdx.game.GameObj;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Asteroid  extends GameObject{
    @Override
    public void update(float deltaTime){
        this.x = this.x + this.speed.x;
        this.y = this.y + this.speed.y;
    }


    public void render(SpriteBatch batch) {
        batch.draw(this.texture,this.x,this.y);
    }

    public Asteroid(float x, float y , Vector2 speed){
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.texture = new Texture("asteroid.png");
    }
    public Asteroid(float x, float y , Vector2 speed, Texture texture){
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.texture = texture;
    }

}
