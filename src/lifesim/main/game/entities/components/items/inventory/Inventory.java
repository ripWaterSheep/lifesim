package lifesim.main.game.entities.components.items.inventory;

import lifesim.main.game.entities.Player;
import lifesim.main.game.entities.components.Vector2D;
import lifesim.main.game.entities.components.items.AllItems;
import lifesim.main.game.entities.components.items.Item;
import lifesim.main.game.overlay.InventoryGUI;

import java.util.ArrayList;

import static lifesim.main.util.math.MyMath.getRand;

public class Inventory {

    private final ArrayList<InventoryStack> stacks = new ArrayList<>();

    private InventoryStack selectedStack;


    public ArrayList<InventoryStack> getStacks() {
        return new ArrayList<>(stacks);
    }

    public Inventory() {
        doGarbageCollection();
    }


    public void addItem(Item item, int amount) {
        for (InventoryStack stack: stacks) {
            if (stack.getItem() == item) {
                stack.changeAmountBy(amount);
                break;
            }
        }

        // Set the position to be randomly located inside inventory bounds
        stacks.add(new InventoryStack(item, amount, InventoryGUI.inventoryBounds.translate(item.sprite.size.scale(-0.5)).getRandInAbsBounds()));
    }


    public void depleteItem(Item item, int amount) {
        for (InventoryStack stack: stacks) {
            if (stack.getItem() == item) {
                stack.changeAmountBy(-amount);
                if (stack.getAmount() > 0)
                    break;
            }
        }

        doGarbageCollection();
    }


    public void bringStackToFront(InventoryStack theStack) {
        stacks.removeIf(stack -> stack.equals(theStack));
        stacks.add(theStack);
    }


    public void selectStack(InventoryStack stack) {
        selectedStack = stack;
    }

    public InventoryStack getSelectedStack() {
        return selectedStack;
    }


    public Item getSelectedItem() {
        return selectedStack.getItem();
    }


    public void doGarbageCollection() {
        stacks.removeIf(stack -> stack.getAmount() <= 0);

        if (selectedStack == null)
            selectedStack = new InventoryStack(AllItems.empty, 0, new Vector2D(0, 0));
    }


}
