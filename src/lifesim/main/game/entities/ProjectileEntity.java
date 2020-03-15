package lifesim.main.game.entities;

import lifesim.main.game.entities.components.Vector2D;
import lifesim.main.game.entities.components.sprites.Sprite;
import lifesim.main.game.entities.components.stats.Stats;
import lifesim.main.game.setting.World;

import static java.lang.Math.abs;

public class ProjectileEntity extends MovementEntity {

    private double movementRange;
    private double distanceTravelled;
    private boolean launched = false;


    public ProjectileEntity(String name, Sprite sprite, double speed, Stats stats, double movementRange) {
        super(name, sprite, new Vector2D(0, 0), speed, 0, stats);
        this.movementRange = movementRange;
    }


    public void launchAt(Vector2D pos, double directionDeg) {
        launched = true;
        movement.setMagnDir(defaultSpeed, directionDeg);
    }


    @Override
    public void update(World world) {
        if (launched) {
            super.update(world);

            distanceTravelled += abs(movement.x);
            distanceTravelled += abs(movement.y);

            if (distanceTravelled > movementRange) {
                removeFromWorld();
            }
        }
    }




}
