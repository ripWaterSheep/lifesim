package lifesim.main.game.items.inventory;

import lifesim.main.game.controls.KeyInputManager;
import lifesim.main.game.controls.MouseInputManager;
import lifesim.main.game.entities.DroppedItem;
import lifesim.main.game.entities.Player;
import lifesim.main.game.entities.components.Vector2D;
import lifesim.main.game.entities.components.stats.PlayerStats;
import lifesim.main.game.items.ItemTypes;
import lifesim.main.game.items.Item;
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
        // Set the position to be randomly located inside inventory bounds
        ItemStack newStack = new ItemStack(item, amount, InventoryGUI.inventoryBounds.translate(item.sprite.getSize().scale(-0.5)).randomizeInAbsRectBounds());
        stacks.add(newStack);
        if (selectedStack == null) selectedStack = newStack;
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

        if (selectedStack == null || !stacks.contains(selectedStack))
            selectNothing();
    }


    public void selectNothing() {
        selectedStack = new ItemStack(ItemTypes.empty, 0, new Vector2D(0, 0));
    }


    public void dropStackInWorld(World world, Vector2D pos) {
        stacks.remove(selectedStack);
        world.add(new DroppedItem("Dropped " + selectedStack.getItem().name, selectedStack.getItem().sprite, selectedStack.getItem(), selectedStack.getAmount()), pos);
        doGarbageCollection();
    }


    public void control(World world, Player player, PlayerStats stats) {
        if (MouseInputManager.right.isClicked()) {
            getSelectedItem().use(world, player, stats);
            selectedStack.changeAmountBy(-1);
            doGarbageCollection();
        }

        if (KeyInputManager.k_q.isClicked()) {
            // Drop the item behind player so it isn't picked back up when moving forward.
            Vector2D dropPos = player.pos.copy();
            if (player.movement.getMagnitude() < player.getStats().getCurrentSpeed()) dropPos.set(dropPos.translate(0, 25));
            else dropPos.set(dropPos.translate(player.movement.scale(-5)));
            dropStackInWorld(player.getWorld(), dropPos);
        }
    }


}
