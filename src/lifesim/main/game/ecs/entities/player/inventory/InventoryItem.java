package lifesim.main.game.ecs.entities.player.inventory;

import lifesim.main.game.ecs.components.AppearanceComponent;
import lifesim.main.game.ecs.components.InteractionComponent;
import lifesim.main.game.ecs.entities.Entity;

public class InventoryItem extends Entity {

    public InventoryItem(String name, String imagename) {
        super(name);
        add(new AppearanceComponent(imagename));
    }


    public InventoryItem createPhysicalEntity() {
        InventoryItem newItem = (InventoryItem) copyInitialState();
        newItem.add(new InteractionComponent() {
                        @Override
                        public void onTouch() {
                            player.getInventory().addItem((InventoryItem) entity);
                            world.remove(entity);
                        }
                    }
        );
        return newItem;
    }

}
