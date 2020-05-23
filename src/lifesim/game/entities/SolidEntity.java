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

        // Set y so that bottom of base it at bottom of hitbox
        Vector2D pos = hb.getCenterPos();
        pos.translate(0, hb.height/2 - baseDepth/2.0);
        Vector2D dims = new Vector2D(hb.width, baseDepth);

        return new Rect(pos, dims);
    }

    @Override
    public Rect getDisplayHitBox() {
        Rect hb = getHitBox();

        hb.translatePos(getDisplayPos());
        hb.translatePos(super.getHitBox().getCenterPos().negate());
        return hb;
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
        semiTransparent = super.getHitBox().contains(Main.getCurrentPlayer().getHitBox());
    }


    @Override
    protected void renderShadow(Graphics2D g2d) {
        // Draw shadow under sprite that is half as tall as it.
        g2d.setColor(new Color(0, 0, 0, 100));
        Rect displayHitBox = sprite.getBoundsAt(getDisplayPos());

        // Set y so that bottom of base it at bottom of hitbox
        Vector2D pos = displayHitBox.getCenterPos();
        pos.translate(0, (displayHitBox.height/2 - baseDepth) + displayHitBox.height/2);
        Vector2D dims = new Vector2D(displayHitBox.width, displayHitBox.height);
        g2d.fill(new Rect(pos, dims));
    }

    @Override
    public void render(Graphics2D g2d) {
        if (semiTransparent) {
            GraphicsMethods.setOpacity(g2d, 0.65);
        }
        //TODO: translate graphics to make center position of sprite in the center of the base.
        super.render(g2d);
    }

}
