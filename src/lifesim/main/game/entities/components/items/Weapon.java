package lifesim.main.game.entities.components.items;

import lifesim.main.game.Game;
import lifesim.main.game.controls.MouseInputManager;
import lifesim.main.game.entities.Entity;
import lifesim.main.game.entities.MovementEntity;
import lifesim.main.game.entities.Projectile;
import lifesim.main.game.entities.TempEntity;
import lifesim.main.game.entities.components.Vector2D;
import lifesim.main.game.entities.components.sprites.Sprite;
import lifesim.main.game.entities.enemies.Enemy;
import lifesim.main.game.handlers.World;

import static lifesim.main.util.math.Geometry.getAngleBetween;


public class Weapon extends Item {

    private final Entity bullet;


    public Weapon(String name, Sprite sprite, Entity bullet) {
        super(name, sprite);
        this.bullet = bullet;
    }


    @Override
    public void onClick(World world, Entity entity) {
        Entity newBullet = bullet.copyInitialState();

        if (newBullet instanceof MovementEntity)
            ((MovementEntity) newBullet).movement.setDirection(getAngleBetween(MouseInputManager.right.getPos(),
                    new Vector2D(0, 0)));

        world.add(newBullet, entity.pos);
    }
    
}
