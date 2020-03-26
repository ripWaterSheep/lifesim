package lifesim.main.game.entities.components.stats;

import lifesim.main.game.controls.MouseInputManager;
import lifesim.main.game.entities.Entity;
import lifesim.main.game.entities.MovementEntity;
import lifesim.main.game.entities.Player;
import lifesim.main.game.entities.components.Alliance;

import java.awt.*;

import static java.lang.Math.*;

public class PlayerStats extends HealthStats {

    public static class Colors {
        public static final Color energyColor = new Color(255, 120, 0);
        public static final Color strengthColor = new Color(255, 255, 20);
        public static final Color moneyColor = new Color(10, 255, 0);
        public static final Color intellectColor = new Color(0, 0, 255);
    }


    private double energy;
    private double strength;
    private double money;
    private double intellect;

    public PlayerStats(double health, double energy, double strength, double money, double intellect) {
        super(0, health, Alliance.PLAYER);
        this.energy = energy;
        this.strength = strength;
        this.money = money;
        this.intellect = intellect;
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
    public void handleCollisions(Entity owner, Entity entity) {
        super.handleCollisions(owner, entity);

        entity.whileTouching((Player) owner, this);
        
        if (MouseInputManager.left.isClicked())
            entity.onClick((Player) owner, this);
    }


    @Override
    public void run(Entity owner) {
        super.run(owner);
        energy = max(0, energy);
        strength = max(0, strength);
        intellect = max(0, intellect);

        double tireAmount = 0.1 + sqrt(((MovementEntity) owner).movement.getMagnitude()/400);
        energy -= tireAmount;
    }



}
