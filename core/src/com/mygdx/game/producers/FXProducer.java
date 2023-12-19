package com.mygdx.game.producers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.GameObj.FXAnimation;
import com.mygdx.game.interfaces.IRenderable;
import com.mygdx.game.interfaces.IUpdatable;

import java.util.LinkedList;

public class FXProducer implements IRenderable, IUpdatable {

    LinkedList<FXAnimation> FXList;

    public FXProducer(){
        this.FXList = new LinkedList<>();
    }

    public void addFX(FXAnimation fxAnimation){
        this.FXList.add(fxAnimation);
    }
    @Override
    public void render(SpriteBatch batch) {
        for (int i = 0; i < FXList.size(); i++){
            FXList.get(i).render(batch);
        }
    }

    @Override
    public void update(float deltaTime) {
        for (int i = 0; i < FXList.size(); i++){
            FXList.get(i).update(deltaTime);
            if (FXList.get(i).getIsEnded()){
                FXList.remove(i);
                i--;
            }
        }
    }
    public void dispose(){
        FXList.removeAll(FXList);
    }
}
