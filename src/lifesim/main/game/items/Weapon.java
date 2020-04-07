package lifesim.main.game.items;

import lifesim.main.game.controls.MouseInputManager;
import lifesim.main.game.entities.Player;
import lifesim.main.game.entities.components.sprites.Sprite;
import lifesim.main.game.entities.components.stats.Alliance;
import lifesim.main.game.entities.components.stats.PlayerStats;
import lifesim.main.game.entities.types.Launchable;
import lifesim.main.game.handlers.World;


public class Weapon extends Item {

    private final Launchable projectileType;

    public Weapon(String name, Sprite sprite, Launchable projectileType) {
        super(name, sprite);
        this.projectileType = projectileType;
    }


    @Override
    public void use(World world, Player player , PlayerStats stats) {
        world.add(projectileType.launchNew(player, Alliance.PLAYER,  MouseInputManager.right.getAngleFromCenter()), player.getPos());
    }

}
