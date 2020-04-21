package lifesim.game.items;

import lifesim.game.entities.Player;
import lifesim.game.entities.components.sprites.Sprite;
import lifesim.game.entities.components.stats.PlayerStats;
import lifesim.game.entities.types.Spawnable;
import lifesim.game.handlers.World;
import lifesim.game.input.MouseInput;


public class SpawnItem extends ClickableItem {

    private final Spawnable entityType;

    public SpawnItem(String name, Sprite sprite, Spawnable entityType) {
        super(name, sprite);
        this.entityType = entityType;
    }

    @Override
    public void use(World world, Player player , PlayerStats stats) {
        world.add(entityType.spawnEntity(), MouseInput.getPos().translate(player.getPos()));
    }

}
