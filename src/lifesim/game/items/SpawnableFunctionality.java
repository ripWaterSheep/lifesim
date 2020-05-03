package lifesim.game.items;

import lifesim.game.entities.Entity;
import lifesim.game.entities.Player;
import lifesim.game.entities.Projectile;
import lifesim.game.entities.stats.Alliance;
import lifesim.game.entities.stats.PlayerStats;
import lifesim.game.entities.types.Launchable;
import lifesim.game.entities.types.Spawnable;
import lifesim.game.handlers.World;
import lifesim.input.MouseInput;
import lifesim.state.engine.GameWindow;
import lifesim.util.GraphicsMethods;
import lifesim.util.geom.Vector2D;
import lifesim.util.sprites.Sprite;

import java.awt.*;

public class SpawnableFunctionality extends ItemFunctionality {

    private final Spawnable spawnable;

    public SpawnableFunctionality(Spawnable spawnable) {
        this.spawnable = spawnable;
    }

    @Override
    public void use(World world, Player player , PlayerStats stats) {
        world.add(spawnable.spawnEntity(), MouseInput.getCursorPos().translate(player.getPos()));
    }

    @Override
    public void render(Graphics2D g2d, Player player, GameWindow window) {
        GraphicsMethods.setOpacity(g2d, 0.35);

        // Show translucent hint at what the entity looks like at the mouse cursor.
        Entity shownEntity = spawnable.spawnEntity();
        shownEntity.setPos(MouseInput.getCursorPos().translate(player.getPos()));
        shownEntity.render(g2d);
    }
}
