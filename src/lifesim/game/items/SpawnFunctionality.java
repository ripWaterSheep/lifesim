package lifesim.game.items;

import lifesim.game.entities.Entity;
import lifesim.game.entities.Player;
import lifesim.game.entities.stats.PlayerStats;
import lifesim.game.entities.types.Spawnable;
import lifesim.game.handlers.World;
import lifesim.input.MouseInput;
import lifesim.state.engine.GameWindow;
import lifesim.util.GraphicsMethods;

import java.awt.*;

public class SpawnFunctionality extends ItemFunctionality {

    private final Spawnable spawnable;

    public SpawnFunctionality(Spawnable spawnable) {
        this.spawnable = spawnable;
    }

    @Override
    public void use(World world, Player player , PlayerStats stats) {
        world.add(spawnable.spawnEntity(), MouseInput.getCursorPosFrom(player.getPos()));
    }

    @Override
    public void render(Graphics2D g2d, Player player, GameWindow window) {
        GraphicsMethods.setOpacity(g2d, 0.35);

        // Show translucent hint at what the entity looks like at the mouse cursor.
        Entity shownEntity = spawnable.spawnEntity();
        shownEntity.setPos(MouseInput.getCursorPosFrom(player.getPos()));
        shownEntity.render(g2d);
    }
}
