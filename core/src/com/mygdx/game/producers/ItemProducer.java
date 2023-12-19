package com.mygdx.game.producers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.GameObj.Item;
import com.mygdx.game.GameParams;
import com.mygdx.game.interfaces.IRenderable;
import com.mygdx.game.interfaces.IUpdatable;

import java.util.LinkedList;

public class ItemProducer implements IRenderable, IUpdatable {

    LinkedList<Item> itemList;

    public ItemProducer(){
        itemList = new LinkedList<>();
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
