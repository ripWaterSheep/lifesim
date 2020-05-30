package lifesim.state;

import lifesim.game.entities.Player;
import lifesim.game.entities.stats.PlayerStats;
import lifesim.game.handlers.Layout;
import lifesim.game.handlers.MyLayout;
import lifesim.game.handlers.World;
import lifesim.engine.input.KeyInput;
import lifesim.game.items.ItemType;
import lifesim.game.overlay.*;
import lifesim.engine.output.GameWindow;
import lifesim.state.GameState;
import lifesim.state.StateManager;
import lifesim.util.geom.Vector2D;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static lifesim.engine.output.GamePanel.HEIGHT;
import static lifesim.engine.output.GamePanel.WIDTH;
import static lifesim.util.GraphicsMethods.createGraphics;


public class Game implements GameState {

    private final Layout layout = new MyLayout(this);
    private final Player player;

    private final List<Overlay>     overlays = new ArrayList<>();
    private final MessageDisplay    messageDisplay;
    private final ToggleableOverlay inventoryGUI;
    private final ToggleableOverlay deathScreen;

    private boolean canBePaused = true;

    private final StateManager stateManager;


    public Game(GameWindow window, StateManager stateManager) {
        this.stateManager = stateManager;
        layout.init();
        player = new Player(this, layout.getWorlds().get(0));

        overlays.add(new StatBar(player));
        messageDisplay = new MessageDisplay(6, Color.WHITE, new Vector2D(WIDTH/2.0, HEIGHT/2.0 -player.getHitbox().height));
        overlays.add(messageDisplay);

        overlays.add(new Hotbar(player, window));
        inventoryGUI = new InventoryGUI(player, window);
        overlays.add(inventoryGUI);

        deathScreen = new DeathScreen(stateManager);
        overlays.add(deathScreen);

        player.init();
    }

    public boolean canBePaused() {
        return canBePaused;
    }

    public void displayMessage(String message) {
        messageDisplay.displayMessage(message);
    }


    public Player getPlayer() {
        return player;
    }

    public List<World> getWorlds() {
        return layout.getWorlds();
    }


    private void control() {
        if (player.getStats().isAlive()) {
            if (KeyInput.k_e.isClicked()) {
                inventoryGUI.toggle();
            }
            canBePaused = !inventoryGUI.isShowing();
        } else {
            deathScreen.show();
            canBePaused = false;
        }

        PlayerStats stats = player.getStats();
        if (KeyInput.k_ctrl.isPressed()) {
            if (KeyInput.k_n.isClicked()) cycleWorlds(1);
            if (KeyInput.k_b.isClicked()) cycleWorlds(-1);
            if (KeyInput.k_1.isPressed()) stats.heal(10);
            if (KeyInput.k_2.isPressed()) stats.energize(10);
            if (KeyInput.k_3.isPressed()) stats.strengthen(10);
            if (KeyInput.k_4.isPressed()) stats.gainMoney(10);
            if (KeyInput.k_5.isPressed()) stats.gainIntellect(10);
            if (KeyInput.k_k.isClicked()) stats.takeDamage(10000);
            if (KeyInput.k_p.isClicked()) player.acquireItem(ItemType.HAMMER, 15);
            if (KeyInput.k_r.isClicked()) stateManager.newGame();
        }
    }


    /**
     * Make player go to the next or previous world declared in the layout's list of worlds
     */
    private void cycleWorlds(int index) {
        List<World> allWorlds = getWorlds();
        World newWorld = player.getWorld();
        int currentIndex = allWorlds.indexOf(newWorld);

        try { // Increment or decrement(if negative) current index and set player world to world at new index.
            newWorld = allWorlds.get(currentIndex + index);
        } catch (IndexOutOfBoundsException e) {
            if (index > 0)
                newWorld  = allWorlds.get(index - 1); // Wraparound to the first world
            else if (index < 0)
                newWorld = allWorlds.get(allWorlds.size() + index); // Wraparound to the last world
        }

        player.setWorld(newWorld);
    }


    public void update() {
        player.getWorld().update();

        for (Overlay display : overlays) {
            if (display.isShowing()) {
                display.update();
            }
        }
        control();
    }

    @Override
    public void render(Graphics2D g2d) {
        player.getWorld().render(createGraphics(g2d));

        for (Overlay display : overlays) {
            if (display.isShowing()) {
                display.render(createGraphics(g2d));
            }
        }
    }

}
