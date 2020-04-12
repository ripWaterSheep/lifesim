package lifesim.main.game.entities.components;

import lifesim.main.game.Main;
import lifesim.main.game.entities.DroppedItem;
import lifesim.main.game.entities.Entity;
import lifesim.main.game.entities.types.Spawnable;
import lifesim.main.game.items.Item;
import lifesim.main.util.math.Vector2D;

import java.util.ArrayList;


/** The list of things that an entity spawns when it dies */
public class Drops {

    private final double money;
    private final ArrayList<Entity> entities = new ArrayList<>();


    public Drops(double money) {
        this.money = money;
    }

    public Drops dropEntity(Spawnable spawnable) {
        entities.add(spawnable.spawnNew());
        return this;
    }

    public Drops dropItem(Item item) {
        entities.add(new DroppedItem(item, 1));
        return this;
    }


    public void dropAt(Vector2D pos) {
        Main.getGame().getPlayer().getStats().gainMoney(money);
        for (Entity entity: entities) {
            double angle = 360.0/(entities.size()) * entities.indexOf(entity);
            Vector2D spawnPos = pos.copy();
            spawnPos.setMagDir(10, angle);
        }
    }

}
