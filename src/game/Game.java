package game;

import game.ECS.entities.Player;
import game.setting.layout.DefaultLayout;
import game.setting.layout.Layout;
import game.ECS.systems.PlayerInputSystem;
import game.setting.world.World;

public class Game {

    private static long currentFrame = 0;

    public static long getCurrentFrame() {
        return currentFrame;
    }

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
        currentFrame++;
        playerInputSystem.run(player);
        player.getWorld().run();
    }

}
