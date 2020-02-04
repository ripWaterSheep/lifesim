package game.components.entities.player;

import game.Subsystem;
import game.activity.controls.MouseControls;
import game.components.entities.player.Player;
import game.components.entities.Projectile;
import main.WindowSize;
import util.Geometry;

import java.awt.*;

import static java.lang.Math.sqrt;

public class PlayerWeapon implements Subsystem {


    private Player player;

    PlayerWeapon(Player player) {
        this.player = player;
    }


    private void shootBullet(Player player) {
        double strength = player.getStats().getStrength();

        double size = 10 + (strength / 400);
        Color color = new Color(35, 31, 15);
        double damage = sqrt((strength / 20) + 1);
        System.out.println(damage);
        double angle = Geometry.getAngle(MouseControls.getLastClickX(), MouseControls.getLastClickY(), WindowSize.getMidWidth(), WindowSize.getMidHeight());
        new Projectile("Projectile", player.getX(), player.getY(), size, size, true, color, player.getWorld(), 43, angle, 800, damage, false);
    }


    private void controlLogic() {
        if (MouseControls.getRightClicked()) {
            shootBullet(player);
        }
    }


    @Override
    public void run() {
        controlLogic();
    }


}
