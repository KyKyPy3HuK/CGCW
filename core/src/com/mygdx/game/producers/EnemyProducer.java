package com.mygdx.game.producers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.GameObj.Enemy;
import com.mygdx.game.GameObj.EnemyRifleman;
import com.mygdx.game.GameParams;
import com.mygdx.game.interfaces.IRenderable;
import com.mygdx.game.interfaces.IUpdatable;

import java.util.LinkedList;
import java.util.Random;

public class EnemyProducer implements IRenderable, IUpdatable {
    Random rnd = new Random(15);
    private LinkedList<Enemy> enemyList ;

    public LinkedList<Enemy> getEnemyList(){
        return enemyList;
    }

    public EnemyProducer(){
        enemyList = new LinkedList<>();
    }

    public void addEnemy(Enemy enemy){
        enemyList.add(enemy);
    }
    public void addEnemyRandom(int enemyType){
        switch (enemyType){
            case (GameParams.RIFLEMAN):{
                enemyList.add(new EnemyRifleman(
                        rnd.nextInt(0,GameParams.WORLD_WIDTH-GameParams.ACTOR_SIZE),
                        GameParams.WORLD_HEIGHT,
                        new Vector2(0, - (rnd.nextInt(10,20)))));
                break;
            }
        }
    }
    @Override
    public void render(SpriteBatch batch) {
        for (int i = 0; i < enemyList.size(); i++){
            enemyList.get(i).render(batch);
        }
    }

    @Override
    public void update(float deltaTime) {
        for (int i = 0; i < enemyList.size(); i++){
            enemyList.get(i).update(deltaTime);
            if (enemyList.get(i).isDead()){
                enemyList.remove(i);
                i--;
            }
        }
    }
}
