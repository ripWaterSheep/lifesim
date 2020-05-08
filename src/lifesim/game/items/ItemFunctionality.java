package lifesim.game.items;

import lifesim.game.entities.Player;
import lifesim.game.entities.stats.PlayerStats;
import lifesim.game.handlers.World;
import lifesim.engine.output.GameWindow;

import java.awt.*;

public abstract class ItemFunctionality {

    public abstract void use(World world, Player player, PlayerStats stats);

    public abstract void render(Graphics2D g2d, Player player, GameWindow window);

}
