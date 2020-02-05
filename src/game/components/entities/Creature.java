package game.components.entities;

import drawing.MyFont;
import game.components.entities.player.Player;
import game.organization.World;
import game.components.entities.stats.CreatureStats;
import util.Geometry;
import util.MyMath;

import java.awt.*;

import static drawing.DrawString.drawCenteredString;
import static game.components.entities.stats.CollisionChecker.getTouchingEntities;
import static util.MyMath.getRand;

public class Creature extends Entity {


    public enum Behaviors {
        PURSUE, // Try to get close to player if player is within range.
        EVADE // Try to get far from player if player is within range.
    }


    protected Behaviors behavior;

    protected final double detectionRange;

    private boolean playerInRange() {
        return (Math.abs(Geometry.getDistance(this, Player.getInstance())) <= detectionRange);
    }


    protected CreatureStats stats;

    public CreatureStats getStats() {
        return stats;
    }



    public Creature(String name, double x, double y, double width, double height, boolean elliptical, Color color,
                    Behaviors behavior, double speed, double detectionRange,
                    double health, double damage, boolean playerAlly, double killLoot) {

        super(name, x, y, width, height, elliptical, color);

        this.behavior = behavior;
        this.detectionRange = detectionRange;
        this.stats = new CreatureStats(this, speed, health, damage, playerAlly, killLoot);
    }



    /** Spawn template for Spawner */
    public Creature(String name, double width, double height, boolean elliptical, Color color,
                         Behaviors behavior, double speed, double detectionRange,
                         double health, double damage, boolean playerAlly, double killLoot) {

        this(name, 0, 0, width, height, elliptical, color, behavior, speed, detectionRange, health, damage, playerAlly, killLoot);
    }



    public Creature(String name, double x, double y, double scale, String imageName,
                    Behaviors behavior, double speed, double detectionRange,
                    double health, double damage, boolean playerAlly, double killLoot) {

        this(name, x, y, 0, 0, false, null, behavior, speed, detectionRange, health, damage, playerAlly, killLoot);

        setImage(imageName, scale);
    }



    /** Copy all fields into new creature and set its location. This is used in class Spawner to clone base instance. */
    public Creature(Creature c, double x, double y, World world) {
        this("Clone of " + c.getName(), x, y, c.width, c.height, c.elliptical, c.color,
            c.behavior, c.stats.getSpeed(), c.detectionRange, c.getStats().getInitialHealth(), c.stats.getDamage(), c.stats.isPlayerAlly(), c.stats.getKillLoot());

        image = c.getImage();
        init(world);
    }



    private void randomMovement() {
        // Switch direction when reached the end of range.
        if (MyMath.getRand(1, 100) <= 4) {
            stats.setAngle(MyMath.getRand(0, 359));
        }
    }



    protected void movementLogic() {
        if (playerInRange()) {
            if (behavior == Behaviors.PURSUE) {
                stats.setAngle(getAngleToPlayer() + 180 + MyMath.getRand(-45, 45));
            } else {
                stats.setAngle(getAngleToPlayer());
            }
        } else {
            randomMovement();
        }

        if (behavior != Behaviors.PURSUE || !getTouchingEntities(this).contains(Player.getInstance()))
            moveTowardsAngle();
    }



    @Override
    public void draw(Graphics g) {
        super.draw(g);
        //drawCenteredString(g, ((int)getStats().getHealth())+"", new Rectangle(getDisplayX(), getDisplayY(), (int)width, (int)height), new Font(MyFont.getMainFont(), Font.PLAIN, 20), Color.WHITE);
    }
}


