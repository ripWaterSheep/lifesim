package lifesim.game.items;

import lifesim.game.entities.Player;
import lifesim.game.entities.components.sprites.Sprite;
import lifesim.game.entities.components.stats.Alliance;
import lifesim.game.entities.components.stats.PlayerStats;
import lifesim.game.entities.types.Launchable;
import lifesim.game.handlers.World;
import lifesim.game.input.MouseInput;


public class Weapon extends ClickableItem {

    private final Launchable projectileType;

    public Weapon(String name, Sprite sprite, Launchable projectileType) {
        super(name, sprite);
        this.projectileType = projectileType;
    }


    @Override
    public void use(World world, Player player , PlayerStats stats) {
        world.add(projectileType.launchEntity(player, Alliance.PLAYER,  MouseInput.getPos().getAngleFrom(player.getDisplayPos())), player.getPos());
    }

}
