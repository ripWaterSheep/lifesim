package lifesim.game.items.OLD;

import lifesim.game.entities.Entity;
import lifesim.game.entities.Player;
import lifesim.game.items.OLD.ClickableItem;
import lifesim.state.engine.GameWindow;
import lifesim.util.sprites.Sprite;
import lifesim.game.entities.stats.PlayerStats;
import lifesim.game.entities.types.Spawnable;
import lifesim.game.handlers.World;
import lifesim.input.MouseInput;
import lifesim.util.GraphicsMethods;

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
    public void render(Graphics2D g2d, Player player, GameWindow window) {
        super.render(g2d, player, window);
        GraphicsMethods.setOpacity(g2d, 0.35);

        Entity shownEntity = spawnable.spawnEntity();
        shownEntity.setPos(MouseInput.getCursorPos().translate(player.getPos()));
        shownEntity.render(g2d);
    }
}
