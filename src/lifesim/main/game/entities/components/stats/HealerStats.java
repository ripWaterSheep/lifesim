package lifesim.main.game.entities.components.stats;

import lifesim.main.game.entities.Entity;

public class HealerStats extends BasicStats {

    private final double healAmount;

    public HealerStats(double speed, double damage, Alliance alliance, double healAmount) {
        super(speed, damage, alliance);
        this.healAmount = healAmount;
    }


    @Override
    public void onCollision(Entity entity, Entity otherEntity) {
        super.onCollision(entity.getOwner(), otherEntity.getOwner());
        if (alliance.equals(otherEntity.getStats().getAlliance()) && !(entity.getOwner().equals(otherEntity) || otherEntity.getOwner().equals(entity)))
            otherEntity.getStats().heal(healAmount);
    }
}
