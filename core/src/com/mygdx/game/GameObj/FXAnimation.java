package com.mygdx.game.GameObj;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.GameParams;
import com.mygdx.game.interfaces.IRenderable;
import com.mygdx.game.interfaces.IUpdatable;

public class FXAnimation implements IRenderable, IUpdatable {

    Animation<TextureRegion> animation;
    float xPos;
    float yPos;

    float FXWidth;
    float FXHeight;
    float durationTime;
    float lifeTime;
    boolean isEnded;
    TextureRegion keyFrame;

    public static Animation<TextureRegion> setAnimation(Texture texture, int frameCols, int frameRows, float fps){
        TextureRegion[][] tmp = TextureRegion.split(texture,
                texture.getWidth() / frameCols,
                texture.getHeight() / frameRows);

        int index = 0;
        TextureRegion[] frames = new TextureRegion[frameRows * frameCols];
        for (int i = 0; i < frameRows; i++){
            for (int j = 0; j < frameCols; j++){
                frames[index++] = tmp[i][j];
            }
        }

        return new Animation<TextureRegion>(0.025f, frames);
    }

    public FXAnimation(Animation<TextureRegion> animation, float x, float y, float FXSize){
        this.animation = animation;
        this.xPos = x;
        this.yPos = y;
        this.durationTime = animation.getAnimationDuration();
        this.lifeTime = 0;
        this.isEnded = false;
        this.FXHeight = FXSize;
        this.FXWidth = FXSize;
    }

    public FXAnimation(Animation<TextureRegion> animation, float x, float y, float FXWidth, float FXHeight){
        this.animation = animation;
        this.xPos = x;
        this.yPos = y;
        this.durationTime =  animation.getAnimationDuration();
        this.lifeTime = 0;
        this.isEnded = false;
        this.FXHeight = FXHeight;
        this.FXWidth = FXWidth;
    }

    public boolean getIsEnded(){
        return this.isEnded;
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(keyFrame,xPos,yPos, FXWidth, FXHeight);
    }

    @Override
    public void update(float deltaTime) {
        this.lifeTime += deltaTime;
        if (this.lifeTime >= durationTime){
           isEnded = true;
        }
        keyFrame = animation.getKeyFrame(lifeTime,false);
    }
}
