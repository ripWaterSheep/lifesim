package lifesim.game.entities;

import lifesim.game.entities.components.sprites.Sprite;
import lifesim.game.handlers.World;
import lifesim.game.entities.components.stats.Stats;
import lifesim.util.math.geom.Rect;

public class SolidEntity extends Entity {

    public SolidEntity(String name, Sprite sprite, Stats stats) {
        super(name, sprite, stats);
    }

    public SolidEntity(String name, Sprite sprite) {
        super(name, sprite);
    }

    @Override
    public Rect getHitBox() {
        Rect hitBox = super.getHitBox();
        hitBox = new Rect(hitBox.getCenterPos(), hitBox.getDims().translate(1, 1));
        return hitBox;
    }

    @Override
    public void handleCollision(Entity entity, World world) {
        super.handleCollision(entity, world);
        // Keep entity's position outside of this hitBox;
        Rect entityRect = entity.getHitBox();
        entity.setPos(entityRect.getPosClampedOutside(getHitBox()));
    }
}
