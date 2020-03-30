package lifesim.main.game.items;

import lifesim.main.game.controls.MouseInputManager;
import lifesim.main.game.entities.Entity;
import lifesim.main.game.entities.Player;
import lifesim.main.game.entities.Projectile;
import lifesim.main.game.entities.components.sprites.Sprite;
import lifesim.main.game.entities.components.stats.PlayerStats;
import lifesim.main.game.handlers.World;


public class SpawnItem extends Item {

    private final Entity spawnEntity;


    public SpawnItem(String name, Sprite sprite, Entity spawnEntity) {
        super(name, sprite);
        this.spawnEntity = spawnEntity;
    }


    @Override
    public void onClick(World world, Player player , PlayerStats stats) {
        Entity spawn = spawnEntity.copyInitialState();
        spawn.movement.setDirection(MouseInputManager.right.getAngleFromCenter());
        world.add(spawn, player.pos);
    }
    
}
