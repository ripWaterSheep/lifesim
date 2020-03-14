package lifesim.main.game.entities;

import lifesim.main.game.entities.components.AnimatedSprite;
import lifesim.main.game.entities.components.stats.Stats;
import lifesim.main.game.setting.World;
import lifesim.main.game.entities.components.Sprite;
import lifesim.main.game.entities.components.Vector2D;

public class MovementEntity extends Entity {

    public final Vector2D movement = new Vector2D(0, 0);

    protected final double defaultSpeed;


    public MovementEntity(String name, Sprite sprite, Vector2D pos, double speed, double angle, Stats stats) {
        super(name, sprite, pos, stats);
        defaultSpeed = speed;
        movement.setMagnDir(speed, angle);
    }

    public MovementEntity(String name, Sprite sprite, Vector2D pos, double speed, Stats stats) {
        this(name, sprite, pos, speed, 0, stats);
    }



    @Override
    public void update(World world) {
        super.update(world);
        pos.set(pos.translate(movement));
    }

}
