package lifesim.main.game.entities.components.items;

import lifesim.main.game.Game;
import lifesim.main.game.controls.MouseInputManager;
import lifesim.main.game.entities.Entity;
import lifesim.main.game.entities.Projectile;
import lifesim.main.game.entities.components.Vector2D;
import lifesim.main.game.entities.components.sprites.Sprite;
import lifesim.main.game.handlers.World;

import static lifesim.main.util.math.Geometry.getAngleBetween;


public class Weapon extends Item {

    private final Projectile bullet;

    public Weapon(String name, Sprite sprite, Projectile bullet) {
        super(name, sprite);
        this.bullet = bullet;
    }

    public double getAttackRange() {
        return bullet.getMovementRange();
    }


    @Override
    public void onClick(World world, Entity entity) {
        world.add(bullet.copyInitialState(getAngleBetween(MouseInputManager.right.getPos(),
                        new Vector2D(Game.getPanel().getWidth()/2.0, Game.getPanel().getHeight()/2.0))), entity.pos);
    }
    
}
