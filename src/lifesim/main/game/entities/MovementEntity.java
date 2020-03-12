package lifesim.main.game.entities;

import lifesim.main.game.setting.World;
import lifesim.main.util.drawing.Sprite;
import lifesim.main.util.math.Vector2D;

public class MovementEntity extends DamageEntity {

    protected Vector2D movement = new Vector2D(0, 0);

    protected final double defaultSpeed;


    public MovementEntity(String name, Sprite sprite, Vector2D pos, double speed, double angle, double damage) {
        super(name, sprite, pos, damage);
        defaultSpeed = speed;
        movement.setMagnDir(speed, angle);
    }

    public MovementEntity(String name, Sprite sprite, Vector2D pos, double speed, double damage) {
        this(name, sprite, pos, speed, 0, damage);
    }


    @Override
    public void update(World world) {
        super.update(world);
        pos.set(pos.translate(movement));
    }

    @Override
    public void collision(Entity entity) {

    }
}
