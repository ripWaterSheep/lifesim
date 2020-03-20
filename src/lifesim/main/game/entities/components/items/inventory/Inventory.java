package lifesim.main.game.entities.components.items.inventory;

import lifesim.main.game.entities.components.Vector2D;
import lifesim.main.game.entities.components.items.AllItems;
import lifesim.main.game.entities.components.items.Item;

import java.util.ArrayList;

import static lifesim.main.util.math.MyMath.getRand;

public class Inventory {

    private ArrayList<InventoryStack> stacks = new ArrayList<>();

    private InventoryStack selectedStack = new InventoryStack(AllItems.empty, 0, new Vector2D(0, 0));


    public ArrayList<InventoryStack> getStacks() {
        return new ArrayList<>(stacks);
    }



    public void addItem(Item item, int amount) {
        for (InventoryStack stack: stacks) {
            if (stack.getItem() == item) {
                stack.changeAmountBy(amount);
                break;
            }
        }

        stacks.add(new InventoryStack(item, amount, new Vector2D(getRand(1, 40), getRand(10, 52))));
    }


    public void doGarbageCollection() {
        stacks.removeIf(stack -> stack.getAmount() <= 0);
    }


}
