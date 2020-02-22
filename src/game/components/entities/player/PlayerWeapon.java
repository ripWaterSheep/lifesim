package game.components.entities.player;

import game.Subsystem;
import game.activity.controls.MouseControls;
import game.components.entities.Projectile;
import main.WindowSize;
import util.Geometry;

import java.awt.*;

import static java.lang.Math.sqrt;


public class PlayerWeapon implements Subsystem {

    private final Player owner;

    private final Color color = new Color(35, 31, 15);


    public PlayerWeapon(Player owner) {
        this.owner = owner;
    }


    private void shoot() {
        double strength = owner.getStats().getStrength();

        double size = 9 + (strength / 275);
        double angle = Geometry.getAngle(MouseControls.getLastClickX(), MouseControls.getLastClickY(), WindowSize.getMidWidth(), WindowSize.getMidHeight());
        double damage = sqrt((strength / 16) + 1);
        System.out.println(damage);
        new Projectile("Player Weapon Projectile", owner.getX(), owner.getY(), size, size, true, color, owner.getWorld(), 43, angle, 800, damage, true);
    }


    @Override
    public void run() {
        if (MouseControls.getRightClicked()) {
            shoot();
        }
    }

}
