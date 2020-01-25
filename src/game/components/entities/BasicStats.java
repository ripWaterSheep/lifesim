package game.components.entities;

import java.awt.*;

import static java.lang.Math.abs;
import static java.lang.Math.max;

public class BasicStats {

    protected Entity entity;


    protected double speed;

    public double getSpeed() { return speed; }


    protected final double initialHealth;
    protected double health;

    public double getHealth() { return health; }

    public void takeDamage(double amount) {
        health -= amount;
        if (getHealth() > 0) StatParticle.spawnParticles(entity, Color.RED, amount, false);
    }

    public boolean isAlive() { return health > 0; }


    private double damage;

    public double getDamage() { return damage; }


    private final boolean canDamagePlayer;

    public boolean canDamagePlayer() { return canDamagePlayer; }



    protected BasicStats(Entity entity, double speed, double health, double damage, boolean canDamagePlayer) {
        this.entity = entity;
        this.speed = speed;
        this.health = health;
        this.initialHealth = health;
        this.damage = damage;
        this.canDamagePlayer = canDamagePlayer;
    }



    protected void statLogic() {
        health = Math.max(health, 0);
        if (health <= 0) {
            damage = 0;
            speed = 0;
            entity.hide();
        }
    }


    protected void deathLogic() {  }


    void update() {
        statLogic();
        if (!isAlive()) deathLogic();
    }


}
