package lifesim.game.entities;

import lifesim.util.geom.Vector2D;
import lifesim.util.sprites.Sprite;
import lifesim.game.handlers.World;
import lifesim.game.entities.stats.Stats;
import lifesim.util.geom.Rect;

public class SolidEntity extends Entity {

    private final int baseDepth; // The actual "floor" of the object that cannot be passed.

    public SolidEntity(String name, Sprite sprite, Stats stats, int baseDepth) {
        super(name, sprite, stats);
        this.baseDepth = baseDepth;
    }

    @Override
    public void handleCollision(Entity entity, World world) {
        super.handleCollision(entity, world);
        if (entity.getVelocity().getMagnitude() == 0) return;
        Rect entityRect = entity.getHitBox();
        // Keep entity's hitbox outside of this entity's hitbox.
        entity.setPos(entityRect.getPosClampedOutside(getHitBox()));
    }
}
