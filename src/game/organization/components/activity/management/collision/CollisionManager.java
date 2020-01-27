package game.organization.components.activity.management.collision;

import game.activity.controls.MouseControls;
import game.organization.components.Component;
import game.organization.World;
import game.organization.components.activity.Subsystem;
import game.organization.components.activity.management.WorldManager;
import game.organization.components.structures.Collectable;
import game.organization.components.structures.Structure;
import game.organization.components.entities.Creature;
import game.organization.components.entities.Entity;
import game.organization.components.entities.Player;
import game.organization.components.entities.Projectile;


public class CollisionManager extends WorldManager {

    CollisionChecker cChecker;

    public CollisionManager(World world) {
        super(world);
        cChecker = new CollisionChecker(world);
    }


    private void projectileCollisions(Projectile projectile) {

        for (Entity entity: cChecker.getTouchingEntities(projectile)) {
            // Do damage to other entities at the moment the collision starts only.
            if (!projectile.getLastTouching().contains(entity)) {
                // Do damage to colliding entities. If canDamagePlayer is true, damage player along with other entities. Else, it can only damage other entities.
                if ((projectile.getStats().canDamagePlayer() || !(entity instanceof Player))) {
                    entity.getStats().takeDamage(projectile.getStats().getDamage());
                }
            }
        }
        projectile.getLastTouching().clear();
        projectile.getLastTouching().addAll(cChecker.getTouchingEntities(projectile));
    }



    private void creatureCollisions(Creature creature) {
        // Establish whether creature is currently touching the player.
        creature.setTouchingPlayer(cChecker.getTouchingEntities(creature).contains(Player.getInstance()));

        // Allow doing damage to other entities continuously for the whole duration of intersection.
        for (Entity entity : cChecker.getTouchingEntities(creature)) {
            // Do damage to colliding entities. If canDamagePlayer is true, damage player along with other entities. Else, it can only damage other entities.
            if (creature.getStats().canDamagePlayer() || !(entity instanceof Player)) {
                // Damage colliding entity only if they are not clones (do not have the same name).
                // This prevents cloned creatures that are clumped together when chasing the player from killing each other.
                if (!creature.getName().equals(entity.getName())) {
                    entity.getStats().takeDamage(creature.getStats().getDamage());
                }
            }
        }
    }



    private void playerCollisions(Player player) {
        if (cChecker.isTouchingAnything(player)) {
            Structure structure = cChecker.getTopStructureTouching(player); // Use the top structure only so you can stand on something above lava to stay safe and stuff

            structure.onTouch();
            if (MouseControls.getLeftClicked()) structure.onClick();
        }
    }


    private void collectableCollisions(Collectable collectable) {
        if (cChecker.isTouchingAnything(Player.getInstance())) {
            if (cChecker.getTopStructureTouching(Player.getInstance()) == collectable) {
                collectable.randomizePos(world);
            }
        }
    }



    public void collisionLogic(Component component) {
        if (component instanceof Projectile)
            projectileCollisions((Projectile) component);
        if (component instanceof Creature)
            creatureCollisions((Creature) component);
        if (component instanceof Player) {
            playerCollisions((Player) component);}
        if (component instanceof Collectable)
            collectableCollisions((Collectable) component);

    }


}
