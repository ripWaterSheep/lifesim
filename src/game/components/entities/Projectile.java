package game.components.entities;

import game.components.GameComponent;
import game.components.World;
import game.components.entities.player.Player;

import java.awt.*;
import java.util.ArrayList;

public class Projectile extends Entity {


    private static ArrayList<Creature> projectileInstances = new ArrayList<>();

    public static ArrayList<Creature> getProjectileInstances() { return projectileInstances; }


    protected double currentDistance = 0; // How far the Projectile currently has gone
    protected final double range; // How far to move towards specified angle


    protected final double damage;
    protected final boolean canDamagePlayer;

    private ArrayList<GameComponent> lastTouching = new ArrayList<>();


    protected boolean isTouchingPlayer() { return lastTouching.contains(Player.getInstance()); }



    public Projectile(String name, double x, double y, double radius, World world, Color color, double speed, double angle, double range, double damage, double health, boolean canDamagePlayer) {
        super(name, x, y, radius, world, color, speed, angle, health);
        this.range = range;
        this.damage = damage;
        this.canDamagePlayer = canDamagePlayer;

    }


    @Override
    protected void movementLogic() {
        // Entity moves along specific angle
        moveTowardsAngle();
        // Die around when at edge of range
        if (Math.abs(currentDistance) >= range) {
            alive = false;
            visible = false;
        } else currentDistance += speed;
    }


    @Override
    protected void collisionLogic() {

        for (Entity entity: getTouchingEntities()) {
            // Do damage to other entities at the moment the collision starts only.
            if (!lastTouching.contains(entity)) {
                // Do damage to colliding entities. If canDamagePlayer == true, can only damage player. Else, it can only damage other Creatures
                if ((canDamagePlayer && entity == Player.getInstance()) || (!canDamagePlayer && entity instanceof Creature)) {
                    entity.damage(damage);
                }
            }
        }

        lastTouching.clear();
        lastTouching.addAll(getTouchingEntities());
    }


}
