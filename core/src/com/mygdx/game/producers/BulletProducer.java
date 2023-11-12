package com.mygdx.game.producers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.GameObj.Bullet;
import com.mygdx.game.GameParams;
import com.mygdx.game.interfaces.IRenderable;

import javax.swing.*;
import java.util.LinkedList;

public class BulletProducer implements IRenderable {
    LinkedList<Bullet> bulletList;
    Bullet currentBullet;
    float x,y;

    public BulletProducer(){
         bulletList = new LinkedList<>();
    }

    public void addBullet(Bullet bullet){
        bulletList.add(bullet);
    }

    @Override
    public void render(SpriteBatch batch) {
        for (int i = 0; i < bulletList.size(); i++){
            currentBullet = bulletList.get(i);
            x = currentBullet.getX();
            y = currentBullet.getY();
            if (x > GameParams.WORLD_WIDTH + 5 || x < -5 || y > GameParams.WORLD_HEIGHT + 5 || y < -5){
                bulletList.remove(i);
            }
            else{
                currentBullet.render(batch);
            }
        }
    }
    public void update(float deltaTime){
        for (int i = 0; i < bulletList.size(); i++){
            bulletList.get(i).update(deltaTime);
        }
    }
}