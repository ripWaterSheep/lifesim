package game.ECS.systems;

import game.ECS.components.PositionComponent;
import game.ECS.components.SpawnerComponent;
import game.ECS.entities.Entity;
import game.ECS.entities.Player;


import static util.Geometry.*;

public class SpawnSystem implements ISystem {

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
                if (getDistanceBetween(Player.getInstance().get(PositionComponent.class), pos) < spawner.getActiveRange()) {
                    spawner.attemptSpawn(pos.getX(), pos.getY(), entity.getWorld());
                }
            }
        }
    }


}
