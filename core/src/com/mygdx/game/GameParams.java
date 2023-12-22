package com.mygdx.game;

public class GameParams {


    // str consts
    public static final  String SKIN_STR = new String("Skins/buts.json");

    // Game difficulty consts
    public static final int GAME_MODE_SURVIVAL = 0;
    public static final int GAME_MODE_WAVES = 1;
    public static final int DIFFICULTY_EASY = 0;
    public static final int DIFFICULTY_EASY_DEFAULT_COUNTER = 10;
    public static final int DIFFICULTY_MEDIUM = 1;
    public static final int DIFFICULTY_MEDIUM_DEFAULT_COUNTER = 10;
    public static final int DIFFICULTY_HARD = 2;
    public static final int DIFFICULTY_HARD_DEFAULT_COUNTER = 20;
    public static final float EASY_GAME_TICK = 10f;
    public static final float MEDIUM_GAME_TICK = 5f;
    public static final float HARD_GAME_TICK = 3f;


    // Enemy consts
    public static final int RIFLEMAN = 0;
    public static final int KAMIKAZE = 1;
    public static final int RANGER = 2;



    // Item consts
    public static final int AMMO_BONUS = 0;
    public static final int MED_BONUS = 1;
    public static final int RELOAD_BONUS = 2;
    public static final int SPEED_BONUS = 3;
    public static final int BULLET_DAMAGE_BONUS = 4;
    public static final int MELEE_DAMAGE_BONUS = 5;
    public static final int SCORE_BONUS = 6;

    // World consts
    public static final int WORLD_WIDTH = 70;
    public static final int WORLD_HEIGHT = 100;
    public static final float WORLD_G = 1.f;


    // Bonus spawn chance values
    public static final int MED_BONUS_SPAWN_WEIGHT = 2;
    public static final int AMMO_BONUS_SPAWN_WEIGHT = 4;
    public static final int RELOAD_BONUS_SPAWN_WEIGHT = 2;
    public static final int SPEED_SPAWN_WEIGHT = 1;
    public static final int BULLET_DAMAGE_BONUS_SPAWN_WEIGHT = 1;
    public static final int MELEE_DAMAGE_BONUS_SPAWN_WEIGHT = 1;
    public static final int SCORE_BONUS_SPAWN_WEIGHT = 7;


    // Teams
    public static final int PLAYER_TEAM = 0;
    public static final int ALLY_TEAM = 1;
    public static final int ENEMY_TEAM = 2;


    // Object sizes
    public static final int ACTOR_SIZE = 6;
    public static final float ITEM_SIZE = 4f;


    // Score constants
    public static final int RIFLEMAN_HIT_SCORE = 1;
    public static final int RIFLEMAN_KILL_SCORE = 300;


    // Actor params
    public static final float RIFLEMAN_MAX_SPEED = 15f;
    public static final float RIFLEMAN_ACCELERATION = 0.3f;
    public static final float RIFLEMAN_MAX_MOVE_RANGE = 20f;
    public static final float RIFLEMAN_MIN_MOVE_RANGE = 5f;


}
