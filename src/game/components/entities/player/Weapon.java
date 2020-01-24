package game.components.entities.player;

import game.activity.controls.KeyboardControls;
import game.activity.controls.MouseControls;
import game.components.entities.Projectile;
import main.WindowSize;
import util.Geometry;

import java.awt.*;

import static util.MyMath.betterRound;

public class Weapon {



    public static void controlLogic() {
        if (MouseControls.getRightClicked()) {
            shootBullet();
        }
    }



    public static void shootBullet() {
        Player player = Player.getInstance();
        double strength = player.getStats().getStrength();

        int radius = betterRound(7 + (strength / 250));
        Color color = new Color(35, 31, 15);
        double damage = Math.sqrt((strength / 24) + 1);
        System.out.println(damage);
        double angle = Geometry.getAngle(MouseControls.getLastClickX(), MouseControls.getLastClickY(), WindowSize.getMidWidth(), WindowSize.getMidHeight());
        Projectile projectile = new Projectile("Projectile", player.getX(), player.getY(), radius, color, player.getWorld(), 45, angle, WindowSize.getHypotLength(), damage, 100, false);
    }

}
