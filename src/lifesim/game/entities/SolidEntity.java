package lifesim.game.entities;

import lifesim.game.entities.components.sprites.Sprite;
import lifesim.game.handlers.World;
import lifesim.game.entities.components.stats.Stats;

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
