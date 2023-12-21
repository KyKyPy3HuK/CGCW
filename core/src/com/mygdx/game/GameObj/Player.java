package com.mygdx.game.GameObj;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.GameParams;
import com.mygdx.game.interfaces.IShootable;
import com.mygdx.screens.GameScreen;

public class Player extends Actor implements IShootable {
    int score;
    int ammo;
    boolean doubleShot = false;
    double  speedConst = GameParams.WORLD_G;
    float playerMaxSpeed = 30;
    float timeSinceLastShoot = 0f;
    float timeSinceLastAddAmmo = 0f;
    float reloadTime;
    float shootsPerMinute;
    float addAmmoTime;




    public int getScore(){
        return score;
    }
    public void addScore(int addScore){
        this.score += addScore;
    }

    public int getAmmo(){
        return this.ammo;
    }
    public Player(int x, int y, Vector2 speed){
        this.hp = 1000;
        this.score = 0;
        this.ammo = 200;
        this.acceleration = 1.f;
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.bulletTexture = new Texture("bulletSmall.png");
        this.texture = new Texture("spaceShip.png");
        this.bulletSpd = new Vector2(0,100);
        this.bulletDmg = 20;
        this.meleeDmg = 40;
        this.bulletSpread = 1;
        this.shootsPerMinute = 600;
        this.reloadTime = 60 / shootsPerMinute;
        this.addAmmoTime = reloadTime * 3;
        this.bulletSize = 1;
        super.playerTeam = GameParams.PLAYER_TEAM;
        super.textureHeight = GameParams.ACTOR_SIZE;
        super.textureWidth = GameParams.ACTOR_SIZE;
        this.collisionOffsetX = 1;
        this.collisionOffsetY = 1;
        this.collisionHeight = 4;
        this.collisionWidth = 4;
        this.collisionRect.x = this.x + collisionOffsetX;
        this.collisionRect.y = this.y + collisionOffsetY;
        this.collisionRect.height = collisionHeight;
        this.collisionRect.width = collisionWidth;
        this.hurtSound = Gdx.audio.newSound(Gdx.files.internal("sounds/hurtPlayer.wav"));
        this.shootSound = Gdx.audio.newSound(Gdx.files.internal("sounds/shoot.wav"));
    }
    @Override
    public void die(int killerTeam) {

    }
    public void reverseSpeed(float x, float y){
        this.speed.y =  (this.y - y) * 5 ;
        this.speed.x =  (this.x - x) * 5;
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(this.texture,this.x,this.y,GameParams.ACTOR_SIZE,GameParams.ACTOR_SIZE);
    }
    public Bullet shootBullet(){
        return new Bullet(this.x,this.y,this);
    }
    @Override
    public void shoot() {
         // seconds
        if (isReadyToShoot()){
            if(doubleShot){
                GameScreen.bulletProducer.addBullet(new Bullet(this.x + 1,this.y + 4,this));
                ammo --;
                doubleShot = false;
            }
            else
            {
                GameScreen.bulletProducer.addBullet(new Bullet(this.x + 4,this.y + 4,this));
                ammo--;
                doubleShot = true;
            }
            this.shootSound.play(0.7f);
            timeSinceLastShoot = 0;
        }
    }
    boolean isReadyToShoot(){
        return (timeSinceLastShoot - reloadTime >= 0) && ammo > 0;
    }
    boolean isReadyToAddAmmo(){
        return timeSinceLastAddAmmo - addAmmoTime >= 0;
    }
    public void addAmmo(){
        if (isReadyToAddAmmo()){
            timeSinceLastAddAmmo = 0f;
            this.ammo ++;
        }
    }
    public void update(float deltaTime){
        super.update(deltaTime);

        if (this.x > GameParams.WORLD_WIDTH - 6){
            this.x = GameParams.WORLD_WIDTH - 6;
            this.speed.x = 0;
        } else if (this.x < 0){
            this.x = 0;
            this.speed.x = 0;
        }

        if (this.y > GameParams.WORLD_HEIGHT - 6){
            this.y = GameParams.WORLD_HEIGHT - 6;
            this.speed.y = 0;
        } else if (this.y < 0){
            this.y = 0;
            this.speed.y = 0;
        }

        timeSinceLastShoot += deltaTime;
        timeSinceLastAddAmmo+= deltaTime;
        addAmmo();
        if(!playerKeyControl()[0]){
            playerSpeedDownX();
        }
        if(!playerKeyControl()[1]){
            playerSpeedDownY();
        }
    }
    boolean[] playerKeyControl(){
        boolean isMovingX = false;
        boolean isMovingY = false;
        //moving
        if (Gdx.input.isKeyPressed(Input.Keys.W)){
            this.speed.y += acceleration;
            isMovingY = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)){
            this.speed.y -= acceleration;
            isMovingY = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)){
            this.speed.x += acceleration;
            isMovingX = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)){
            this.speed.x -= acceleration;
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

    public void takeBonus(Item item, int difficulty){
        item.playPickupSound();
        this.score += item.bonusStats.score * difficulty;
        this.hp += item.bonusStats.hp;
        this.ammo += item.bonusStats.ammo;
        this.bulletDmg += item.bonusStats.bulletDmg;
        this.meleeDmg += item.bonusStats.meleeDmg;
        this.acceleration += item.bonusStats.acceleration;
        this.playerMaxSpeed += item.bonusStats.maxSpeed;
        this.shootsPerMinute += item.bonusStats.shootingRate;
        this.reloadTime = 60 / this.shootsPerMinute;
        this.addAmmoTime = this.reloadTime * 3;
    }
}
