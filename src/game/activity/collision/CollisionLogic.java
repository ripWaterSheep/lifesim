package game.activity.collision;

import game.components.GameComponent;
import game.components.entities.Creature;
import game.components.entities.Entity;
import game.components.entities.Projectile;
import game.components.entities.player.Player;
import game.activity.controls.MouseControls;
import game.components.structures.Collectable;
import game.components.structures.Structure;


import static game.activity.collision.CollisionCheckers.*;

public class CollisionLogic {


    public static void projectileCollisionLogic(Projectile projectile) {

        for (Entity entity: getTouchingEntities(projectile)) {
            // Do damage to other entities at the moment the collision starts only.
            if (!projectile.getLastTouching().contains(entity)) {
                // Do damage to colliding entities. If canDamagePlayer is true, damage player along with other entities. Else, it can only damage other entities.
                if (projectile.getStats().canDamagePlayer() || !(entity instanceof Player)) {
                    entity.getStats().takeDamage(projectile.getStats().getDamage());
                }
            }
        }
        projectile.getLastTouching().clear();
        projectile.getLastTouching().addAll(getTouchingEntities(projectile));
    }



    public static void creatureCollisionLogic(Creature creature) {
        // Establish whether creature is currently touching the player.
        creature.setTouchingPlayer(getTouchingEntities(creature).contains(Player.getInstance()));

        // Allow doing damage to other entities continuously for the whole duration of intersection.
        for (Entity entity : getTouchingEntities(creature)) {
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




    public static void playerCollisionLogic(Player player) {
        if (isTouchingAnyStructures(player)) {
            Structure component = getTopStructureTouching(player); // Use the top structure only so you can stand on something above lava to stay safe and stuff

            component.onTouch();
            if (MouseControls.getLeftClicked()) {
                component.onClick();
            }

        }
    }



    public static void collectableCollisionLogic(Collectable collectable) {
        if (isTouchingAnyStructures(Player.getInstance())) {
            if (getTopStructureTouching(Player.getInstance()) == collectable) {
                collectable.randomizePos();
            }
        }
    }



}
