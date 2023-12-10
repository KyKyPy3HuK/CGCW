package com.mygdx.game.GameObj;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Bullet extends GameObject{
    Texture bulletHitSprite;
    Animation<TextureRegion> bulletHitAnimation;
    public float bulletHitAnimationSize = 2;

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
        this.playerTeam = actor.playerTeam;
        this.x = x;
        this.y = y;
        this.speed = new Vector2(actor.bulletSpd);
        this.speed.y += actor.speed.y / 2;
        this.speed.x += actor.speed.x / 2 + rnd.nextFloat(-actor.bulletSpread, actor.bulletSpread);
        this.damage = actor.bulletDmg;
        this.collisionRect.x = this.x;
        this.collisionRect.y = this.y;
        this.collisionRect.height = textureHeight;
        this.collisionRect.width = textureWidth;
        this.bulletHitAnimation = FXAnimation.setAnimation(new Texture("explosionSmall2.png"), 8,1, 24f);
    }

    public Animation<TextureRegion> getHitAnimation(){
        return this.bulletHitAnimation;
    }

    @Override
    public void update(float deltaTime){
        super.update(deltaTime);
    }
}
