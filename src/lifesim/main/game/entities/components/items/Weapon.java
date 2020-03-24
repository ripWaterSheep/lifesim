package lifesim.main.game.entities.components.items;

import lifesim.main.game.controls.MouseInputManager;
import lifesim.main.game.entities.Player;
import lifesim.main.game.entities.Projectile;
import lifesim.main.game.entities.components.sprites.Sprite;
import lifesim.main.game.handlers.World;


public class Weapon extends Item {

    private final Projectile projectile;


    public Weapon(String name, Sprite sprite, Projectile projectile) {
        super(name, sprite);
        this.projectile = projectile;
    }


    @Override
    public void onClick(World world, Player player) {
        Projectile newProjectile = projectile.copyInitialState();
        newProjectile.launchTowards(MouseInputManager.right.getAngleFromCenter());

        world.add(newProjectile, player.pos);
    }
    
}
