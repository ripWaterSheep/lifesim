package game.components.entities;

import drawing.MyImage;
import game.components.entities.player.Player;
import game.organization.World;

import java.awt.*;

import static util.Geometry.getDistance;
import static util.TimeUtil.getCurrentTime;

public class RangedCreature extends Creature {

    private Projectile shotTemplate;

    private final long shootInterval;
    private long lastShootTime = 0;


    public RangedCreature(String name, double x, double y, double width, double height, boolean elliptical, Color color,
                          Behaviors behavior, double speed, double detectionRange,
                          double health, double damage, boolean canDamagePlayer, double killLoot,
                          long shootInterval, Projectile shotTemplate) {

        super(name, x, y, width, height, elliptical, color, behavior, speed, detectionRange, health, damage, canDamagePlayer, killLoot);

        this.shootInterval = shootInterval;
        this.shotTemplate = shotTemplate;
    }


    public RangedCreature(String name, double x, double y, double scale, String imageName,
                               Behaviors behavior, double speed, double detectionRange,
                               double health, double damage, boolean canDamagePlayer, double killLoot,
                               long shootInterval, Projectile shotTemplate) {

        this(name, x, y, 0, 0, false, null, behavior, speed, detectionRange, health, damage, canDamagePlayer, killLoot, shootInterval, shotTemplate);

        setImage(imageName, scale);
    }


    /** Copy all fields into new creature and set its location. This is used in class Spawner to clone base instance. */
    public RangedCreature(RangedCreature c, double x, double y, World world) {
        this("Clone of " + c.getName(), x, y, c.width, c.height, c.elliptical, c.color,
                c.behavior, c.stats.getSpeed(), c.detectionRange, c.getStats().getInitialHealth(), c.stats.getDamage(), c.stats.canDamagePlayer(), c.stats.getKillLoot(),
                c.shootInterval, c.shotTemplate);

        image = c.getImage();
        init(world);
    }




    private void shootLogic() {
        // Shoot out new clone of the projectile passed as a parameter if spawn interval passes
        if (getCurrentTime() - lastShootTime > shootInterval && getDistance(this, Player.getInstance()) < shotTemplate.getRange()) {
            new Projectile(shotTemplate, x, y, world, 180+getAngleToPlayer());
            lastShootTime = getCurrentTime();
        }
    }


    @Override
    protected void movementLogic() {
        // DOn't move if player is in range and this is supposed to chase the player.
        if (getDistance(this, Player.getInstance()) > shotTemplate.getRange() || !(behavior == Behaviors.PURSUE))
            super.movementLogic();
    }


    @Override
    public void update() {
        super.update();
        shootLogic();
    }
}