package game;

import game.ECS.entities.Player;
import game.setting.layout.Layout;
import game.ECS.systems.PlayerInputSystem;
import game.setting.world.World;

public class Game {

    PlayerInputSystem playerInputSystem = new PlayerInputSystem();
    private Player player = new Player();

    private Layout layout;

    public Layout getLayout() {
        return layout;
    }


    public Game(Layout layout) {
        this.layout = layout;

        for (World world: layout.getWorlds()) {
            // Ensures player can interact with other entities no matter what world is currently in use.
            world.add(player);
        }
        player.setWorld(layout.getWorlds().get(0));
    }


    public void run() {
        playerInputSystem.run(player);
        player.getWorld().run();
    }

}
