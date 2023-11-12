package com.mygdx.game.GameObj;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.interfaces.IShootable;
import com.mygdx.screens.MainScreen;
import jdk.internal.org.jline.utils.ShutdownHooks;

import javax.swing.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Timer;

public class Player extends Actor implements IShootable {
    boolean doubleShot = false;
    double  speedConst = 0.2;
    double playerSpeed = 1;
    float playerMaxSpeed = 30;
    float timeSinceLastShoot = 0f;
    float reloadTime;
    float shootsPerMinute;

    List<Bullet> bulletList;
    public Player(int x, int y, Vector2 speed){
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.bulletTexture = new Texture("bulletSmall.png");
        this.texture = new Texture("spaceShip.png");
        //bulletList = new LinkedList<>();
        bulletSpd = new Vector2(0,50);
        bulletDmg = 3;
        this.bulletSpread = 1;
        shootsPerMinute = 600;
        this.reloadTime = 60 / shootsPerMinute;
        this.bulletSize = 1;
        super.playerTeam = PLAYER_TEAM;
    }

    @Override
    public void die(int killerTeam) {

    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(this.texture,this.x,this.y,ACTOR_SIZE,ACTOR_SIZE);
        //bulletListRender(batch);
    }

    public Bullet shootBullet(){
        return new Bullet(this.x,this.y,this);
    }

    @Override
    public void shoot() {
         // seconds
        if (isReadyToShoot()){
            if(doubleShot){
                MainScreen.bulletProducer.addBullet(new Bullet(this.x + 1,this.y + 4,this));
                //bulletList.add(new Bullet(this.x + 1,this.y + 4, this));
                doubleShot = false;
            }
            else
            {
                MainScreen.bulletProducer.addBullet(new Bullet(this.x + 4,this.y + 4,this));
                //bulletList.add(new Bullet(this.x + 4,this.y + 4,this));

                doubleShot = true;
            }
            timeSinceLastShoot = 0;
        }
    }
    boolean isReadyToShoot(){
        return (timeSinceLastShoot - reloadTime >= 0);
    }

    void bulletListUpdate(float deltaTime){

        for (int i = 0; i < bulletList.size(); ++i)
        {
            bulletList.get(i).update(deltaTime);;
            if (bulletList.get(i).y > 100) {
                bulletList.remove(i);
            }
        }
    }
    void bulletListRender(SpriteBatch batch){
        for (Bullet bullet: bulletList
        ) {
            bullet.render(batch);
        }
    }
    public void update(float deltaTime){
        super.update(deltaTime);

        timeSinceLastShoot += deltaTime;

        if(!playerKeyControl()[0]){
            playerSpeedDownX();
        }
        if(!playerKeyControl()[1]){
            playerSpeedDownY();
        }

        //bulletListUpdate(deltaTime);

    }
    boolean[] playerKeyControl(){
        boolean isMovingX = false;
        boolean isMovingY = false;
        //moving
        if (Gdx.input.isKeyPressed(Input.Keys.W)){
            this.speed.y += playerSpeed;
            isMovingY = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)){
            this.speed.y -= playerSpeed;
            isMovingY = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)){
            this.speed.x += playerSpeed;
            isMovingX = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)){
            this.speed.x -= playerSpeed;
            isMovingX = true;
        }
        if (this.speed.x > this.playerMaxSpeed){
            this.speed.x = this.playerMaxSpeed;
        }
        if (this.speed.y > this.playerMaxSpeed){
            this.speed.y = this.playerMaxSpeed;
        }
        if(this.speed.x < -(this.playerMaxSpeed)){
            this.speed.x = -(this.playerMaxSpeed);
        }
        if(this.speed.y < -(this.playerMaxSpeed)){
            this.speed.y = -(this.playerMaxSpeed);
        }

        //shooting
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)){
            this.shoot();
        }
        return new boolean[]{isMovingX, isMovingY};
    }
    void playerSpeedDownX(){
        if (this.speed.x > speedConst){
            this.speed.x -= speedConst;
        }
        else if(this.speed.x < -(speedConst)){
            this.speed.x += speedConst;
        }

        if (this.speed.x > 0 && this.speed.x < speedConst){
            this.speed.x = 0;
        } else if (this.speed.x < 0 && this.speed.x > -(speedConst)) {
            this.speed.x = 0;
        }
    }
    void playerSpeedDownY(){
        if(this.speed.y > speedConst){
            this.speed.y -= speedConst;
        } else if (this.speed.y < -(speedConst)) {
            this.speed.y += speedConst;
        }

        if (this.speed.y > 0 && this.speed.y < speedConst){
            this.speed.y = 0;
        } else if (this.speed.y < 0 && this.speed.y > -(speedConst)) {
            this.speed.y = 0;
        }
    }
}
