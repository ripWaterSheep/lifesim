package game.components.entities.player;

import game.components.entities.Entity;
import game.components.structures.Structure;
import game.components.World;
import game.GameSession;
import game.components.GameComponent;
import game.components.entities.MobileEntity;
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


    public boolean getInteracted() { return Controls.getInteracted(); }



    public Player(String name, double x, double y, double radius, World world, Color color) {
        super(name, x, y, radius*2, radius*2, world, color, 10, 0);
        Player.instance = this;

        speed = 9;
        health = 1000;
        this.world = world;
        this.color = color;
    }



    /** Gets the speed at which the player is currently intended to move at.
     *
     * @return An int representing number of pixels player will move
     * in a direction per frame if appropriate key is pressed.
     */
    private double applyCurrentSpeed() {
        double currentSpeed = speed * clamp(((energy/getStrengthDependentStatCap())/2)+0.5, 0.5, 1);
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
        double moveSpeed = applyCurrentSpeed();

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
            if (left) currentAngle = 45;
            else if (right) currentAngle = 135;
            else currentAngle = 90;
        } else if (down) {
            if (left) currentAngle = 315;
            else if (right) currentAngle = 225;
            else currentAngle = 270;
        }
        else if (right) currentAngle = 180;
        else if (left) currentAngle = 0;
        else moveSpeed = 0;

        moveTowardsAngle(currentAngle, moveSpeed);
    }



    /** Interact with GameComponents
     */
    @Override
    protected void collisionLogic() {
        if (isTouchingAnyStructures()) {
            GameComponent component = getTopStructureTouching(); // Use the top structure only so you can stand on something above lava to stay safe and stuff
            GameSession.getUsedLayout().playerCollisionLogic(component);
            if (Controls.getInteracted()) {
                GameSession.getUsedLayout().playerInteractLogic(component);
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
        money = max(money, 0);
        strength = max(strength, 0);
        width = clamp(width, 6, Math.min(WindowSize.getWidth(), WindowSize.getHeight()));
        height = clamp(height, 6, Math.min(WindowSize.getWidth(), WindowSize.getHeight()));

    }


    private void projectileLogic() {
        if (Controls.getFired()) {
            int radius = 25;
            Color color = new Color(35, 31, 15);
            int damage = betterRound(Math.ceil(strength/1000)+1);
            double angle = getAngle(Controls.getLastClickX(), Controls.getLastClickY(), WindowSize.getMidWidth(), WindowSize.getMidHeight());
            MobileEntity projectile = new MobileEntity("Projectile", x, y, radius, radius, world, color,
                    MobileEntity.MovementType.LINEAR, 25, 800, angle, damage, 1, false, false);
        }
    }



    @Override
    public void setup(JPanel panel) {
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
