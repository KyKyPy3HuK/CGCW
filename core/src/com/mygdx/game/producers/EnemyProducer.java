package com.mygdx.game.producers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.GameObj.Enemy;
import com.mygdx.game.GameObj.EnemyKamikaze;
import com.mygdx.game.GameObj.EnemyRanger;
import com.mygdx.game.GameObj.EnemyRifleman;
import com.mygdx.game.GameParams;
import com.mygdx.game.interfaces.IRenderable;
import com.mygdx.game.interfaces.IUpdatable;

import java.util.LinkedList;
import java.util.Random;

public class EnemyProducer implements IRenderable, IUpdatable {
    Random rnd = new Random(System.currentTimeMillis());
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
    public void addEnemyRandom( int difficulty){

        int enemyType = rnd.nextInt(0, difficulty);
        if (enemyType < GameParams.KAMIKAZE_SPAWN_WEIGHT){
            enemyType = GameParams.KAMIKAZE;
        }
        else if (enemyType < GameParams.RIFLEMAN_SPAWN_WEIGHT){
            enemyType = GameParams.RIFLEMAN;
        } else if (enemyType < GameParams.RANGER_SPAWN_WEIGHT) {
            enemyType = GameParams.RANGER;
        }

        switch (enemyType){
            case (GameParams.RIFLEMAN):{
                enemyList.add(new EnemyRifleman(
                        rnd.nextInt(0,GameParams.WORLD_WIDTH-GameParams.ACTOR_SIZE),
                        GameParams.WORLD_HEIGHT,
                        rnd.nextFloat(GameParams.RIFLEMAN_MIN_MOVE_RANGE,GameParams.RIFLEMAN_MAX_MOVE_RANGE),
                        new Vector2(0, - (rnd.nextInt(5,10))), difficulty));
                break;
            }
            case (GameParams.KAMIKAZE):{
                int spawnCounter = (int) rnd.nextFloat(1f, (float) (1.5f * (Math.E - Math.log10(difficulty) + 1)));
                for(int i = 0; i < spawnCounter;++i){
                    enemyList.add(new EnemyKamikaze(
                            rnd.nextInt(0,GameParams.WORLD_WIDTH-GameParams.ACTOR_SIZE),
                            GameParams.WORLD_HEIGHT,
                            rnd.nextFloat(GameParams.RIFLEMAN_MIN_MOVE_RANGE,GameParams.RIFLEMAN_MAX_MOVE_RANGE),
                            new Vector2(0, - (rnd.nextInt(30,40))), difficulty));

                }
                break;
            }
            case (GameParams.RANGER):{
                enemyList.add(new EnemyRanger(
                        rnd.nextInt(0,GameParams.WORLD_WIDTH-GameParams.ACTOR_SIZE),
                        GameParams.WORLD_HEIGHT,
                        rnd.nextFloat(GameParams.RIFLEMAN_MIN_MOVE_RANGE,GameParams.RIFLEMAN_MAX_MOVE_RANGE),
                        new Vector2(0, - (rnd.nextInt(3,5))), difficulty));
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
    public void dispose(){
        enemyList.removeAll(enemyList);
    }
}
