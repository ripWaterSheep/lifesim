package lifesim.main.game.entities.components.stats;

import lifesim.main.game.entities.Entity;
import lifesim.main.game.entities.components.Drops;
import lifesim.main.game.entities.types.Spawnable;
import lifesim.main.game.handlers.World;

import static java.lang.Math.max;
import static java.lang.Math.min;
import static lifesim.main.util.math.MyMath.betterRound;

public class HealthStats extends BasicStats implements Stats {

    boolean alive = true;
    protected double health;
    protected final double maxHealth;


    Drops drops = new Drops();


    public HealthStats(double speed, double damage, Alliance alliance, double health) {
        super(speed, damage, alliance);
        this.health = health;
        maxHealth = health;
    }


    @Override
    public boolean isAlive() {
        return health > 0;
    }


    @Override
    public boolean hasHealth() {
        return true;
    }


    @Override
    public double getHealth() {
        return health;
    }

    @Override
    public void heal(double amount) {
        health += amount;
    }

    @Override
    public void takeDamage(double damage) {
        health -= damage;
    }

    @Override
    public String getInfo() {
        return betterRound(health) + "";
    }

    @Override
    public void update(Entity entity, World world) {
        super.update(entity, world);

        if (health <= 0 && alive) {
            alive = false;
            drops.dropAt(entity.getPos(), world);
            entity.removeFromWorld();
        }

        health = max(health, 0);

        if (!(this instanceof PlayerStats)) {
            health = min(maxHealth, health);
        }
    }

}
