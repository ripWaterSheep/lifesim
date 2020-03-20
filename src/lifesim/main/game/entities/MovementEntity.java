package lifesim.main.game.entities;

import lifesim.main.game.entities.components.stats.Stats;
import lifesim.main.game.handlers.World;
import lifesim.main.game.entities.components.sprites.Sprite;
import lifesim.main.game.entities.components.Vector2D;

import java.awt.*;

import static java.lang.Math.abs;

public class MovementEntity extends Entity {

    public final Vector2D movement = new Vector2D(0, 0);
    protected final double defaultSpeed;


    public MovementEntity(String name, Sprite sprite, Stats stats, double speed) {
        super(name, sprite, stats);
        defaultSpeed = speed;
        movement.setMagnDir(speed, 0);
    }


    protected void move() {
        pos.set(pos.translate(movement));
        // Fully stop if speed is tiny
        double stationaryThreshold = 0.05;
        if (abs(movement.x) < stationaryThreshold) movement.x = 0;
        if (abs(movement.y) < stationaryThreshold) movement.y = 0;
    }


    @Override
    public void update(World world) {
        super.update(world);
        move();
    }

    @Override
    public void render(Graphics2D g2d) {
        sprite.render(g2d, getDisplayPos(), movement);
    }
}
