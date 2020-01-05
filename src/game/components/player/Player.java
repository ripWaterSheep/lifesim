package game.components.player;

import game.components.structures.Structure;
import game.components.World;
import game.GameSession;
import game.components.GameComponent;
import game.overlay.GameMessage;
import util.MyMath;
import main.WindowSize;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

import static game.components.player.Controls.*;
import static java.lang.Math.*;
import static util.ShapeUtil.testIntersection;


public class Player extends GameComponent {


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


    public static ArrayList<Player> getInstances() {

		ArrayList<Player> instanceList = new ArrayList<>();
		instanceList.add(getInstance());
		return instanceList;
	}
   

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



    @Override
    public int getDisplayX() {
        return WindowSize.getMidWidth()-getMidWidth();
    }

    @Override
    public int getDisplayY() { return WindowSize.getMidHeight()-getMidHeight();}


    // Override superclass getShape() return type, because subclasses can override method return type if new return type is a subclass of overridden type.
    // Pretty cool.
    @Override
    public Ellipse2D getShape() { return new Ellipse2D.Double(getDisplayX(), getDisplayY(), width, height); }


    public ArrayList<Structure> getTouching() {
        ArrayList<Structure> touching = new ArrayList<>();
        for (Structure structure: Structure.getInstances()) {
            if (testIntersection(getShape(), structure.getShape()) && world == structure.getWorld()) {
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

    public void heal(double amount) { health += amount; }

    public void damage(double amount) { health -= amount; }


    private double energy = 1000;

    public double getEnergy() { return energy; }

    public void energize(double amount) { energy += Math.abs(amount); }

    public void tire(double amount) { energy -= Math.abs(amount); }


    private double money = 0;

    public double getMoney() { return money; }

    public boolean hasMoney() {
        boolean has = money > 0;
        if (!has)
            GameMessage.NeedMoneyMessage();
        return has;
    }

    public boolean canAfford(double moneyAmount) {
        boolean can = money > moneyAmount;
        if (!can)
            GameMessage.NeedMoneyMessage();
        return money > moneyAmount;
    }

    public void gainMoney(double amount) { money += Math.abs(amount); }

    public void loseMoney(double amount) { money -= Math.abs(amount); }


    private double strength = 0;

    public double getStrength() { return strength; }

    public void strengthen(double amount) { strength += Math.abs(amount); }


    private double intellect =  0;

    public double getIntellect() { return intellect; }

    public void gainIntellect(double amount) { intellect += Math.abs(amount); }


    /** Calculate the cap for the many stats whose caps increase when strength increases.
     */
    public double getStrengthDependentStatCap() { return strength+1000; }




    public Player(int x, int y, int radius, World world, Color color) {
        Player.instance = this;

        String label = "Player";
        this.x = x;
        this.y = y;
        this.width = radius*2;
        this.height = radius*2;
        this.world = world;
        this.color = color;
    }



    /** Gets the speed at which the player is currently intended to move at.
     *
     * @return An int representing number of pixels player will move
     * in a direction per frame if appropriate key is pressed.
     */
    public int getSpeed() {
        int speed = 10;
        if (Controls.getSprintToggled()) {
            speed *= 2;
        }
        return speed;
    }


    /** Move in a direction according to which keys are pressed.
     * If two keys of opposing directions are pressed, move in the direction of the first key pressed.
     *
     */
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

    /** Interact with
     */
    private void collisionLogic() {

        if (isTouchingAnything()) {
            Structure structure = getTopTouching(); // Use the top structure so you can stand on something above lava to stay safe and stuff

            GameSession.getUsedLayout().playerTouchLogic(structure);
            if (Controls.getInteracted()) {
                GameSession.getUsedLayout().playerInteractLogic(structure);
            }
        }
    }


    private void borderLogic() {
        x = MyMath.clamp(x, -world.getMidWidth() + getMidWidth(), world.getMidWidth() - getMidWidth());
        y = MyMath.clamp(y, -world.getMidHeight() + getMidHeight(), world.getMidHeight() - getMidHeight());
    }


    public void statLogic() {
        tire(0.1);

        health = MyMath.clamp(health, 0, getStrengthDependentStatCap());
        energy = MyMath.clamp(energy, 0, getStrengthDependentStatCap());
        money = max(money, 0);
        strength = max(strength, 0);
    }


    @Override
    public void setup(JPanel panel) {
        instance = this;
        Controls.initListeners(panel);
    }


    @Override
    public void act() {
        movementLogic();
        borderLogic();
        collisionLogic();
        statLogic();
        Controls.reset();
    }


    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(color);
        g2d.fill(getShape());
    }


}
