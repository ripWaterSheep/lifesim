package game.ecs.systems;

import game.ecs.components.AIComponent;
import game.ecs.components.PositionComponent;
import game.ecs.components.ProjectileComponent;
import game.ecs.components.SpawnerComponent;
import game.ecs.entities.Entity;
import game.ecs.entities.player.Player;


import static util.Geometry.*;

public class SpawnSystem implements IterableSystem {

    private static final int MAX_ENTITIES = 50;


    @Override
    public void run(Entity entity) {
        if (entity.getWorld().getEntities().size() < MAX_ENTITIES) {
            doSpawning(entity);
        }
    }


    private void doSpawning(Entity entity) {
        for (PositionComponent pos: entity.getAll(PositionComponent.class)) {
            for (SpawnerComponent spawner : entity.getAll(SpawnerComponent.class)) {
                boolean doSpawning = true;

                double distanceFromPlayer = getDistanceBetween(Player.getInstance().getPos(), pos);

                if (distanceFromPlayer > spawner.getActiveRange()) {
                    doSpawning = false;
                }
                for (AIComponent ai: entity.getAll(AIComponent.class)) {
                    if (distanceFromPlayer > ai.getFollowDistance())
                        doSpawning = false;
                }
                for (ProjectileComponent projectile: entity.getAll(ProjectileComponent.class)) {
                    if (distanceFromPlayer > projectile.getMovementRange())
                        doSpawning = false;
                }


                if (doSpawning) {
                    spawner.attemptSpawn(pos.getX(), pos.getY(), entity.getWorld());
                }
            }
        }
    }


}
