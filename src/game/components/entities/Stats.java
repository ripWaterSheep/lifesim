package game.components.entities;

import game.components.GameComponent;
import game.components.entities.player.Player;
import game.overlay.DeathScreen;
import game.overlay.GameMessage;
import util.MyMath;

import java.awt.*;

import static java.lang.Math.abs;
import static java.lang.Math.max;

public class Stats {

    private Entity entity;

    protected final double initialHealth;
    protected double health;



    public double getHealth() { return health; }

    public void takeDamage(double amount) {
        health -= amount;
        if (getHealth() > 0) Particle.spawnParticles(Color.RED, false);
    }

    public boolean isAlive() { return health > 0; }


    private final double damage;

    public double getDamage() { return damage; }


    private final boolean canDamagePlayer;

    public boolean canDamagePlayer() { return canDamagePlayer; }


    private final double killLoot; // Specify amount of money the player earns from entity's death.

    public double getKillLoot() { return killLoot; }

    private boolean looted = false;



    protected Stats(Entity entity, double health, double damage, boolean canDamagePlayer, double killLoot) {
        this.entity = entity;
        this.health = health;
        this.initialHealth = health;
        this.damage = damage;
        this.canDamagePlayer = canDamagePlayer;
        this.killLoot = killLoot;
    }



    protected void statLogic() {
        health = Math.max(health, 0);
    }


    protected void deathLogic() {
        if (entity instanceof Creature && health <= 0 && !looted) {
            Player.getInstance().getStats().gainMoney(killLoot);
            looted = true;
        }
    }


    void update() {
        statLogic();
        if (!isAlive()) deathLogic();
    }


}
