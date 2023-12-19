package com.mygdx.game.producers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.GameObj.Bullet;
import com.mygdx.game.GameParams;
import com.mygdx.game.interfaces.IRenderable;
import com.mygdx.game.interfaces.IUpdatable;

import javax.swing.*;
import java.util.LinkedList;

public class BulletProducer implements IRenderable, IUpdatable {
    LinkedList<Bullet> bulletList;
    Bullet currentBullet;
    float x,y;

    public LinkedList<Bullet> getBulletList(){
        return bulletList;
    }

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
                i--;
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
    public void dispose(){
        bulletList.removeAll(bulletList);
    }
}
