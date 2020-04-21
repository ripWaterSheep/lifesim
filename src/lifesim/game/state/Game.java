package lifesim.game.state;

import lifesim.game.Main;
import lifesim.game.entities.Player;
import lifesim.game.entities.components.stats.PlayerStats;
import lifesim.game.handlers.Layout;
import lifesim.game.handlers.World;
import lifesim.game.input.KeyInput;
import lifesim.game.state.displays.*;
import lifesim.util.math.Vector2D;

import java.awt.*;
import java.util.ArrayList;


public final class Game {

    private final Layout layout = new Layout();
    private final Player player;

    private final ArrayList<RenderableDisplay> overlays = new ArrayList<>();
    private final PauseScreen pauseScreen = new PauseScreen();
    private final DeathScreen deathScreen = new DeathScreen();
    private final InventoryGUI inventoryGUI;

    private final MessageDisplay messageDisplay;

    private GameState gameState = GameState.PLAYING;

    public Game() {
        player = new Player(this);
        player.setWorld(layout.getWorlds().get(0));

        inventoryGUI = new InventoryGUI(player);

        overlays.add(new Hotbar(player));
        overlays.add(new StatBar(player));

        messageDisplay = new MessageDisplay(6, Color.WHITE, new Vector2D(0, -player.sprite.getSize().y));
        overlays.add(messageDisplay);
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


    private GameState toggleState(GameState gs1, GameState gs2) {
        if (gameState.equals(gs1))
            return gs2;
        if (gameState.equals(gs2))
            return gs1;
        return gameState;
    }


    public void manageState() {
        if (player.getStats().isAlive()) {

            if (KeyInput.k_e.isClicked()) {
                gameState = toggleState(GameState.PLAYING, GameState.INVENTORY);
            }

            if (KeyInput.k_esc.isClicked()) {
                if (gameState.isMenu) {
                       gameState = GameState.PLAYING;
                } else {
                    gameState = toggleState(GameState.PAUSED, GameState.PLAYING);
                }
            }
        } else {
            gameState = GameState.DEATH_SCREEN;
        }
    }


    public void update() {
        manageState();

        switch (gameState) {
            case PLAYING:
                for (RenderableDisplay display : overlays)
                    display.update();

                player.getWorld().update(player);
                cheatLogic();
                break;

            case PAUSED:
                pauseScreen.update();
                break;

            case INVENTORY:
                player.getWorld().update(player);
                inventoryGUI.update();
                break;


            case DEATH_SCREEN:
                deathScreen.update();
                break;

        }
    }



    public void render(Graphics g) {
        switch (gameState) {
            case PLAYING:
                player.getWorld().render(g);

                for (RenderableDisplay display : overlays)
                    display.render((Graphics2D) g.create());
                break;

            case PAUSED:
                player.getWorld().render(g);
                pauseScreen.render((Graphics2D) g.create());
                break;

            case INVENTORY:
                player.getWorld().render(g);
                inventoryGUI.render((Graphics2D) g.create());
                break;

            case DEATH_SCREEN:
                player.getWorld().render(g);
                deathScreen.render((Graphics2D) g.create());
                break;
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


    private void cheatLogic() {
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

}
