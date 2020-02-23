package game;

import game.ecs.entities.player.Player;
import game.setting.layout.Layout;


public class Game {

    private Player player = new Player();

    public Player getPlayer() {
        return player;
    }

    private Layout layout;

    public Layout getLayout() {
        return layout;
    }


    public Game(Layout layout) {
        this.layout = layout;

        player.setWorld(layout.getWorlds().get(0));
    }


    public void run() {
        player.control();
        player.getWorld().run();
    }


    public Game copyGameState() {
        return new Game(layout.copyWorldStates());
    }

}
