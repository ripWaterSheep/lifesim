package lifesim.main.game.entities.components.stats;

import lifesim.main.game.controls.KeyInputManager;
import lifesim.main.game.controls.MouseInputManager;
import lifesim.main.game.entities.Entity;
import lifesim.main.game.entities.Player;

import static java.lang.Math.max;
import static java.lang.Math.sqrt;


public class PlayerStats extends HealthStats {

    private double energy;
    private double strength;
    private double money;
    private double intellect;

    public PlayerStats(double speed, double health, double energy, double strength, double money, double intellect) {
        super(speed, 0, Alliance.PLAYER, health);
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
    public void onCollision(Entity player, Entity otherEntity) {
        super.onCollision(player, otherEntity);

        otherEntity.eventWhileTouching((Player) player, this);
        
        if (MouseInputManager.left.isClicked())
            otherEntity.eventOnClick((Player) player, this);
    }


    @Override
    public void update(Entity player) {
        super.update(player);
        energy = max(0, energy);
        strength = max(0, strength);
        intellect = max(0, intellect);

        speedMultiplier *= (energy/5000) + 0.8;
        if (KeyInputManager.k_space.isPressed() && energy > 0)
            speedMultiplier *= 1.45;

        energy -= 0.1 + sqrt(player.getVelocity().getMagnitude()/450);
    }



}
