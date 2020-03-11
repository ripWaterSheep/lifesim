package lifesim.main.game.entities.player;

import lifesim.main.game.Game;
import lifesim.main.game.controls.MouseInputManager;
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
        super("Player", new Sprite("Eh Walk Right 1"), new Vector2D(0, 0), 1000, 0);
    }


    public Vector2D getDisplayPos() {
        System.out.println(MouseInputManager.left.getPos().x/Game.getPanel().renderScale);
        System.out.println(1/Game.getPanel().renderScale);
        return new Vector2D(Game.getPanel().getScaledDimensions()).getScaled(0.5).getTranslated(sprite.size.getScaled(-0.5));
    }


    public World getWorld() {
        return world;
    }

    public void setWorld(World newWorld) {
        world = newWorld;
        newWorld.add(this);
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
        manageStats();
        System.out.println(getDisplayPos().x);
    }


    @Override
    public void render(Graphics2D g2d) {
        super.render(g2d);
    }
}
