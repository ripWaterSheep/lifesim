package lifesim.main.game.entities.components.items;

import lifesim.main.game.entities.Entity;
import lifesim.main.game.entities.components.sprites.Sprite;
import lifesim.main.game.entities.components.stats.PlayerStats;
import lifesim.main.game.handlers.World;


public class Consumable extends Item {

    private PlayerStats stats;


    public Consumable(String name, Sprite sprite) {
        super(name, sprite);
    }


    public void consume(PlayerStats stats) { }

    @Override
    public void onClick(World world, Entity entity) {
        super.onClick(world, entity);
        if (entity.stats instanceof PlayerStats)
        consume((PlayerStats) entity.stats);
    }

}
