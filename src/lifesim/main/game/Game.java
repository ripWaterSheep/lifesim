package lifesim.main.game;

import lifesim.main.game.input.KeyInput;
import lifesim.main.game.display.MessageDisplay;
import lifesim.main.game.display.overlay.DeathScreen;
import lifesim.main.game.display.overlay.InventoryGUI;
import lifesim.main.game.display.overlay.StatBar;
import lifesim.main.game.entities.Player;
import lifesim.main.util.math.Vector2D;
import lifesim.main.game.entities.components.stats.PlayerStats;
import lifesim.main.game.handlers.Layout;
import lifesim.main.game.handlers.World;
import lifesim.main.game.display.RenderableDisplay;

import java.awt.*;
import java.util.ArrayList;



public final class Game {

    private final GamePanel panel;
    private final Layout layout = new Layout();
    private final Player player;

    private final ArrayList<RenderableDisplay> displays = new ArrayList<>();

    private final MessageDisplay centralDisplay;

    public Game(GamePanel panel) {
        this.panel = panel;
        player = new Player(this);
        player.setWorld(layout.getWorlds().get(0));

        displays.add(new StatBar(panel, player));
        displays.add(new DeathScreen(panel, player));
        displays.add(new InventoryGUI(panel, player));

        centralDisplay = new MessageDisplay(6, Color.WHITE, new Vector2D(0, -player.sprite.getSize().y));
        displays.add(centralDisplay);
    }


    public Player getPlayer() {
        return player;
    }

    public ArrayList<World> getWorlds() {
        return layout.getWorlds();
    }

    public void displayCenter(String message) {
        centralDisplay.displayMessage(message);
    }


    public void update() {
        for (RenderableDisplay display: displays)
            display.update();

        player.getWorld().update(player);

        cheatLogic();
    }


    public void render(Graphics g) {
        player.getWorld().render(g, panel);

        for (RenderableDisplay display: displays)
            display.render((Graphics2D) g.create());
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
            if (KeyInput.k_r.isClicked()) panel.newGame();
        }
    }



}
