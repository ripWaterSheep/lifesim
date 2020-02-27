package game;

import game.ecs.entities.player.Player;
import game.setting.layout.DefaultLayout;
import game.setting.world.World;

import java.util.ArrayList;

public class GameManager {

    static Game currentGame;
    private static Game savedGame;
    private static World playerWorldAtSave;


    public static Game getCurrentGame() {
        return currentGame;
    }

    public static Player getPlayer() {
        return currentGame.getPlayer();
    }


    public static void startNew() {
        currentGame = new Game(new DefaultLayout());
        savedGame = new Game(new DefaultLayout());
        playerWorldAtSave = savedGame.getLayout().getWorlds().get(0);
    }

    public static void saveGame() {
        if (!currentGame.copyGameState().equals(savedGame.copyGameState())) {
            savedGame = currentGame.copyGameState();
            playerWorldAtSave = getPlayer().getWorld();
        }
    }

    public static void startFromLastSave() {
        currentGame = savedGame;
        getPlayer().setWorld(currentGame.getLayout().getWorld(playerWorldAtSave.getName()));
        saveGame();
        System.out.println(currentGame +"  "+savedGame);
    }


    public static ArrayList<World> getAllWorlds() {
       return currentGame.getLayout().getWorlds();
    }


}
