package game.components.entities.player;

import game.Chapter;
import game.components.entities.Entity;
import game.components.entities.Projectile;
import game.components.structures.Structure;
import game.components.World;
import game.overlay.DeathScreen;
import game.overlay.GameMessage;
import util.MyMath;
import main.WindowSize;

import javax.swing.*;
import java.awt.*;

import static game.components.entities.player.Controls.*;
import static java.lang.Math.*;
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


    private Chapter currentChapter;

    public Chapter getCurrentChapter() { return currentChapter; }


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
    public double getDisplayX() {
        return WindowSize.getMidWidth()-getMidWidth();
    }

    @Override
    public double getDisplayY() { return WindowSize.getMidHeight()-getMidHeight();}


    public void grow(double amount) {
        width += amount;
        height += amount;
    }

    public void shrink(double amount) {
        width -= amount;
        height -= amount;
    }

    private double baseSpeed;


    private double energy = 1000;

    public double getEnergy() { return energy; }

    public void energize(double amount) { energy += Math.abs(amount); }

    public void tire(double amount) { energy -= Math.abs(amount); }


    private double money = 0;

    public double getMoney() { return money; }

    public boolean hasMoney() {
        boolean has = money > 0;
        if (!has)
            GameMessage.needMoneyMessage();
        return has;
    }

    public boolean canAfford(double moneyAmount) {
        boolean can = money > moneyAmount;
        if (!can)
            GameMessage.needMoneyMessage();
        return money > moneyAmount;
    }

    public void gainMoney(double amount) { money += Math.abs(amount); }

    public void loseMoney(double amount) { money -= Math.abs(amount); }


    private double strength = 1;

    public double getStrength() { return strength; }

    public void strengthen(double amount) { strength += Math.abs(amount); }


    private double intellect =  0;

    public double getIntellect() { return intellect; }

    public void gainIntellect(double amount) { intellect += Math.abs(amount); }


    /** Calculate the cap for the many stats whose caps increase when strength increases.
     */
    public double getStrengthDependentStatCap() { return 1000 + (strength/10); }




    public Player(String name, double x, double y, double radius, World world, Color color, double speed) {
        super(name, x, y, radius, world, color, speed,0, 1000);
        Player.instance = this;
        this.world = world;
        this.color = color;
        baseSpeed = speed;
    }



    /** Gets the speed at which the player is currently intended to move at.
     *
     * @return An int representing number of pixels player will move
     * in a direction per frame if appropriate key is pressed.
     */
    private double calculateSpeed() {
        double currentSpeed = baseSpeed * clamp(((energy/getStrengthDependentStatCap())/2)+0.5, 0.5, 1);
        if (Controls.getSprinting()) {
            currentSpeed *= 1.5;
            tire(0.05);
        }
        return currentSpeed;
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
        speed = calculateSpeed();

        if (left||right||up||down) moveTowardsAngle();
    }



    /** Interact with GameComponents
     */
    @Override
    protected void collisionLogic() {
        if (isTouchingAnyStructures()) {
            Structure component = getTopStructureTouching(); // Use the top structure only so you can stand on something above lava to stay safe and stuff

            component.onTouch();
            if (Controls.getInteracted()) {
                component.onClick();
            }
        }
    }


    @Override
    public void statLogic() {
        super.statLogic();

        if (energy <= 0) {
            new GameMessage("Get energy quickly!");
            damage(2);
        }

        if (!alive) {
            new GameMessage("Oof!");
            DeathScreen.show();
            health = 0;
            energy = 0;
        }

        tire(0.1);

        health = MyMath.clamp(health, 0, getStrengthDependentStatCap());
        energy = MyMath.clamp(energy, 0, getStrengthDependentStatCap());
        money = clamp(money, 0, currentChapter.getCashCap());
        strength = max(strength, 0);
        width = clamp(width, 6, Math.min(WindowSize.getWidth(), WindowSize.getHeight()));
        height = clamp(height, 6, Math.min(WindowSize.getWidth(), WindowSize.getHeight()));

    }


    private void projectileLogic() {
        if (Controls.getFired()) {
            int radius = 8 + betterRound(Math.ceil(strength/75));
            Color color = new Color(35, 31, 15);
            int damage = betterRound(Math.ceil(strength/65));
            System.out.println(damage);
            double angle = getAngle(Controls.getLastClickX(), Controls.getLastClickY(), WindowSize.getMidWidth(), WindowSize.getMidHeight());
            new Projectile("Projectile", x, y, radius, world, color, 30, angle, WindowSize.getHypotLength(), damage, 100, false);
        }
    }



    @Override
    public void setup(JPanel panel) {
        currentChapter = Chapter.getChapters().get(0);
        Controls.initListeners(panel);

    }


    @Override
    public void act() {
        super.act();
        if (alive) projectileLogic();
        Controls.reset();
    }


    @Override
    public void draw(Graphics g) {
        super.draw(g);
    }


}
