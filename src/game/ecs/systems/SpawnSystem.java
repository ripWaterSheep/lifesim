package game.ecs.systems;

import game.GameManager;
import game.ecs.components.*;
import game.ecs.entities.Entity;
import game.setting.World;


import static util.Geometry.*;

public class SpawnSystem extends IterativeSystem {

    private static final int MAX_ENTITIES = 50;


    public SpawnSystem(World world) {
        super(world);
    }


    @Override
    public void run(Entity entity) {
        if (world.getEntities().size() < MAX_ENTITIES) {
            doSpawning(entity);
        }
    }


    private void doSpawning(Entity entity) {
        for (PositionComponent pos: entity.getAll(PositionComponent.class)) {
            for (SpawnerComponent spawner : entity.getAll(SpawnerComponent.class)) {
                boolean doSpawning = true;

                double distanceFromPlayer = getDistanceBetween(GameManager.getPlayer().getPos(), pos);

                // If entity has AI and player is farther away than AI detection range, don't spawn.
                for (AIComponent ai: entity.getAll(AIComponent.class)) {
                    if (distanceFromPlayer > ai.getDetectionRange())
                        doSpawning = false;
                }

                //If the entity spawns a projectile and the player is out of range, don't spawn.
                for (ProjectileComponent projectile: spawner.getSpawnTemplate().getAll(ProjectileComponent.class)) {
                    if (distanceFromPlayer > projectile.getMovementRange())
                        doSpawning = false;
                }

                // Attempt to spawn copy of spawn template, setting its position (its own declared pos has precedence over spawner pos).
                Entity spawnTemplate = spawner.getSpawnTemplate();
                if (doSpawning) {
                    for (PositionComponent spawnPos: spawnTemplate.getAll(PositionComponent.class)) {
                        spawner.attemptSpawn(spawnPos.getX(), spawnPos.getY(), world);
                    }

                   if (spawnTemplate.get(PositionComponent.class) == null) {
                        spawner.attemptSpawn(pos.getX(), pos.getY(), world);
                   }
                }
            }
        }
    }


}
