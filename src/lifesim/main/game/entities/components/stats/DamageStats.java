package lifesim.main.game.entities.components.stats;

import lifesim.main.game.entities.Entity;
import lifesim.main.game.entities.TempEntity;
import lifesim.main.game.entities.components.Alliance;
import lifesim.main.game.entities.components.sprites.AnimatedSprite;
import lifesim.main.game.entities.components.sprites.Animation;
import lifesim.main.game.handlers.World;

public class DamageStats extends BasicStats {

    private final double damage;
    private final boolean dieOnDamage;

    // Dictates which entities may attack it.
    public final Alliance alliance;


    public DamageStats(double damage, Alliance alliance, boolean dieOnDamage) {
        this.damage = damage;
        this.alliance = alliance;
        this.dieOnDamage = dieOnDamage;
    }

    public DamageStats(double damage, Alliance alliance) {
        this(damage, alliance, false);
    }


    @Override
    public void onDeath(Entity owner, World world) {
        world.add(new TempEntity("Death Animation", new AnimatedSprite(new Animation(40,
                "boom_1", "boom_2", "boom_3", "boom_4", "boom_5", "boom_6", "boom_7", "boom_8")),
                false), owner.pos);
    }

    @Override
    public void handleCollisions(Entity owner, Entity entity) {
        if (entity.stats instanceof HealthStats) {
            HealthStats opponentStats = (HealthStats) entity.stats;

            // Don't attack if both entities are on the same alliance, unless that alliance is neutral.
            if (alliance.equals(opponentStats.alliance) && !alliance.equals(Alliance.NEUTRAL))
                return;

            ((HealthStats) entity.stats).loseHealth(damage);
            if (dieOnDamage) {
                owner.die();
            }
        }
    }

    @Override
    public void run(Entity owner) {

    }


}
