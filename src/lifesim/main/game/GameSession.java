package lifesim.main.game;


import lifesim.main.game.entities.Player;
import lifesim.main.game.handlers.Layout;
import lifesim.main.game.handlers.World;

import java.awt.*;
import java.util.ArrayList;

public final class GameSession {

    private Player player = new Player();

    private Layout layout;


    public GameSession(Layout layout) {
        this.layout = layout;

        player.setWorld(layout.getWorlds().get(0));
    }


    public Player getPlayer() {
        return player;
    }

    public ArrayList<World> getAllWorlds() {
        return layout.getWorlds();
    }


    public void update() {
        player.getWorld().update();
    }


    public void render(Graphics g) {
        player.getWorld().render(g);
    }

}
