package lifesim.game.item;

import lifesim.game.entities.Player;
import lifesim.game.entities.Projectile;
import lifesim.game.entities.stats.Alliance;
import lifesim.game.entities.stats.PlayerStats;
import lifesim.game.entities.types.Launchable;
import lifesim.game.handlers.World;
import lifesim.io.input.MouseInput;
import lifesim.io.output.GameWindow;
import lifesim.util.geom.Vector2D;

import java.awt.*;

class LaunchFunctionality extends ConsumeFunctionality {

    private final Launchable launchable;
    private final double recoilMagnitude;

    public LaunchFunctionality(Launchable launchable, double recoilMagnitude) {
        this.launchable = launchable;
        this.recoilMagnitude = recoilMagnitude;
    }


    @Override
    public void use(World world, Player player, PlayerStats stats) {
        Projectile projectile = launchable.launchEntity(player, Alliance.PLAYER,
                MouseInput.getCursorPos().getAngleFrom(player.getDisplayPos()));

        if (projectile.getVelocity().getMagnitude() > 0) {
            Vector2D recoil = projectile.getVelocity().normalize().scale(-recoilMagnitude);
            player.push(recoil);
        }

        world.add(projectile, player.getPos());
    }

    @Override
    public void render(Graphics2D g2d, Player player, GameWindow window) {

    }

}
