package lifesim.main.game.entities.components;

import lifesim.main.game.entities.DroppedItem;
import lifesim.main.game.entities.Entity;
import lifesim.main.game.entities.types.Spawnable;
import lifesim.main.game.handlers.World;
import lifesim.main.game.items.Item;
import lifesim.main.util.math.Vector2D;

import java.util.ArrayList;


/** The list of things that an entity spawns when it dies */
public class Drops {

    private final ArrayList<Spawnable> drops = new ArrayList<>();

    public Drops add(Spawnable spawnable) {
        drops.add(spawnable);
        return this;
    }


    public void dropAt(Vector2D pos, World world) {
        for (Spawnable spawnable: drops) {

            double angle = 360.0/(drops.size()) * drops.indexOf(spawnable);
            Entity entity = spawnable.spawnEntity();

            world.add(entity, pos.copy());
            entity.push(Vector2D.newMagDir(10, angle));
        }
    }

}
