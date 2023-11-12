package com.mygdx.game.interfaces;

import com.badlogic.gdx.math.Rectangle;

public interface ICollisionable {
    Rectangle getCollisionRect();
    boolean isIntersects(Rectangle otherCollisionRect);
}
