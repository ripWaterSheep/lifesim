package lifesim.game.items;

import lifesim.game.entities.Player;
import lifesim.game.entities.Projectile;
import lifesim.state.engine.Main;
import lifesim.state.menus.ui.CursorType;
import lifesim.util.sprites.ImageSprite;
import lifesim.util.sprites.Sprite;
import lifesim.game.entities.stats.Alliance;
import lifesim.game.entities.stats.PlayerStats;
import lifesim.game.entities.types.Launchable;
import lifesim.game.handlers.World;
import lifesim.game.input.MouseInput;
import lifesim.util.geom.Vector2D;

import java.awt.*;

import static lifesim.game.entities.types.EffectType.BIG_BOOM;


public class LaunchableItem extends ClickableItem {

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

        System.out.println(BIG_BOOM.spawnEntity().canDamage(projectile));
    }


    @Override
    public void renderOnPlayer(Graphics2D g2d, Player player) {
        super.renderOnPlayer(g2d, player);
        if (showReticle) {
            Main.getWindow().changeCursor(CursorType.RETICLE);
        }
    }
}
