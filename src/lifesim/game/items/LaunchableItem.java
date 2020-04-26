package lifesim.game.items;

import lifesim.game.entities.Player;
import lifesim.game.entities.Projectile;
import lifesim.game.entities.components.sprites.ImageSprite;
import lifesim.game.entities.components.sprites.Sprite;
import lifesim.game.entities.components.stats.Alliance;
import lifesim.game.entities.components.stats.PlayerStats;
import lifesim.game.entities.types.Launchable;
import lifesim.game.handlers.World;
import lifesim.game.input.MouseInput;
import lifesim.util.geom.Vector2D;

import java.awt.*;


public class LaunchableItem extends ClickableItem {

    private static final Sprite reticle = new ImageSprite("reticle");


    private final Launchable launchable;
    private final double recoilMagnitude;
    private final boolean showReticle;

    public LaunchableItem(String name, Sprite sprite, Launchable launchable, double recoilMagnitude, boolean showReticle) {
        super(name, sprite);
        this.launchable = launchable;
        this.recoilMagnitude = recoilMagnitude;
        this.showReticle = showReticle;
    }


    @Override
    public void use(World world, Player player , PlayerStats stats) {
        Projectile projectile = launchable.launchEntity(player, Alliance.PLAYER,
                MouseInput.getCursorPos().getAngleFrom(player.getDisplayPos()));

        if (projectile.getVelocity().getMagnitude() > 0) {
            Vector2D recoil = projectile.getVelocity().normalize().scale(-recoilMagnitude);
            player.push(recoil);
        }
        world.add(projectile, player.getPos());
    }

    @Override
    public void renderWhileHolding(Graphics2D g2d, Player player) {
        super.renderWhileHolding(g2d, player);
        if (showReticle) {
            reticle.render(g2d, MouseInput.getCursorPos(), MouseInput.getCursorVelocity());
        }
    }
}
