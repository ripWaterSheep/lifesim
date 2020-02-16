package game;

import game.entities.Player;
import game.layout.DefaultLayout;
import game.layout.Layout;
import game.systems.PlayerInputSystem;
import game.world.World;

public class Game {

    PlayerInputSystem playerInputSystem = new PlayerInputSystem();
    private Player player = new Player();
    private Layout layout = new DefaultLayout();


    public Game() {
        for (World world: layout.getWorlds()) {
            // Ensures player can interact with other entities no matter what world is currently in use.
            world.add(player);
        }

        player.setWorld(layout.getWorlds().get(0));
    }

    public void run() {
        player.getWorld().run();
        playerInputSystem.run(player);
    }

}
