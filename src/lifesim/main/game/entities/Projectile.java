package lifesim.main.game.entities;

import lifesim.main.game.entities.components.sprites.Sprite;
import lifesim.main.game.entities.components.stats.Stats;
import lifesim.main.game.handlers.World;

import static java.lang.Math.abs;


public class Projectile extends MovementEntity {

    private final double movementRange;
    private double distanceTravelled;


    public Projectile(String name, Sprite sprite, Stats stats, double speed, double movementRange) {
        super(name, sprite, stats, speed);
        this.movementRange = movementRange;
    }


    public double getMovementRange() {
        return movementRange;
    }


    public Projectile copyInitialState(double direction) {
        Projectile copy = new Projectile(name, sprite, stats, defaultSpeed, movementRange);
        copy.movement.setMagnDir(defaultSpeed, direction);
        return copy;
    }


    @Override
    public void update(World world) {
        super.update(world);

        distanceTravelled += abs(movement.x);
        distanceTravelled += abs(movement.y);

        if (distanceTravelled > movementRange) {
            die();
       }
    }




}
