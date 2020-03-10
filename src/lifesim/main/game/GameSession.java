package lifesim.main.game;

import lifesim.main.game.ecs.entities.player.Player;
import lifesim.main.game.setting.Layout;
import lifesim.main.game.setting.World;

import java.util.ArrayList;


public class GameSession {

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


    public void run() {
        player.run();
        player.getWorld().run();
    }

}
