package com.mygdx.game.producers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.GameObj.Item;
import com.mygdx.game.GameParams;
import com.mygdx.game.interfaces.IRenderable;
import com.mygdx.game.interfaces.IUpdatable;

import java.util.ArrayList;
import java.util.LinkedList;

public class ItemProducer implements IRenderable, IUpdatable {
    LinkedList<Item> itemList;

    public  ArrayList<Integer> spawnArray;

    public ItemProducer(){
        itemList = new LinkedList<>();
        spawnArray = new ArrayList<>();
        for (int i = 0; i < GameParams.AMMO_BONUS_SPAWN_WEIGHT; ++i){
            spawnArray.add(GameParams.AMMO_BONUS);
        }
        for (int i = 0; i < GameParams.MED_BONUS_SPAWN_WEIGHT; ++i){
            spawnArray.add(GameParams.MED_BONUS);
        }
        for (int i = 0; i < GameParams.RELOAD_BONUS_SPAWN_WEIGHT; ++i){
            spawnArray.add(GameParams.RELOAD_BONUS);
        }
        for (int i = 0; i < GameParams.SPEED_SPAWN_WEIGHT; ++i){
            spawnArray.add(GameParams.SPEED_BONUS);
        }
        for (int i = 0; i < GameParams.BULLET_DAMAGE_BONUS_SPAWN_WEIGHT; ++i){
            spawnArray.add(GameParams.BULLET_DAMAGE_BONUS);
        }
        for (int i = 0; i < GameParams.MELEE_DAMAGE_BONUS_SPAWN_WEIGHT; ++i){
            spawnArray.add(GameParams.MELEE_DAMAGE_BONUS);
        }
        for (int i = 0; i < GameParams.SCORE_BONUS_SPAWN_WEIGHT; ++i){
            spawnArray.add(GameParams.SCORE_BONUS);
        }
    }

    public void addItem(Item newItem){
        this.itemList.add(newItem);
    }

    public Item getItem(int index){
        return this.itemList.get(index);
    }

    public void removeItem(int index){
        this.itemList.remove(index);
    }

    public int getItemsCount(){
        return this.itemList.size();
    }

    @Override
    public void render(SpriteBatch batch) {
        for (int i = 0; i < itemList.size(); i++){
            itemList.get(i).render(batch);
        }
    }

    @Override
    public void update(float deltaTime) {
        Item currentItem;
        for (int i = 0; i < itemList.size(); i++) {
            currentItem =  itemList.get(i);
            currentItem.update(deltaTime);
            if(currentItem.getY() < -5){
                itemList.remove(i);
                i--;
            }
        }

    }

    public void dispose(){
        itemList.removeAll(itemList);
    }
}
