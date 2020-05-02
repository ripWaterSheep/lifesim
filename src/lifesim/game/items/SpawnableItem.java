package lifesim.game.items;

import lifesim.game.entities.Player;
import lifesim.state.engine.GameWindow;
import lifesim.util.sprites.Sprite;
import lifesim.game.entities.stats.PlayerStats;
import lifesim.game.entities.types.Spawnable;
import lifesim.game.handlers.World;
import lifesim.input.MouseInput;
import lifesim.util.GraphicsMethods;
import lifesim.util.geom.Vector2D;

import java.awt.*;


public class SpawnableItem extends ClickableItem {

    private final Spawnable spawnable;

    public SpawnableItem(String name, Sprite sprite, Spawnable spawnable) {
        super(name, sprite);
        this.spawnable = spawnable;
    }

    @Override
    public void use(World world, Player player , PlayerStats stats) {
        world.add(spawnable.spawnEntity(), MouseInput.getCursorPos().translate(player.getPos()));
    }

    @Override
    public void renderOnPlayer(Graphics2D g2d, Player player, GameWindow window) {
        super.renderOnPlayer(g2d, player, window);
        GraphicsMethods.setOpacity(g2d, 0.35);
        spawnable.spawnEntity().sprite.render(g2d, MouseInput.getCursorPos(), new Vector2D(0, 0));
    }
}
