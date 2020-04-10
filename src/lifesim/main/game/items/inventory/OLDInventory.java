package lifesim.main.game.items.inventory;

import lifesim.main.game.controls.KeyInput;
import lifesim.main.game.controls.MouseInput;
import lifesim.main.game.entities.DroppedItem;
import lifesim.main.game.entities.Player;
import lifesim.main.game.entities.components.Vector2D;
import lifesim.main.game.entities.components.stats.PlayerStats;
import lifesim.main.game.items.ItemTypes;
import lifesim.main.game.items.Item;
import lifesim.main.game.handlers.World;

import java.util.ArrayList;

public class OLDInventory {

    private final ArrayList<OLDItemStack> stacks = new ArrayList<>();

    private OLDItemStack selectedStack;


    public ArrayList<OLDItemStack> getStacks() {
        return new ArrayList<>(stacks);
    }

    public OLDInventory() {
        doGarbageCollection();
    }

/*
    public void addItem(Item item, int amount) {
        for (OLDItemStack stack: stacks) {
            if (stack.getItem() == item) {
                stack.changeAmountBy(amount);
                return;
            }
        }
        // Set the position to be randomly located inside inventory bounds
        OLDItemStack newStack = new OLDItemStack(item, amount, OLDInventoryGUI.inventoryBounds.copy().translate(item.sprite.getSize().scale(-0.5)).randomizeInAbsRectBounds());
        stacks.add(newStack);
        if (selectedStack == null) selectedStack = newStack;
    }*/

    public void bringStackToFront(OLDItemStack theStack) {
        stacks.removeIf(stack -> stack.equals(theStack));
        stacks.add(theStack);
    }


    public void selectStack(OLDItemStack stack) {
        selectedStack = stack;
    }

    public OLDItemStack getSelectedStack() {
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
        selectedStack = new OLDItemStack(ItemTypes.hand, 0, new Vector2D(0, 0));
    }


    public void dropStackInWorld(World world, Vector2D pos) {
        stacks.remove(selectedStack);
        world.add(new DroppedItem("Dropped " + selectedStack.getItem().name, selectedStack.getItem().sprite, selectedStack.getItem(), selectedStack.getAmount()), pos);
        doGarbageCollection();
    }


    public void control(World world, Player player, PlayerStats stats) {
        if (MouseInput.right.isClicked()) {
            getSelectedItem().use(world, player, stats);
            selectedStack.changeAmountBy(-1);
            doGarbageCollection();
        }

        if (KeyInput.k_q.isClicked()) {
            // Drop the item behind player so it isn't picked back up when moving forward.
            Vector2D dropPos = player.getPos();
            if (player.getVelocity().getMagnitude() < player.getStats().getCurrentSpeed()) dropPos.translate(0, 25);
            else dropPos.translate(player.getVelocity().scale(-5));
            dropStackInWorld(player.getWorld(), dropPos);
            //dropStackInWorld(world, player.getPos().translate(10, 10));
        }
    }


}
