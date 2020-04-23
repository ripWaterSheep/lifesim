package lifesim.game.items;

import lifesim.game.entities.Player;
import lifesim.game.entities.components.sprites.ImageSprite;
import lifesim.game.entities.components.sprites.Sprite;
import lifesim.game.entities.components.stats.Alliance;
import lifesim.game.entities.components.stats.PlayerStats;
import lifesim.game.entities.types.Launchable;
import lifesim.game.handlers.World;
import lifesim.game.input.MouseInput;

import java.awt.*;


public class Weapon extends ClickableItem {

    private static Sprite reticle = new ImageSprite("reticle");


    private final Launchable projectileType;
    private final boolean showReticle;

    public Weapon(String name, Sprite sprite, Launchable projectileType, boolean showReticle) {
        super(name, sprite);
        this.projectileType = projectileType;
        this.showReticle = showReticle;
    }


    @Override
    public void use(World world, Player player , PlayerStats stats) {
        world.add(projectileType.launchEntity(player, Alliance.PLAYER,
                MouseInput.getCursorPos().getAngleFrom(player.getDisplayPos())), player.getPos());
    }

    @Override
    public void renderWhileHolding(Graphics2D g2d, Player player) {
        super.renderWhileHolding(g2d, player);
        if (showReticle) {
            reticle.render(g2d, MouseInput.getCursorPos(), MouseInput.getCursorVelocity());
        }
    }
}
