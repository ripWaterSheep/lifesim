package game;

import game.ecs.entities.player.Player;
import game.ecs.entities.player.PlayerControls;
import game.setting.layout.Layout;

public class Game {

    private Player player = new Player();

    private Layout layout;

    public Layout getLayout() {
        return layout;
    }


    public Game(Layout layout) {
        this.layout = layout;

        layout.getWorlds().get(0).add(player);
    }


    public void run() {
        player.control();
        player.getWorld().run();
    }

}
