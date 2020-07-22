package lifesim.game.entities;

import lifesim.game.entities.stats.Stats;
import lifesim.util.geom.Rect;
import lifesim.util.geom.Vector2D;
import lifesim.util.sprites.Sprite;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class Entity3D extends Entity {

    public Entity3D(String name, Sprite sprite, Stats stats) {
        super(name, sprite, stats);
    }

    @Override
    public boolean shouldBeSorted() {
        return true;
    }

    public void keepHitboxInRect(Rect rect) {
        // Scoot the rect down so only the entity's feet (bottom bound) must stay inside.
        // This way, the entity's body can overlap the outside to give the illusion of 3D.
        Vector2D rectCenter = rect.getCenterPos().translate(0, -getHitbox().height/2);
        Vector2D rectDims = rect.getDims().translate(-getHitbox().width, 0);
        rectDims.translate(0, -1);

        pos.clampInRect(new Rect(rectCenter, rectDims));
    }

    /** Entities are sorted by y position by the world.
     * Only 3d entities may be sorted so the order of flat objects remains constant.
     */
    @Override
    public int compareTo(@NotNull Entity entity) {
        super.compareTo(entity);
        // Compare y positions bottoms of the hit boxes.
        boolean comparison = getHitbox().getMaxY() < entity.getHitbox().getMaxY();
        return comparison ? -1 : 1;
    }

}
