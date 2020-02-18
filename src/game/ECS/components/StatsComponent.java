package game.ECS.components;

import java.awt.*;

public class StatsComponent implements IComponent {

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


    @Override
    public StatsComponent copy() {
        return new StatsComponent();
    }


    public static class Colors {
        public static final Color energyColor = new Color(255, 159, 0);
        public static final Color strengthColor = new Color(0, 0, 0);
        public static final Color moneyColor = new Color(10, 255, 0);
        public static final Color intellectColor = new Color(0, 0, 255);
    }


}
