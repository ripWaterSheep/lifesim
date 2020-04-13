package lifesim.main.game.entities.components.stats;

import lifesim.main.game.Game;
import lifesim.main.game.entities.Entity;


import static java.lang.Math.*;


public class PlayerStats extends HealthStats {

    private final Game game;

    private double energy;
    private double strength;
    private double money;
    private double intellect;

    public PlayerStats(double speed, double health, double energy, double strength, double money, double intellect, Game game) {
        super(speed, 0, Alliance.PLAYER, health);
        this.energy = energy;
        this.strength = strength;
        this.money = money;
        this.intellect = intellect;
        this.game = game;
    }

    @Override
    public String getInfo() {
        return "";
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

    public boolean attemptToPay(double amount) {
        boolean can = money >= amount;
        if (can) loseMoney(amount);
        else game.displayCenter("Out of money!");
        return can;
    }


    public double getIntellect() {
        return intellect;
    }

    public void gainIntellect(double amount) {
        intellect += amount;
    }



    @Override
    public void update(Entity player) {
        super.update(player);
        energy = max(0, energy);
        strength = max(0, strength);
        intellect = max(0, intellect);

        speedMultiplier *= (energy/5000) + 0.8;

        energy -= 0.05 + sqrt(player.getVelocity().getMagnitude()/500);
    }

}
