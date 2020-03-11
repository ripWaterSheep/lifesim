package lifesim.main.game.entities;

import lifesim.main.util.drawing.Sprite;
import lifesim.main.util.math.Vector2D;

public class StructureEntity extends DamageEntity {

    public StructureEntity(String name, Sprite sprite, Vector2D pos, double damage) {
        super(name, sprite, pos, damage);
    }

    public StructureEntity(String name, Sprite sprite, Vector2D pos) {
        this(name, sprite, pos, 0);
    }

}
