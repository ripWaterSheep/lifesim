package game.components.entities.stats;

import game.components.entities.*;
import game.components.entities.player.Player;

import java.awt.*;

import static game.components.entities.stats.CollisionChecker.*;

public class CreatureStats extends HealthStats {


    // Override belongsTo with subtype, because Creature is the intended subtype to instantiate this class.
    protected Creature belongsTo;

    // Accessor must be used because supertype is hidden. Any time the superclass references the
    @Override
    protected Creature getBelongsTo() {
        return belongsTo;
    }


    @Override
    public void takeDamage(double amount) {
        health -= amount;
        if (getHealth() > 0) StatParticle.spawnParticles(belongsTo, true, Color.RED, amount, false);
    }


    private final double killLoot; // Specify amount of money the player earns from entity's death.

    public double getKillLoot() {
        return killLoot;
    }

    private boolean looted = false;


    public CreatureStats(Creature belongsTo, double speed, double health, double damage, boolean canDamagePlayer, double killLoot) {
        super(belongsTo, speed, health, damage, canDamagePlayer);
        this.belongsTo = belongsTo;
        this.killLoot = killLoot;
    }


    @Override
    protected void deathLogic() {
        super.deathLogic();
        if (health <= 0 && !looted) {
            Player.getInstance().getStats().gainMoney(killLoot);
            looted = true;
        }
    }

}
