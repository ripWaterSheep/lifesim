package lifesim.main.game.entities;

import lifesim.main.game.entities.components.sprites.Sprite;
import lifesim.main.game.entities.components.stats.Alliance;
import lifesim.main.game.entities.components.stats.Stats;
import lifesim.main.game.entities.types.Launchable;
import lifesim.main.game.handlers.World;



public class RangedAIEntity extends AIEntity {

    public final Launchable projectileType;

    private final long shootInterval;
    private long lastShootTime = System.currentTimeMillis();


    public RangedAIEntity(String name, Sprite sprite, Stats stats, Alliance AITargetAlliance, double detectionRange, long shootInterval, Launchable projectileType) {
        super(name, sprite, stats, AITargetAlliance, detectionRange);
        this.shootInterval = shootInterval;
        this.projectileType = projectileType;
    }


    public Projectile getProjectile() {
        return projectileType.launchEntity(this, stats.getAlliance(), targetEntity.pos.getAngleFrom(pos));
    }


    private void attemptShot(World world) {
        if (System.currentTimeMillis() - lastShootTime > shootInterval) {
            world.add(getProjectile(), pos);
            lastShootTime = System.currentTimeMillis();
        }
    }


    @Override
    protected void evaluateAIState() {
        super.evaluateAIState();
        double distance = targetEntity.pos.getDistanceFrom(pos);
        if (distance < getProjectile().getRange() * 0.75) // Pursue target entity unless safely in range
            pursuing = false;;

        // Only launch projectile if target is inside attack range.
        attacking = distance <= getProjectile().getRange();
    }


    @Override
    protected void doAI(World world) {
        super.doAI(world);
        if (attacking) attemptShot(world);
    }

    @Override
    public void update(World world) {
        super.update(world);

    }
}
