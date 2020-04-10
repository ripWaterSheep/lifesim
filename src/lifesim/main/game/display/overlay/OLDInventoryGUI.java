package lifesim.main.game.display.overlay;

public class OLDInventoryGUI {

/*
    private static final int GRID_SIZE = 1;

    private static final Sprite bg = new Sprite("inventory");
    private static final Sprite selectedBubble = new Sprite("selected_slot");

    // Define the edges of the inside of the inventory.
    public static final Vector2D inventoryBounds = bg.getSize().scale(0.48, 0.3).translate(0, 5);

    private static final Font detailFont = FontLoader.getMainFont(8);

    private final Inventory inventory;

    private boolean opened = false;
    private OLDItemStack draggedStack;


    public OLDInventoryGUI(GamePanel panel, Player player) {
        super(panel, player);
        this.inventory = player.inventory;
    }


    @Override
    public void update() {
        if (KeyInput.k_e.isClicked()) opened = !opened;
        if (KeyInput.k_esc.isClicked()) opened = false;

        if (opened) dragItems();
        scrollThroughItems();
    }


    private void dragItems() {
        Vector2D mousePos = MouseInput.left.getScaledPos();

        if (MouseInput.left.isClicked())  {
            for (OLDItemStack stack: inventory.getStacks()) {
                if (stack.getItem().sprite.containsPointAt(mousePos, stack.inventoryPos)) {
                    draggedStack = stack;
                    inventory.bringStackToFront(stack);
                    inventory.selectStack(stack);
                }
            }
        }
        if (draggedStack != null) {
            draggedStack.drag(mousePos, inventoryBounds);

            if (!MouseInput.left.isPressed() && draggedStack != null) {
                draggedStack.snapToGrid(GRID_SIZE);
                draggedStack = null;
            }
        }
    }


    private void scrollThroughItems() {
        if (inventory.getStacks().size() > 0) {
            ArrayList<OLDItemStack> stacks = inventory.getStacks();
            int newIndex = stacks.indexOf(inventory.getSelectedStack());
            newIndex += MouseInput.getMouseWheelSpeed();
            if (newIndex > stacks.size() - 1)
                newIndex = 0;
            if (newIndex < 0)
                newIndex = stacks.size() - 1;
            inventory.selectStack(stacks.get(newIndex));
        } else {
            inventory.selectNothing();
        }
    }



    @Override
    public void render(Graphics2D g2d) {
        if (!opened) {
            g2d.translate(panel.getScaledWidth()/2.0 - inventoryBounds.x*1.1, -panel.getScaledHeight()/2.0+ inventoryBounds.y*1.1);
            g2d.scale(0.6, 0.6);
        }
        bg.render(g2d, new Vector2D(0, 0), new Vector2D(0, 0));

        // Draw the stack selection bubble over the selected stack, and display item details under the inventory
        OLDItemStack selectedStack = inventory.getSelectedStack();
        if (selectedStack.getItem() != ItemTypes.hand) {
            selectedBubble.render(g2d, selectedStack.inventoryPos, new Vector2D(0, 0));
            selectedStack.renderDetails(g2d, inventoryBounds.copy().translate(-inventoryBounds.x, 10), detailFont);
        }

        for (OLDItemStack stack : inventory.getStacks())
            stack.render(g2d);
    }
*/
}
