package com.mygdx.game.GameObj;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AsteroidProducer{
    List<Asteroid> asteroidList;
    static  List<Texture> textureList = new ArrayList<>(3);

    static Random random = new Random();
    public AsteroidProducer(float[] coordsX, float[] coordsY, Vector2[] speeds, int count){
        asteroidList = new ArrayList<>(count);

        for (int i = 0; i < count; ++i){

            asteroidList.add(new Asteroid(coordsX[i],coordsY[i],speeds[i],textureList.get(random.nextInt(0,3))));
        }
    }

    public AsteroidProducer(){
        asteroidList = new ArrayList<>();
        textureList.add(new Texture("bulletSmall.png"));
    }

    public void add(Asteroid asteroid){
        asteroidList.add(asteroid);
    }

    static public Asteroid generateRandom(){
        float x = random.nextFloat(0,1600.0f) /4;
        float y = random.nextFloat(0,900.0f) /4;
        Vector2 speed = new Vector2(random.nextFloat(-1.0f,1.0f),random.nextFloat(-1.0f,1.0f));

        return new Asteroid(x,y,speed,textureList.get(random.nextInt(0,textureList.size())));
    }

    public void update(float deltaTime){
        for (Asteroid asteroid:
             asteroidList) {
            asteroid.update(deltaTime);
        }
    }

    public void render(SpriteBatch batch){
        for (Asteroid asteroid:
                asteroidList) {
            asteroid.render(batch);
        }
    }
}
