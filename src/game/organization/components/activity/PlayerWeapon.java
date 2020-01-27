package game.organization.components.activity;

import game.activity.controls.MouseControls;
import game.organization.components.entities.Player;
import game.organization.components.entities.Projectile;
import main.WindowSize;
import util.Geometry;

import java.awt.*;

import static java.lang.Math.sqrt;

public class PlayerWeapon implements Subsystem{


    private Player player;



    public PlayerWeapon(Player player) {
        this.player = player;
    }


    public void controlLogic() {
        if (MouseControls.getRightClicked()) {
            shootBullet(player);
        }
    }


    public void shootBullet(Player player) {
        double strength = player.getStats().getStrength();

        double size = 7 + (strength / 300);
        Color color = new Color(35, 31, 15);
        double damage = sqrt((strength / 24) + 1);
        System.out.println(damage);
        double angle = Geometry.getAngle(MouseControls.getLastClickX(), MouseControls.getLastClickY(), WindowSize.getMidWidth(), WindowSize.getMidHeight());
        new Projectile("Projectile", player.getX(), player.getY(), size, size, true, color, player.getWorld(), 47, angle, 1000, damage, false);
    }


    @Override
    public void run() {
        controlLogic();
    }


}
