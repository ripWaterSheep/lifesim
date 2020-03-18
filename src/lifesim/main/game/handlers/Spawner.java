package lifesim.main.game.handlers;

import lifesim.main.game.entities.Entity;
import lifesim.main.game.entities.components.Vector2D;

import static lifesim.main.util.math.MyMath.getRand;

public class Spawner {

    private final Entity spawnTemplate;

    public Spawner(Entity spawnTemplate) {
        this.spawnTemplate = spawnTemplate;
    }



    public void attemptSpawn(World world) {
        Vector2D worldSize = world.size;

        Vector2D spawnPos = new Vector2D(world.size);
        spawnPos.set(spawnPos.scale(getRand(-0.5, 0.5)));
        world.add(spawnTemplate.copyInitialState(), spawnPos);
    }



}
