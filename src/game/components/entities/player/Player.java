package game.components.entities.player;

import game.activity.collision.CollisionLogic;
import game.components.entities.Entity;
import game.components.entities.Projectile;
import game.activity.controls.KeyboardControls;
import game.activity.controls.MouseControls;
import game.components.structures.Structure;
import game.components.World;
import game.overlay.DeathScreen;
import game.overlay.GameMessage;
import util.Geometry;
import util.MyMath;
import main.WindowSize;

import javax.swing.*;
import java.awt.*;

import static game.activity.controls.KeyboardControls.*;
import static util.MyMath.*;


public class Player extends Entity {


    private static Player instance;

    /** Gets the player instance because there can only be one player character.
     * This is pretty much a singleton design pattern.
     *
     * @return first defined instance of player if one exists already.
     */
    public static Player getInstance() { return instance; }


    /** Because game session polymorphizes the other objects with an arraylist of instance arraylists
     * this function was needed in order to return an arraylist to match.
     */


    public void goTo(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void goTo(Structure structure) {
        goTo(structure.getX(), structure.getY());
        world = structure.getWorld();
    }


    public void goTo(World world) {
        goTo(0, 0);
        this.world = world;
    }

    public void goTo(double x, double y, World world) {
        goTo(x, y);
        this.world = world;
    }


    @Override
    public int getDisplayX() { return betterRound(WindowSize.getMidWidth()-getMidWidth()); }

    @Override
    public int getDisplayY() { return betterRound(WindowSize.getMidHeight()-getMidHeight());}


    private double baseSpeed;

    private Stats stats = new Stats();

    public Stats getStats() { return stats; }


    public Player(String name, double x, double y, int radius, World world, Color color, double speed) {
        super(name, x, y, radius, world, color, speed, 1000, 0, false);
        Player.instance = this;
        this.world = world;
        this.color = color;
        baseSpeed = speed;
    }



    /** Calculates the speed at which the player is currently intended to move at.
     * An int representing number of pixels player will move
     * in a direction per frame if appropriate key is pressed.
     */
    private void calculateSpeed() {
        speed = baseSpeed * (((stats.energy/1000)/2)+0.5);
        //System.out.println(speed);
        if (KeyboardControls.getSprinting()) {
            speed *= 1.5;
            stats.energy -= 0.75;
        }
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
            calculateSpeed();
            moveTowardsAngle();
        }
    }



    /** Interact with GameComponents
     */
    @Override
    protected void collisionLogic() {
        CollisionLogic.playerCollisionLogic(this);
    }


    @Override
    public void statLogic() {
        super.statLogic();

        width += stats.growthThisFrame;
        height += stats.growthThisFrame;
        stats.statLogic();
        health = MyMath.clamp(health, 0.0, stats.getStrengthDependentStatCap());
        width = clamp(width, 6, Math.min(WindowSize.getWidth(), WindowSize.getHeight()));
        height = clamp(height, 6, Math.min(WindowSize.getWidth(), WindowSize.getHeight()));

    }


    private void projectileLogic() {
        if (MouseControls.getFired()) {
            int radius = betterRound(7 + (stats.strength/250));
            Color color = new Color(35, 31, 15);
            double damage = Math.sqrt((stats.strength/20)+1);
            System.out.println(damage);
            double angle = Geometry.getAngle(MouseControls.getLastClickX(), MouseControls.getLastClickY(), WindowSize.getMidWidth(), WindowSize.getMidHeight());
            new Projectile("Projectile", x, y, radius, world, color, 45, angle, WindowSize.getHypotLength(), damage, 100, false);
        }
    }



    @Override
    public void init(JPanel panel) {
        stats.init();
    }


    @Override
    public void update() {
        super.update();
        if (alive) projectileLogic();
    }


    @Override
    public void draw(Graphics g) {
        super.draw(g);
    }


}
