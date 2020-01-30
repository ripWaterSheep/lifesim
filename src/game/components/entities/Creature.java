package game.components.entities;

import drawing.MyFont;
import game.components.entities.player.Player;
import game.organization.World;
import game.components.entities.stats.CreatureStats;
import util.Geometry;

import java.awt.*;

import static drawing.DrawString.drawCenteredString;
import static game.components.entities.stats.CollisionChecker.getTouchingEntities;
import static util.MyMath.getRandInRange;

public class Creature extends Entity {


    public enum Behaviors {
        PURSUE, // Try to get close to player if player is within range.
        EVADE // Try to get far from player if player is within range.
    }


    protected Behaviors behavior;

    private final double initialHealth; // Keep track of health set when first spawned in order to spawn full-health clones, no matter how much actual health creature still has.

    private final double detectionRange;

    private boolean playerInRange() {
        return (Math.abs(Geometry.getDistanceBetween(this, Player.getInstance())) <= detectionRange);
    }

    protected double getAngleToPlayer() {
        return Geometry.getAngle(x, y, Player.getInstance().getX(), Player.getInstance().getY());
    }


    protected CreatureStats stats;

    public CreatureStats getStats() {
        return stats;
    }



    public Creature(String name, double x, double y, double width, double height, boolean elliptical, Color color,
                    Behaviors behavior, double speed, double detectionRange,
                    double health, double damage, boolean canDamagePlayer, double killLoot) {

        super(name, x, y, width, height, elliptical, color);

        initialHealth = health;
        this.behavior = behavior;
        this.detectionRange = detectionRange;
        this.stats = new CreatureStats(this, speed, health, damage, canDamagePlayer, killLoot);
    }


    public Creature(String name, double width, double height, boolean elliptical, Color color,
                         Behaviors behavior, double speed, double detectionRange,
                         double health, double damage, boolean canDamagePlayer, double killLoot) {

        this(name, 0, 0, width, height, elliptical, color, behavior, speed, detectionRange, health, damage, canDamagePlayer, killLoot);
    }



    public Creature(String name, double x, double y, double scale, String imageName,
                    Behaviors behavior, double speed, double detectionRange,
                    double health, double damage, boolean canDamagePlayer, double killLoot) {

        this(name, x, y, 0, 0, false, null, behavior, speed, detectionRange, health, damage, canDamagePlayer, killLoot);
        setImage(imageName, scale);
    }



    /** Copy all fields into new c (for spawners) and set its location. This is used in class Spawner to clone base instance. */
    public Creature(Creature c, double x, double y, World world) {
        this("Clone of " + c.getName(), x, y, c.width, c.height, c.isElliptical(), c.color,
            c.behavior, c.stats.getSpeed(), c.detectionRange, c.initialHealth, c.stats.getDamage(), c.stats.canDamagePlayer(), c.stats.getKillLoot());

        image = c.getImage();
        init(world);
    }



    private void randomMovement() {
        // Switch direction when reached the end of range.
        if (getRandInRange(1, 100) <= 4) {
            stats.setAngle(getRandInRange(0, 359));
        }
    }


    protected void movementLogic() {
        if (playerInRange()) {
            if (behavior == Behaviors.PURSUE) {
                stats.setAngle(getAngleToPlayer() + 180 + getRandInRange(-45, 45));
            } else {
                stats.setAngle(getAngleToPlayer());
            }
        } else  {
            randomMovement();
        }

        if (behavior != Behaviors.PURSUE || !getTouchingEntities(this).contains(Player.getInstance()))
            moveTowardsAngle();

    }


    @Override
    public void draw(Graphics g) {
        super.draw(g);
        drawCenteredString(g, ((int)getStats().getHealth())+"", new Rectangle(getDisplayX(), getDisplayY(), (int)width, (int)height), new Font(MyFont.getMainFont(), Font.PLAIN, 20), Color.WHITE);
    }
}


