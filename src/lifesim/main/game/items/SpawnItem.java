package lifesim.main.game.items;

import lifesim.main.game.entities.Player;
import lifesim.main.game.entities.components.sprites.Sprite;
import lifesim.main.game.entities.components.stats.PlayerStats;
import lifesim.main.game.entities.types.Spawnable;
import lifesim.main.game.handlers.World;
import lifesim.main.game.input.MouseInput;


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
