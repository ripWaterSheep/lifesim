package lifesim.main.game.entities.components.items.inventory;

import lifesim.main.game.entities.DroppedItemStack;
import lifesim.main.game.entities.components.Vector2D;
import lifesim.main.game.entities.components.items.AllItems;
import lifesim.main.game.entities.components.items.Item;
import lifesim.main.game.handlers.World;
import lifesim.main.game.overlay.InventoryGUI;

import java.util.ArrayList;

public class Inventory {

    private final ArrayList<ItemStack> stacks = new ArrayList<>();

    private ItemStack selectedStack;


    public ArrayList<ItemStack> getStacks() {
        return new ArrayList<>(stacks);
    }

    public Inventory() {
        doGarbageCollection();
    }


    public void addItem(Item item, int amount) {
        for (ItemStack stack: stacks) {
            if (stack.getItem() == item) {
                stack.changeAmountBy(amount);
                return;
            }
        }

        System.out.println(item +"   ");
        // Set the position to be randomly located inside inventory bounds
        stacks.add(new ItemStack(item, amount, InventoryGUI.inventoryBounds.translate(item.sprite.size.scale(-0.5)).getRandInAbsBounds()));
    }


    public void depleteSelectedItem() {
       selectedStack.changeAmountBy(-1);

        doGarbageCollection();
    }


    public void bringStackToFront(ItemStack theStack) {
        stacks.removeIf(stack -> stack.equals(theStack));
        stacks.add(theStack);
    }


    public void selectStack(ItemStack stack) {
        selectedStack = stack;
    }

    public ItemStack getSelectedStack() {
        return selectedStack;
    }


    public Item getSelectedItem() {
        return selectedStack.getItem();
    }


    public void doGarbageCollection() {
        stacks.removeIf(stack -> stack.getAmount() <= 0);

        if (selectedStack == null || !stacks.contains(selectedStack)) {
            selectNothing();
        }
    }


    public void selectNothing() {
        selectedStack = new ItemStack(AllItems.empty, 0, new Vector2D(0, 0));
    }


    public void dropStackInWorld(World world, Vector2D pos) {
        Item item = selectedStack.getItem();
        world.add(new DroppedItemStack("Dropped " + item.name, item.sprite, item, selectedStack.getAmount()), pos);
        doGarbageCollection();
        stacks.remove(selectedStack);
    }


}
