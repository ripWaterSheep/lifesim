package lifesim.main.game.entities;

import lifesim.main.game.entities.components.stats.Stats;
import lifesim.main.game.setting.World;
import lifesim.main.game.entities.components.sprites.Sprite;
import lifesim.main.game.entities.components.Vector2D;

import java.awt.*;

public class MovementEntity extends Entity {

    public final Vector2D movement = new Vector2D(0, 0);
    protected final double defaultSpeed;


    public MovementEntity(String name, Sprite sprite, double speed, double angle, Stats stats) {
        super(name, sprite, stats);
        defaultSpeed = speed;
        movement.setMagnDir(speed, angle);
    }

    public MovementEntity(String name, Sprite sprite, double speed, Stats stats) {
        this(name, sprite, speed, 0, stats);
    }


    @Override
    public void update(World world) {
        super.update(world);
        pos.set(pos.translate(movement));
    }

    @Override
    public void render(Graphics2D g2d) {
        sprite.render(g2d, getDisplayPos(), movement);
    }
}
