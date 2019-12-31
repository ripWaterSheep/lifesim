package GameComponents;

import Controls.KeyboardControls;
import Util.Callback;
import Util.WindowSize;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

import static Controls.KeyboardControls.*;
import static Util.MyMath.clamp;
import static java.lang.Math.*;


public class Player extends GameComponent {

    private static Player instance = DefaultInstances.player;

    /** Gets the player instance because there can only be one player character.
     * This is pretty much a singleton design pattern.
     *
     * @return first defined instance of player if one exists already.
     */
    public static Player getInstance() { return instance; }


    public void goTo(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void goTo(Structure structure) {
        goTo(structure.getX(), structure.getY());
        this.world = structure.getWorld();
    }


    private World world;

    public void goTo(World world) {
        goTo(0, 0);
        this.world = world;
    }

    public void goTo(int x, int y, World world) {
        goTo(x, y);
        this.world = world;
    }

    public World getWorld() { return world; }


    public String getWorldLabel() { return world.getLabel(); }


    public boolean isInSameWorld(Structure structure) {
        boolean sameWorld = false;
        if (structure.getWorld() == this.world)
            sameWorld = true;

        return sameWorld;
    }


    public int getDisplayX() {
        return WindowSize.getMidWidth()-getRadius();
    }

    public int getDisplayY() {
        return WindowSize.getMidHeight()-getRadius();
    }


    private int radius;

    public int getRadius() {
        return radius;
    }

    public int getDiameter() {
        return radius*2;
    }


    public Ellipse2D.Double getShape() { return new Ellipse2D.Double(getDisplayX(), getDisplayY(), getDiameter(), getDiameter()); }


    public ArrayList<Structure> getTouching() {
        ArrayList<Structure> touching = new ArrayList<>();

        for (Structure structure : Structure.getInstances()) {
            if (getShape().intersects(structure.getShape()) && isInSameWorld(structure)) {
                touching.add(structure);
            }
        }

        return touching;
    }


    public boolean isTouchingAnything() { return !getTouching().isEmpty(); }

    public Structure getTopTouching() {
        Structure topTouching = null;
        if (isTouchingAnything())
            topTouching = getTouching().get(0);

        return topTouching;
    }


    private double health = 1000;

    public double getHealth() { return health; }

    public void heal(float amount) {
        health += amount;
        //health = clamp(health, 0, 1000);
    }


    private double energy = 1000;

    public double getEnergy() { return energy; }

    public void energize(float amount) {
        energy += amount;
        //energy = clamp(energy, 0, 1000);
    }


    private double money = 1000;

    public double getMoney() { return money; }

    public void pay(float amount) {
        money += amount;
        money = max(0, money);
    }



    public Player(int x, int y, int radius, World world, Color color) {
        Player.instance = this;

        String label = "Player";
        this.x = x;
        this.y = y;
        this.world = world;
        this.radius = radius;
        this.color = color;
    }


    /** Gets the speed at which the player is currently intended to move at.
     *
     * @return An int representing number of pixels player will move
     * in a direction per frame if appropriate key is pressed.
     */
    public int getSpeed() {
        int speed = 10;
        if (KeyboardControls.getSprintToggled()) {
            speed *= 2;
        }
        return speed;
    }


    private void movementLogic() {

        int speed = getSpeed();

        if(getLeftPressed() && getRightPressed()) {
            if(getLeftReadTime() > getRightReadTime()) x -= speed;
            else x += speed;
        }
        
        else if(getLeftPressed()) x -= speed;
        else if(getRightPressed()) x += speed;

        if(getUpPressed() && getDownPressed()) {
            if(getUpReadTime() > getDownReadTime()) y -= speed;
            else y += speed;
        }

        else if(getUpPressed()) y -= speed;
        else if(getDownPressed()) y += speed;
    }


    private void borderLogic() {
        x = clamp(x, -world.getMidWidth() + getRadius(), world.getMidWidth() - getRadius());
        y = clamp(y, -world.getMidHeight() + getRadius(), world.getMidHeight() - getRadius());
    }


    public void commandLogic() {
        if (isTouchingAnything()) {
            for (Structure structure : getTouching()) {

                if (getInteractKeyTyped() && !structure.getCommandsOnTap().isEmpty()) {
                    for (Callback command : structure.getCommandsOnTap()) {
                        if (command != null)
                            command.call();
                    }
                } else if (!structure.getCommandsOnTouch().isEmpty()) {
                    for (Callback command : structure.getCommandsOnTouch()) {
                        if (command != null)
                            command.call();
                    }
                }
                if (structure.getRandomPosOnTouch()) {
                    structure.randomizePos();
                }
            }
        }
    }


    public void statLogic() {
        energy -= 0.1;
    }


    @Override
    public void setup(JPanel panel) {
        world = World.instances.get(0);
    }


    @Override
    public void act() {
        movementLogic();
        borderLogic();
        commandLogic();
        statLogic();
    }


    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(color);
        g2d.fill(getShape());
    }


}
