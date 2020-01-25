package game.components.entities.player;

import game.components.entities.Entity;
import game.activity.controls.KeyboardControls;
import game.components.structures.Structure;
import main.WindowSize;

import java.awt.*;

import static game.activity.collision.CollisionLogic.playerCollisionLogic;
import static game.activity.controls.KeyboardControls.*;
import static util.FindComponent.*;
import static util.MyMath.*;


public class Player extends Entity {


    private static Player instance;

    /** Gets the player instance because there can only be one player character.
     * This is pretty much a singleton design pattern.
     *
     * @return first defined instance of player if one exists already.
     */
    public static Player getInstance() { return instance; }


    public void goToPos(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void goToStructure(String name) {
        Structure structure = findStructure(name);
        goToPos(structure.getX(), structure.getY());
        world = structure.getWorld();
    }


    public void goToEntity(String name) {
        Entity entity = findEntity(name);
        goToPos(entity.getX(), entity.getY());
        world = entity.getWorld();
    }


    public void goToWorld(String name) {

        this.world = findWorld(name);
        goToPos(0, 0);
    }

    public void goToWorldAt(String name, double x, double y) {
        this.world = findWorld(name);
        goToPos(x, y);
    }


    @Override
    public int getDisplayX() { return betterRound(WindowSize.getMidWidth()-getMidWidth()); }

    @Override
    public int getDisplayY() { return betterRound(WindowSize.getMidHeight()-getMidHeight());}


    private PlayerStats stats;

    @Override
    public PlayerStats getStats() { return stats; }



    public Player(String name, double x, double y, int radius, Color color, double speed) {
        super(name, x, y, radius, color, speed, 1000, 0, false);
        Player.instance = this;
        stats = new PlayerStats(speed, 1000);
    }



    /** Move in a direction according to which keys are pressed.
     * If two keys of opposing directions are pressed, move in the direction of the first key pressed.
     */
    protected void movementLogic() {
        boolean left = false;
        boolean right = false;
        boolean up = false;
        boolean down = false;

        if(getLeftPressed() && getRightPressed()) {
            if(getLeftReadTime() > getRightReadTime()) right = true;
            else left = true;
        }
        else if(getLeftPressed()) left = true;
        else if(getRightPressed()) right = true;

        if(getUpPressed() && getDownPressed()) {
            if(getUpReadTime() > getDownReadTime()) up = true;
            else down = true;
        }
        else if(getUpPressed()) up = true;
        else if(getDownPressed()) down = true;

        // Get angles for different key directions (makes going diagonal the same speed as horizontal or vertical)
        if (up) {
            if (left) angle = 45;
            else if (right) angle = 135;
            else angle = 90;
        } else if (down) {
            if (left) angle = 315;
            else if (right) angle = 225;
            else angle = 270;
        }
        else if (right) angle = 180;
        else if (left) angle = 0;

        if (left||right||up||down) {
            stats.calculateSpeed();
            moveTowardsAngle();
        }
    }



    @Override
    public void update() {
        super.update();
        playerCollisionLogic(this);
        Weapon.controlLogic();
    }


    @Override
    public void draw(Graphics g) {
        super.draw(g);
    }


}
