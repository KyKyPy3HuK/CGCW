package com.mygdx.game.producers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.GameObj.Enemy;
import com.mygdx.game.interfaces.IRenderable;
import com.mygdx.game.interfaces.IUpdatable;

import java.util.LinkedList;

public class EnemyProducer implements IRenderable, IUpdatable {

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
            }
        }
    }
}
