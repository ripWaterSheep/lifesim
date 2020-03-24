package lifesim.main.game;

import lifesim.main.game.controls.KeyInputManager;
import lifesim.main.game.controls.MouseInputManager;
import lifesim.main.game.entities.Player;
import lifesim.main.game.handlers.Layout;
import lifesim.main.game.handlers.World;
import lifesim.main.game.overlay.DeathScreen;
import lifesim.main.game.overlay.InventoryGUI;
import lifesim.main.game.overlay.Overlay;
import lifesim.main.game.overlay.StatBar;

import java.awt.*;
import java.util.ArrayList;


public final class Game {

    private Player player = new Player(this);

    private Layout layout = new Layout();

    private ArrayList<Overlay> overlays = new ArrayList<>();


    public Game(GamePanel panel) {

        player.setWorld(layout.getWorlds().get(0));

        overlays.add(new StatBar(panel, player));
        overlays.add(new DeathScreen(panel, player));
        overlays.add(new InventoryGUI(panel, player));
    }


    public Player getPlayer() {
        return player;
    }

    public ArrayList<World> getAllWorlds() {
        return layout.getWorlds();
    }


    public void update() {
        player.getWorld().update();

        for (Overlay overlay: overlays) {
            overlay.update();
        }
    }


    public void render(Graphics g) {
        player.getWorld().render(g);

        for (Overlay overlay: overlays) {
            overlay.render((Graphics2D) g.create());
        }
    }

}
