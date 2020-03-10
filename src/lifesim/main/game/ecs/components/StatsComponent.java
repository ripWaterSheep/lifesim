package lifesim.main.game.ecs.components;

import java.awt.*;

public class StatsComponent implements CopyableComponent {

    private double energy;
    public double getEnergy() {
        return energy;
    }

    public void energize(double amount) {
        energy += amount;
    }
    public void tire(double amount) {
        energy -= amount;
    }


    private double strength;
    public double getStrength() {
        return strength;
    }

    public void strengthen(double amount) {
        strength += amount;
    }


    private double money;
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


    private double intellect;
    public double getIntellect() {
        return intellect;
    }

    public void gainIntellect(double amount) {
        intellect += amount;
    }


    public StatsComponent() {
        energy = 1000;
        strength = 0;
        money = 0;
        intellect = 0;
    }


    public StatsComponent(double energy, double strength, double money, double intellect) {
        this.energy = energy;
        this.strength = strength;
        this.money = money;
        this.intellect = intellect;
    }


    @Override
    public StatsComponent copyInitialState() {
        return new StatsComponent();
    }


    public static class Colors {
        public static final Color energyColor = new Color(255, 120, 0);
        public static final Color strengthColor = new Color(255, 255, 20);
        public static final Color moneyColor = new Color(10, 255, 0);
        public static final Color intellectColor = new Color(0, 0, 255);
    }


}
