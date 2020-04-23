package lifesim.state;

import lifesim.game.entities.Player;
import lifesim.game.entities.components.stats.PlayerStats;
import lifesim.game.handlers.Layout;
import lifesim.game.handlers.World;
import lifesim.game.input.KeyInput;
import lifesim.game.display.*;
import lifesim.state.engine.Main;
import lifesim.util.math.geom.Vector2D;

import java.awt.*;
import java.util.ArrayList;

import static lifesim.util.GraphicsMethods.createGraphics;


public final class Game implements GameState {

    private final Layout layout = new Layout();
    private final Player player;

    private final ArrayList<GameDisplay> displays = new ArrayList<>();
    private final MessageDisplay messageDisplay;
    private final ToggleableDisplay inventoryGUI;
    private final ToggleableDisplay deathScreen = new DeathScreen();

    private boolean pausable;


    public Game() {
        player = new Player(this, layout.getWorlds().get(0));

        displays.add(new StatBar(player));
        messageDisplay = new MessageDisplay(6, Color.WHITE, new Vector2D(0, -player.sprite.getSize().y));
        displays.add(messageDisplay);

        inventoryGUI = new InventoryGUI(player);
        displays.add(inventoryGUI);
        displays.add(new Hotbar(player));

        displays.add(deathScreen);
    }

    public boolean isPausable () {
        return pausable;
    }


    public Player getPlayer() {
        return player;
    }

    public ArrayList<World> getWorlds() {
        return layout.getWorlds();
    }

    public void displayCenter(String message) {
        messageDisplay.displayMessage(message);
    }


    private void control() {
        if (player.getStats().isAlive()) {
            if (KeyInput.k_e.isClicked()) {
                inventoryGUI.toggle();
            }
            pausable = true;
        } else {
            deathScreen.show();
            pausable = false;
        }

        PlayerStats stats = player.getStats();
        if (KeyInput.k_shift.isPressed()) {
            if (KeyInput.k_n.isClicked()) cycleWorlds(1);
            if (KeyInput.k_b.isClicked()) cycleWorlds(-1);
            if (KeyInput.k_1.isPressed()) stats.heal(10);
            if (KeyInput.k_2.isPressed()) stats.energize(10);
            if (KeyInput.k_3.isPressed()) stats.strengthen(10);
            if (KeyInput.k_4.isPressed()) stats.gainMoney(10);
            if (KeyInput.k_5.isPressed()) stats.gainIntellect(10);
            if (KeyInput.k_k.isClicked()) stats.takeDamage(10000);
            if (KeyInput.k_r.isClicked()) Main.newGame();
        }
    }


    /**
     * Make player go to the next or previous world declared in the layout's list of worlds
     */
    private void cycleWorlds(int index) {
        ArrayList<World> allWorlds = getWorlds();
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
        control();
        player.getWorld().update(player);


        for (GameDisplay display : displays) {
            if (display.isShowing()) {
                display.update();
            }
        }
    }

    @Override
    public void render(Graphics2D g2d) {
        player.getWorld().render(createGraphics(g2d));

        for (GameDisplay display : displays) {
            if (display.isShowing()) {
                display.render(createGraphics(g2d));
            }
        }
    }

}
