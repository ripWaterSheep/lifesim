package lifesim.main.game.entities.components.stats;

import lifesim.main.game.entities.Entity;
import lifesim.main.game.entities.TempEntity;
import lifesim.main.game.entities.components.Alliance;
import lifesim.main.game.entities.components.Vector2D;
import lifesim.main.game.entities.components.sprites.AnimatedSprite;
import lifesim.main.game.entities.components.sprites.Animation;
import lifesim.main.game.handlers.World;

public class DamageStats extends BasicStats {

    protected final double damage;
    private final boolean dieOnDamage;

    // Dictates which entities the owner entity may may damage and which entities may damage the owner.
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
    public void onRemoval(Entity owner, World world) {
        if (!owner.name.equals("Death Animation"))
        world.add(new TempEntity("Death Animation", new AnimatedSprite(new Animation("boom",
                40, new Vector2D(16, 16), 0))), owner.pos);
    }


    @Override
    public DamageStats copyInitialState() {
        return new DamageStats(damage, alliance, dieOnDamage);
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
                owner.removeFromWorld();
            }

        }
    }

    @Override
    public void run(Entity owner) {

    }


}
