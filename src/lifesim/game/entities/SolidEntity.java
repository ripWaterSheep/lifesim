package lifesim.game.entities;

import lifesim.engine.Main;
import lifesim.game.entities.stats.InanimateStats;
import lifesim.util.GraphicsMethods;
import lifesim.util.geom.Vector2D;
import lifesim.util.sprites.Sprite;
import lifesim.game.handlers.World;
import lifesim.game.entities.stats.Stats;
import lifesim.util.geom.Rect;

import java.awt.*;

/** This entity cannot be wallked over by any other entity */
public class SolidEntity extends Entity {

    private final double baseDepth; // The actual "floor" of the object that cannot be passed.

    // The entity will be drawn at partial opacity if this is true.
    private boolean semiTransparent = false;

    public SolidEntity(String name, Sprite sprite, Stats stats, double baseDepth) {
        super(name, sprite, stats);
        this.baseDepth = baseDepth;
    }

    public SolidEntity(String name, Sprite sprite, double baseDepth) {
        this(name, sprite, new InanimateStats(), baseDepth);
    }


    /** If the solid entity should be completely flat, make the base that entity's feet cannot walk over the whole height of the sprite,
     * preventing its sprite from overlapping others and making it not look like its sticking out in 3D.
     */
    public SolidEntity(String name, Sprite sprite, Stats stats) {
        this(name, sprite, stats, sprite.getSize().y);
    }

    public SolidEntity(String name, Sprite sprite) {
        this(name, sprite, new InanimateStats());
    }

    /** Get the hitbox that other entities cannot cross with their feet.
     * It is at the bottom of this sprite's actual hitbox.
     * The reason for this is so that other entities can overlap this entity to give the illusion of 3d,
     * without them walking over the base of this entity with their feet.
     * */
    @Override
    public Rect getHitBox() {
        Rect hb = super.getHitBox();
        Vector2D dims = new Vector2D(hb.width, baseDepth);// Set y so that bottom of base it at bottom of hitbox
        return new Rect(hb.getCenterPos(), dims);
    }

    @Override
    public Vector2D getDisplayPos() {
        return super.getDisplayPos().translate(0, -sprite.getSize().y/2 + baseDepth/2);
    }

    @Override
    public Rect getDisplayHitBox() {
        Vector2D dims = new Vector2D(super.getDisplayHitBox().width, baseDepth);
        return new Rect(super.getDisplayPos(), dims);
    }

    @Override
    public void handleCollision(Entity entity, World world) {
        super.handleCollision(entity, world);

        // If entity is flat, then it can be overlapped and therefore shouldn't be pushed away.
        if (entity.isFlat()) return;
        Rect entityRect = entity.getHitBox();
        // Keep entity's bottom bound (AKA feet) outside of this entity's base.
        entityRect.clampBottomOutside(getHitBox());
        entity.setPos(entityRect.getCenterPos());
    }

    @Override
    public void update(World world) {
        super.update(world);
        semiTransparent = super.getDisplayHitBox().contains(Main.getCurrentPlayer().getDisplayHitBox());
    }


    @Override
    protected void renderShadow(Graphics2D g2d) {
        // Put shadow in center of base
        Rect shadowRect = super.getDisplayHitBox();
        shadowRect.y += (sprite.getSize().y - baseDepth);

        g2d.setColor(new Color(0, 0, 0, 100));
        g2d.fill(shadowRect);
    }

    @Override
    public void render(Graphics2D g2d) {
        if (semiTransparent) {
            GraphicsMethods.setOpacity(g2d, 0.65);
        }
        super.render(g2d);
    }

}
