package lifesim.main.game;


import lifesim.main.game.entities.Entity;
import lifesim.main.game.entities.player.Player;
import lifesim.main.game.setting.Layout;
import lifesim.main.game.setting.World;

import java.awt.*;
import java.util.ArrayList;

public final class GameSession {

    private Player player = new Player();

    public Player getPlayer() {
        return player;
    }

    private Layout layout;


    public ArrayList<World> getAllWorlds() {
        return layout.getWorlds();
    }



    public GameSession(Layout layout) {
        this.layout = layout;

        player.setWorld(layout.getWorlds().get(0));
    }


    public void update() {
        player.getWorld().update();
    }


    public void render(Graphics g2d) {
        player.getWorld().render(g2d);
    }

}
