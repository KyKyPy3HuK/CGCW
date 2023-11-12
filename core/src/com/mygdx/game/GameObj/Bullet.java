package com.mygdx.game.GameObj;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Bullet extends GameObject{
    public int playerTeam;
    int damage;
    int bulletSize;
    @Override
    public void render(SpriteBatch batch) {
        batch.draw(texture,x,y,bulletSize,bulletSize);
    }

    public Bullet(float x,float y, Actor actor){
        this.texture = actor.bulletTexture;
        this.bulletSize = actor.bulletSize;
        this.textureHeight = bulletSize;
        this.textureWidth = bulletSize;
        this.x = x;
        this.y = y;
        this.speed = new Vector2(actor.bulletSpd);
        this.speed.y += actor.speed.y / 2;
        this.speed.x += actor.speed.x / 2 + rnd.nextFloat(- actor.bulletSpread, actor.bulletSpread);
        this.damage = actor.bulletDmg;
        this.collisingRect.x = this.x;
        this.collisingRect.y = this.y;
        this.collisingRect.height = textureHeight;
        this.collisingRect.width = textureWidth;
    }

    @Override
    public void update(float deltaTime){
        super.update(deltaTime);
    }
}
