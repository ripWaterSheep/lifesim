package game.organization.components.entities.stats;

import game.organization.components.entities.Creature;
import game.organization.components.entities.Player;
import game.organization.components.entities.StatParticle;

import java.awt.*;

public class CreatureStats extends HealthStats{


    @Override
    public void takeDamage(double amount) {
        health -= amount;
        if (getHealth() > 0) StatParticle.spawnParticles(belongsTo, false, Color.RED, amount, false);
    }


    private final double killLoot; // Specify amount of money the player earns from entity's death.

    public double getKillLoot() {
        return killLoot;
    }

    private boolean looted = false;


    public CreatureStats(Creature belongsTo, double speed, double health, double damage, boolean canDamagePlayer, double killLoot) {
        super(belongsTo, speed, health, damage, canDamagePlayer);
        this.killLoot = killLoot;
    }



    @Override
    protected void deathLogic() {
        if (belongsTo instanceof Creature && health <= 0 && !looted) {
            Player.getInstance().getStats().gainMoney(killLoot);
            looted = true;
        }
    }

}
