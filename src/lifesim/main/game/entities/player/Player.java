package lifesim.main.game.entities.player;

import lifesim.main.game.Game;
import lifesim.main.game.Game.*;
import lifesim.main.game.GamePanel;
import lifesim.main.game.controls.KeyInputListener;
import lifesim.main.game.controls.KeyInputManager;
import lifesim.main.game.entities.HealthEntity;
import lifesim.main.game.setting.World;
import lifesim.main.util.drawing.Sprite;
import lifesim.main.util.math.Vector2D;

import java.awt.*;


public final class Player extends HealthEntity {

    private World world;

    private double energy;
    private double strength;
    private double money;
    private double intellect;


    public Player() {
        super("Player", new Sprite("Eh Walk Right 1"), new Vector2D(0, 0), 10, 1000, 0);
    }


    public Vector2D getDisplayPos() {
        //return new Vector2D(Game.getPanel().getScaledDimensions().scale(0.5/GamePanel.MAP_SCALE)).translate(sprite.size.scale(-0.5));
        return sprite.size.scale(-0.5);
    }


    public World getWorld() {
        return world;
    }

    public void setWorld(World newWorld) {
        world = newWorld;
        newWorld.add(this);
    }


    public void control() {
        boolean up, down, left, right;

        final KeyInputListener upkey = KeyInputManager.k_w;
        final KeyInputListener leftKey = KeyInputManager.k_a;
        final KeyInputListener downkey = KeyInputManager.k_s;
        final KeyInputListener rightKey = KeyInputManager.k_d;

        if (leftKey.isPressed() && rightKey.isPressed()) {
            left = leftKey.getReadTime() < rightKey.getReadTime();
            right = !left;
        } else {
            left = leftKey.isPressed();
            right = rightKey.isPressed();
        }
        if (upkey.isPressed() && downkey.isPressed()) {
            up = upkey.getReadTime() < downkey.getReadTime();
            down = !up;
        } else {
            up = upkey.isPressed();
            down = downkey.isPressed();
        }

        if (up || down || left || right) {
            movement.setMagnDir(calculateSpeed(), getIntendedAngle(up, down, left, right));
        } else {
            movement.set(0, 0);
        }
    }


    private double calculateSpeed() {
        double speed = defaultSpeed;
        if (KeyInputManager.k_space.isPressed()) {
            speed *= 1.5;
            energy -= 0.025;
        }
        return speed;
    }


    private double getIntendedAngle(boolean up, boolean down, boolean left, boolean right) {

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
        } else if (right) angle = 180;
        else if (left) angle = 0;

        return angle;
    }



    public double getEnergy() {
        return energy;
    }

    public void energize(double amount) {
        energy += amount;
    }

    public void tire(double amount) {
        energy -= amount;
    }


    public double getStrength() {
        return strength;
    }

    public void strengthen(double amount) {
        strength += amount;
    }


    public double getMoney() {
        return money;
    }

    public void gainMoney(double amount) {
        money += amount;
    }

    public void loseMoney(double amount) {
        money -= amount;
    }

    public boolean hasEnoughMoney(double amount) {
        return (money >= amount);
    }


    public double getIntellect() {
        return intellect;
    }

    public void gainIntellect(double amount) {
        intellect += amount;
    }


    public void manageStats() {
        energy -= 0.1;
    }



    @Override
    public void update(World world) {
        super.update(world);

        manageStats();
        control();
    }


    @Override
    public void render(Graphics2D g2d) {
        super.render(g2d);
    }
}
