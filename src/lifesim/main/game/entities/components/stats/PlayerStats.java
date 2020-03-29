package lifesim.main.game.entities.components.stats;

import lifesim.main.game.controls.KeyInputManager;
import lifesim.main.game.controls.MouseInputManager;
import lifesim.main.game.entities.Entity;
import lifesim.main.game.entities.Player;

import static java.lang.Math.max;
import static java.lang.Math.sqrt;

public class PlayerStats extends Stats {

    private double energy;
    private double strength;
    private double money;
    private double intellect;

    public PlayerStats(double speed, double health, double energy, double strength, double money, double intellect) {
        super(speed, health, false, 0, Alliance.PLAYER);
        this.energy = energy;
        this.strength = strength;
        this.money = money;
        this.intellect = intellect;
    }

    @Override
    public double getCurrentSpeed() {
        // Base speed on current energy level.
        speedMultiplier = (energy / 1100) + 0.61;
        if (KeyInputManager.k_space.isPressed() && energy > 0)
            speedMultiplier *= 1.45;

        return super.getCurrentSpeed();
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

    public boolean canAfford(double amount) {
        return (money >= amount);
    }


    public double getIntellect() {
        return intellect;
    }

    public void gainIntellect(double amount) {
        intellect += amount;
    }



    @Override
    public void onCollision(Entity owner, Entity otherEntity) {
        super.onCollision(owner, otherEntity);

        otherEntity.whileTouching((Player) owner, this);
        
        if (MouseInputManager.left.isClicked())
            otherEntity.onClick((Player) owner, this);
    }


    @Override
    public void update(Entity owner) {
        super.update(owner);
        energy = max(0, energy);
        strength = max(0, strength);
        intellect = max(0, intellect);

        double tireAmount = 0.1 + sqrt(owner.movement.getMagnitude()/400);
        energy -= tireAmount;
    }



}
