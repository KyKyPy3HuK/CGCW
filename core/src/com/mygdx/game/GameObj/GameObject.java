package com.mygdx.game.GameObj;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.interfaces.ICollisionable;
import com.mygdx.game.interfaces.IRenderable;
import com.mygdx.game.interfaces.IUpdatable;

import java.util.Random;

abstract public class GameObject implements IRenderable , IUpdatable , ICollisionable {
    // Поля
    float x,  y;
    float textureWidth, textureHeight;
    float collisionWidth, collisionHeight;
    float collisionOffsetX = 0, collisionOffsetY = 0;
    Vector2 speed;
    static Random rnd = new Random();
    Texture texture;
    Rectangle collisionRect = new Rectangle(x + collisionOffsetX,y + collisionOffsetY, collisionWidth, collisionHeight);

    //методы
    @Override
    public Rectangle getCollisionRect() {
        return collisionRect;
    }
    @Override
    public boolean isIntersects(Rectangle otherCollisionRect){
        return this.getCollisionRect().overlaps(otherCollisionRect);
    }
    public float getX(){
        return this.x;
    }
    public float getY(){
        return this.y;
    }
    @Override
    public void update(float deltaTime){
        this.x += this.speed.x * deltaTime;
        this.y += this.speed.y * deltaTime;
        collisionRect.x = x + collisionOffsetX;
        collisionRect.y = y + collisionOffsetY;
        collisionRect.width = collisionWidth;
        collisionRect.height = collisionHeight;
    }
}
