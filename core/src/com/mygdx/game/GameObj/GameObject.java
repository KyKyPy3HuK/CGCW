package com.mygdx.game.GameObj;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.interfaces.IRenderable;
import com.mygdx.game.interfaces.IUpdatable;

import java.util.Random;

abstract public class GameObject implements IRenderable , IUpdatable {
    float x,  y;
    int textureWidth, textureHeight;

    Vector2 speed;
    static Random rnd = new Random();

    Texture texture;
    @Override
    public void update(float deltaTime){
        this.x += this.speed.x * deltaTime;
        this.y+= this.speed.y * deltaTime;
    }
    public float getX(){
        return this.x;
    }
    public float getY(){
        return this.y;
    }
}
