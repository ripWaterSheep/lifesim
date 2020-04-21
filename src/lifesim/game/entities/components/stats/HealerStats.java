package lifesim.game.entities.components.stats;

import lifesim.game.entities.Entity;

public class HealerStats extends BasicStats {

    private final double healAmount;

    public HealerStats(double damage, Alliance alliance, double healAmount) {
        super(damage, alliance);
        this.healAmount = healAmount;
    }


    @Override
    public void onCollision(Entity entity, Entity otherEntity) {
        super.onCollision(entity.getOwner(), otherEntity.getOwner());

        if (!alliance.opposes(otherEntity.getStats().getAlliance()) && !entity.getOwner().equals(otherEntity)) {
            otherEntity.getStats().heal(healAmount);
        }
    }
}
