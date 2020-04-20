package lifesim.main.game.items;

import lifesim.main.game.entities.Player;
import lifesim.main.game.entities.components.sprites.Sprite;
import lifesim.main.game.entities.components.stats.PlayerStats;
import lifesim.main.game.handlers.World;
import lifesim.main.util.math.Vector2D;

import java.awt.*;


public abstract class HoldableItem extends Item {

    private final Sprite effectSprite;

    private final long depletionInterval;
    private long lastDepletionTime = System.currentTimeMillis();

    public HoldableItem(String name, Sprite sprite, Sprite effectSprite, long depletionInterval) {
        super(name, sprite);
        this.effectSprite = effectSprite;
        this.depletionInterval = depletionInterval;
    }

    @Override
    public boolean shouldBeDepleted() {
        if (System.currentTimeMillis() - lastDepletionTime > depletionInterval) {
            lastDepletionTime = System.currentTimeMillis();
            return true;
        }
        return false;
    }

    @Override
    public boolean shouldBeUsed() {
        return true;
    }


    public void use(World world, Player player, PlayerStats stats) {

    }


    @Override
    public void renderIcon(Graphics2D g2d, Vector2D pos) {
        super.renderIcon(g2d, pos);
    }

    @Override
    public void renderWhileHolding(Graphics2D g2d, Player player) {
        effectSprite.render(g2d, player.getDisplayPos(), player.getVelocity());
    }
}
