package game.components.entities;

import game.components.entities.player.Player;

import java.awt.*;

public class AdvancedStats extends BasicStats{


    private final double killLoot; // Specify amount of money the player earns from entity's death.

    public double getKillLoot() { return killLoot; }

    private boolean looted = false;


    protected AdvancedStats(Entity entity, double health, double damage, boolean canDamagePlayer, double killLoot) {
        super(entity, health, damage, canDamagePlayer);
        this.killLoot = killLoot;
    }



    protected void deathLogic() {
        if (entity instanceof Creature && health <= 0 && !looted) {
            Player.getInstance().getStats().gainMoney(killLoot);
            looted = true;
        }
    }

}
