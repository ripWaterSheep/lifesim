package game.components.entities.player;

import game.activity.controls.MouseControls;
import game.components.entities.Entity;
import game.organization.World;
import game.components.structures.Structure;
import main.WindowSize;
import game.components.entities.stats.PlayerStats;

import java.awt.*;

import static game.activity.controls.KeyboardControls.*;
import static game.components.entities.stats.CollisionChecker.getTopStructureTouching;
import static util.FindComponent.*;



public class Player extends Entity {


    private static Player instance;

    /** Gets the player instance because there can only be one player character.
     * This is pretty much a singleton design pattern.
     *
     * @return first defined instance of player if one exists already.
     */
    public static Player getInstance() {
        return instance;
    }


    public void goToWorld(World world) {
        this.world = world;
        goToPos(0, 0);
    }


    public void goToWorld(String name) {
        this.world = findWorld(name);
        goToPos(0, 0);
    }


    public void goToStructure(String name) {
        Structure structure = findStructure(name);
        this.world = structure.getWorld();
        goToPos(structure.getX(), structure.getY());
    }


    @Override
    public int getDisplayX() {
        return (int)(WindowSize.getMidWidth()-getMidWidth());
    }

    @Override
    public int getDisplayY() {
        return (int)(WindowSize.getMidHeight()-getMidHeight());
    }


    protected PlayerStats stats;

    @Override
    public PlayerStats getStats() {
        return stats;
    }


    private PlayerWeapon weapon;



    public Player(double x, double y) {
        super("Player", x, y, 50, 50, true, Color.YELLOW);
        Player.instance = this;
        stats = new PlayerStats(this, 12, 1000);
        weapon = new PlayerWeapon(this);
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

        double angle = 0;
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

        stats.setAngle(angle);

        if (left||right||up||down) {
            getStats().calculateSpeed();
            moveTowardsAngle();
        }
    }


    private void collisionLogic() {
        Structure structure = getTopStructureTouching(this); // Use the top structure to implement concept of layered surfaces.

        structure.onTouch();
        if (MouseControls.getLeftClicked()) {
            structure.onClick();
        }
    }



    @Override
    public void update() {
        super.update();

        collisionLogic();
        weapon.run();
    }


}
