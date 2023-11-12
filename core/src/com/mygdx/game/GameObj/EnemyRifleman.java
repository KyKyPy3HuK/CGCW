package com.mygdx.game.GameObj;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.interfaces.IShootable;

public class EnemyRifleman extends Enemy implements IShootable {


    public EnemyRifleman(Vector2 speed){



    }
    @Override
    public void shoot(){

    };

    @Override
    public void die(int killerTeam) {

    }


    @Override
    public void render(SpriteBatch batch) {
        super.render(batch);
    }

    @Override
    public void spawn() {

    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
    }
}
