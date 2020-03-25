package lifesim.main.game.items;

import lifesim.main.game.entities.Player;
import lifesim.main.game.entities.components.sprites.Sprite;
import lifesim.main.game.entities.components.stats.PlayerStats;
import lifesim.main.game.handlers.World;


public class Consumable extends Item {

    public Consumable(String name, Sprite sprite) {
        super(name, sprite);
    }


    public void consume(Player player, PlayerStats stats) { }

    @Override
    public void onClick(World world, Player player) {
        super.onClick(world, player);
        consume(player, (PlayerStats) player.stats);

    }

}
