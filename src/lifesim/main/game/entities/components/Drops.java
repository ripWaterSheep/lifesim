package lifesim.main.game.entities.components;

import lifesim.main.game.entities.itemEntites.DroppedItem;
import lifesim.main.game.handlers.World;
import lifesim.main.game.items.Item;
import lifesim.main.util.math.Vector2D;

import java.util.ArrayList;


/** The list of things that an entity spawns when it dies */
public class Drops {

    private final ArrayList<DroppedItem> drops = new ArrayList<>();

    public Drops add(Item drop, int amount) {
        drops.add(drop.getDroppedEntity(amount));
        return this;
    }


    public void dropAt(Vector2D pos, World world) {
        for (DroppedItem drop: drops) {
            double angle = 360.0/(drops.size()) * drops.indexOf(drop);
            world.add(drop, pos.copy());
            drop.push(Vector2D.newMagDir(10, angle));
        }
    }

}
