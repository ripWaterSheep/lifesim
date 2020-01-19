package game.components.entities.player;

import game.components.entities.Particle;
import game.overlay.DeathScreen;
import game.overlay.GameMessage;
import util.MyMath;

import java.awt.*;

import static java.lang.Math.max;

public class Stats {

    private Player player;

    void init() { player = Player.getInstance(); }


    // Since player's health is inherited because it's an entity, use the player's health variable instead of creating a new one.
    public double getHealth() { return player.getHealth(); }

    public void heal(double amount) {
        player.heal(amount);
        if (player.getHealth() < getStrengthDependentStatCap()) Particle.spawnParticles(Color.RED, true);
    }


    public void dealDamage(double amount) {
        player.dealDamage(amount);
        if (player.getHealth() > 0) Particle.spawnParticles(Color.RED, false);
    }


    double energy = 1000;

    public double getEnergy() { return energy; }

    public void energize(double amount) {
        energy += Math.abs(amount);
        if(energy < getStrengthDependentStatCap()) Particle.spawnParticles(Color.ORANGE, true);
    }

    public void tire(double amount) {
        energy -= Math.abs(amount);
        if(energy > 0) Particle.spawnParticles(Color.ORANGE, false);
    }


    double money = 0;

    public double getMoney() { return money; }

    public boolean hasMoney() {
        boolean has = money > 0;
        if (!has)
            GameMessage.needMoneyMessage();
        return has;
    }

    public boolean canAfford(double moneyAmount) {
        boolean can = money > moneyAmount;
        if (!can)
            GameMessage.needMoneyMessage();
        return money > moneyAmount;
    }

    public void gainMoney(double amount) {
        money += Math.abs(amount);
        Particle.spawnParticles(Color.GREEN, true);
    }

    public void loseMoney(double amount) {
        money -= Math.abs(amount);
        Particle.spawnParticles(Color.GREEN, false);
    }


    double strength = 1;

    public double getStrength() { return strength; }

    public void strengthen(double amount) {
        strength += Math.abs(amount);
        Particle.spawnParticles(Color.YELLOW, true);
    }


    double intellect =  0;

    public double getIntellect() { return intellect; }

    public void gainIntellect(double amount) {
        intellect += Math.abs(amount);
        Particle.spawnParticles(Color.BLUE, true);
    }


    /** Calculate the cap for the many stats whose caps increase when strength increases.
     */
    public double getStrengthDependentStatCap() { return 1000 + (strength/10); }



    public double growthThisFrame = 0;


    public void grow(double amount) {
        growthThisFrame += amount;
    }

    public void shrink(double amount) {
        growthThisFrame -= amount;
    }



    void statLogic() {

        if (!player.isAlive()) {
            new GameMessage("Oof!");
            DeathScreen.show();
        }

        if (energy <= 0) {
            new GameMessage("Get energy quickly!");
            dealDamage(2);
        }

        energy = MyMath.clamp(energy, 0, getStrengthDependentStatCap());
        strength = max(strength, 0);
        money = max(money, 0);
        energy -= 0.1;

        growthThisFrame = 0;

    }


}
