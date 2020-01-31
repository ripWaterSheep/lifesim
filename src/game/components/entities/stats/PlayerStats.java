package game.components.entities.stats;

import game.activity.controls.KeyboardControls;
import game.components.entities.player.Player;
import game.components.entities.StatParticle;
import game.overlay.DeathScreen;
import game.overlay.GameMessage;
import util.MyMath;

import java.awt.*;

import static java.lang.Math.max;

public class PlayerStats extends HealthStats {

    private static final Color energyColor = new Color(255, 200, 0);
    public static Color getEnergyColor() {
        return energyColor;
    }

    private static final Color strengthColor = new Color(255, 255, 0);
    public static Color getStrengthColor() {
        return strengthColor;
    }

    private static final Color moneyColor = new Color(35, 240, 35);
    public static Color getMoneyColor() {
        return moneyColor;
    }

    private static final Color intellectColor = new Color(0, 0, 255);
    public static Color getIntellectColor() {
        return intellectColor;
    }




    // Override belongsTo with subtype, because Player is the intended subtype to instantiate this class.
    protected Player belongsTo;

    // Accessor must be used because supertype is hidden. Any time the superclass references the
    @Override
    protected Player getBelongsTo() {
        return belongsTo;
    }


    // Speed under normal conditions (full energy, not sprinting)
    private double baseSpeed;

    /** Calculates the speed at which the player is currently intended to move at.
     * An int representing number of pixels player will move
     * in a direction per frame if appropriate key is pressed.
     */
    public void calculateSpeed() {
        speed = baseSpeed * (((energy/1000)/2)+0.55);
        energy -= 0.075;
        if (KeyboardControls.getSpacePressed() && energy > 0) {
            speed *= 1.5;
            energy -= 0.075;
        }
    }


    public void heal(double amount) {
        health += amount;
        if (health < getStrengthDependentStatCap())
            StatParticle.spawnParticles(belongsTo, true, healthColor, amount, true);
    }


    private double energy = 1000;

    public double getEnergy() {
        return energy;
    }

    public void energize(double amount) {
        energy += Math.abs(amount);
        if(energy < getStrengthDependentStatCap())
            StatParticle.spawnParticles(belongsTo, true, energyColor, amount, true);
    }

    public void tire(double amount) {
        energy -= Math.abs(amount);
        if(energy > 0)
            StatParticle.spawnParticles(belongsTo, true, energyColor, amount, false);
    }



    private double money = 0;

    public double getMoney() {
        return money;
    }

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
        StatParticle.spawnParticles(belongsTo, false, moneyColor, amount, true);
    }

    public void loseMoney(double amount) {
        money -= Math.abs(amount);
        StatParticle.spawnParticles(belongsTo, false, moneyColor, amount, false);
    }


    private double strength = 0;

    public double getStrength() {
        return strength;
    }

    public void strengthen(double amount) {
        strength += Math.abs(amount);
        StatParticle.spawnParticles(belongsTo, true, strengthColor, amount, true);
    }


    private double intellect =  0;

    public double getIntellect() {
        return intellect;
    }

    public void gainIntellect(double amount) {
        intellect += Math.abs(amount);
        StatParticle.spawnParticles(belongsTo, false, intellectColor, amount, true);
    }


    /** Calculate the cap for the many stats whose caps increase when strength increases. */
    public double getStrengthDependentStatCap() {
        return 1000 + (strength/10);
    }



    public PlayerStats(Player player, double speed, double health) {
        super(player, speed, health, 0, false);
        this.belongsTo = player;
        baseSpeed = speed;
    }


    @Override
    protected void statLogic() {
        super.statLogic();
        if (energy <= 0) {
            new GameMessage("Get energy quickly!");
            takeDamage(2);
        }

        health = MyMath.clamp(health, 0, getStrengthDependentStatCap());
        energy = MyMath.clamp(energy, 0, getStrengthDependentStatCap());
        strength = max(strength, 0);
        money = max(money, 0);
        energy -= 0.075;
    }



    @Override
    protected void deathLogic() {
        baseSpeed = 0;
        System.out.println(speed);
        if (!DeathScreen.iStarted()) {
            new GameMessage("Oof!");
            DeathScreen.show();
        }
    }

}
