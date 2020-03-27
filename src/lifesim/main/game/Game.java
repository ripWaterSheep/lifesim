package lifesim.main.game;

import lifesim.main.game.controls.KeyInputManager;
import lifesim.main.game.entities.Player;
import lifesim.main.game.entities.components.stats.PlayerStats;
import lifesim.main.game.entities.components.stats.Stats;
import lifesim.main.game.handlers.Layout;
import lifesim.main.game.handlers.World;
import lifesim.main.game.overlay.DeathScreen;
import lifesim.main.game.overlay.InventoryGUI;
import lifesim.main.game.overlay.Overlay;
import lifesim.main.game.overlay.StatBar;

import java.awt.*;
import java.util.ArrayList;



public final class Game {

    private GamePanel panel;
    private Layout layout = new Layout();
    private Player player = new Player(this);

    private ArrayList<Overlay> overlays = new ArrayList<>();


    public Game(GamePanel panel) {
        this.panel = panel;
        player.setWorld(layout.getWorlds().get(0));

        overlays.add(new StatBar(panel, player));
        overlays.add(new DeathScreen(panel, player));
        overlays.add(new InventoryGUI(panel, player));
    }


    public Player getPlayer() {
        return player;
    }

    public ArrayList<World> getWorlds() {
        return layout.getWorlds();
    }



    public void update() {
        player.getWorld().update();

        for (Overlay overlay: overlays) {
            overlay.update();
        }
        cheatLogic();
    }


    public void render(Graphics g) {
        player.getWorld().render(g, panel);

        for (Overlay overlay: overlays) {
            overlay.render((Graphics2D) g.create());
        }
    }


    /**
     * Make player go to the next world declared in the used com.lifesim.main.game layout
     */
    private void cycleWorlds(int index) {
        ArrayList<World> allWorlds = layout.getWorlds();
        World newWorld = player.getWorld();
        int currentIndex = allWorlds.indexOf(newWorld);

        try { // Increment or decrement(if negative) current index and set player world to world at new index.
            newWorld = allWorlds.get(currentIndex + index);
        } catch (IndexOutOfBoundsException e) {
            if (index > 0) newWorld  = allWorlds.get(index - 1); // Wraparound to the first world
            else if (index < 0) newWorld = allWorlds.get(allWorlds.size() + index); // Wraparound to the last world
        }

        player.setWorld(newWorld);
    }


    private void cheatLogic() {
        PlayerStats stats = player.getStats();

        if (KeyInputManager.k_shift.isPressed()) {
            if (KeyInputManager.k_n.isClicked()) cycleWorlds(1);
            if (KeyInputManager.k_b.isClicked()) cycleWorlds(-1);
            if (KeyInputManager.k_1.isPressed()) stats.gainHealth(10);
            if (KeyInputManager.k_2.isPressed()) stats.energize(10);
            if (KeyInputManager.k_3.isPressed()) stats.strengthen(10);
            if (KeyInputManager.k_4.isPressed()) stats.gainMoney(10);
            if (KeyInputManager.k_5.isPressed()) stats.gainIntellect(10);
            if (KeyInputManager.k_k.isClicked()) stats.loseHealth(10000);
            if (KeyInputManager.k_r.isClicked()) panel.newGame();
        }
    }



}
