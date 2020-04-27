package lifesim.game.items;

import lifesim.game.entities.Player;
import lifesim.util.sprites.Sprite;
import lifesim.game.entities.stats.PlayerStats;
import lifesim.game.handlers.World;
import lifesim.game.input.MouseInput;

import java.awt.*;


public abstract class ClickableItem extends Item {

    public ClickableItem(String name, Sprite sprite) {
        super(name, sprite);
    }

    @Override
    public boolean shouldBeDepleted() {
        return shouldBeUsed();
    }

    @Override
    public final boolean shouldBeUsed() {
        return MouseInput.right.isClicked();
    }

    @Override
    public abstract void use(World world, Player player, PlayerStats stats);


    @Override
    public void renderWhileHolding(Graphics2D g2d, Player player) {
        //icon.render(g2d, player.getDisplayPos(), player.getVelocity());
    }

}
