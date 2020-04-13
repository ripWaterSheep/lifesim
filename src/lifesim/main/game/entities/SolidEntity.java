package lifesim.main.game.entities;

import lifesim.main.game.entities.components.sprites.Sprite;
import lifesim.main.game.entities.components.stats.Stats;
import lifesim.main.game.handlers.World;
import lifesim.main.util.math.Geometry;

import java.awt.*;

public class SolidEntity extends Entity {

    public SolidEntity(String name, Sprite sprite, Stats stats) {
        super(name, sprite, stats);
    }

    public SolidEntity(String name, Sprite sprite) {
        super(name, sprite);
    }

    @Override
    public Shape getHitBox() {
        Shape hitBox = super.getHitBox();
        hitBox.getBounds().grow(1, 1);
        return hitBox;
    }

    @Override
    public void handleCollision(Entity entity, World world) {
        super.handleCollision(entity, world);
        entity.pos.keepRectOutOfRect(entity.sprite.getSize(), pos, sprite.getSize());
    }
}
