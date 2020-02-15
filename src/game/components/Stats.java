package game.components;

public class Stats implements IComponent {

    private double energy;
    public double getEnergy() {
        return energy;
    }

    public void gainEnergy(double amount) {
        energy += amount;
    }
    public void loseEnergy(double amount) {
        energy -= amount;
    }


    private double strength;
    public double getStrength() {
        return strength;
    }

    public void gainStrength(double amount) {
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


    private double intellect;
    public double getIntellect() {
        return intellect;
    }

    public void gainIntellect(double amount) {
        intellect += amount;
    }


    public Stats() {
        energy = 1000;
        strength = 0;
        money = 0;
        intellect = 0;
    }

}
