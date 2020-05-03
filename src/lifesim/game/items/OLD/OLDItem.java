package lifesim.game.items.OLD;

import lifesim.game.entities.Player;
import lifesim.state.engine.GameWindow;
import lifesim.util.sprites.Sprite;
import lifesim.game.entities.stats.PlayerStats;
import lifesim.game.entities.itemEntites.DroppedItem;
import lifesim.game.handlers.World;
import lifesim.util.geom.Vector2D;

import java.awt.*;


public abstract class OLDItem {

    public final String name;
    public final Sprite icon;

    public OLDItem(String name, Sprite icon) {
        this.name = name;
        this.icon = icon;
    }
/*
    public DroppedItem getDroppedEntity(int amount) {
        return new DroppedItem(this, amount);
    }*/

    public abstract boolean shouldBeUsed();

    public abstract boolean shouldBeDepleted();


    public abstract void use(World world, Player player, PlayerStats stats);

    public abstract void render(Graphics2D g2d, Player player, GameWindow window);

    public void renderIcon(Graphics2D g2d, Vector2D pos) {
        icon.render(g2d, pos, new Vector2D(0, 0));
    }

}
