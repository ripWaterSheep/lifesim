package lifesim.main.game.entities.components.stats;

import lifesim.main.game.controls.MouseInputManager;
import lifesim.main.game.entities.Entity;
import lifesim.main.game.entities.Player;

public class PlayerStats extends HealthStats {

    private double energy;
    private double strength;
    private double money;
    private double intellect;

    public PlayerStats(double health, double energy, double strength, double money, double intellect) {
        super(health);
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

    public boolean hasEnoughMoney(double amount) {
        return (money >= amount);
    }


    public double getIntellect() {
        return intellect;
    }

    public void gainIntellect(double amount) {
        intellect += amount;
    }



    @Override
    public void collision(Entity owner, Entity entity) {
        super.collision(owner, entity);

        entity.onTouch((Player) owner, this);
        
        if (MouseInputManager.left.isClicked())
            entity.onClick((Player) owner, this);
    }


    @Override
    public void run(Entity owner) {
        super.run(owner);

        energy -= 0.1;
    }

}
