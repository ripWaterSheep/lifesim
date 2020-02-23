package game;

import game.Game;
import game.ecs.entities.player.Player;
import game.setting.layout.DefaultLayout;
import game.setting.layout.Layout;
import game.setting.world.World;

import java.util.ArrayList;

public class GameManager {

    static Game currentGame;

    private static Game savedGame;


    public static Game getCurrentGame() {
        return currentGame;
    }

    public static Player getPlayer() {
        return currentGame.getPlayer();
    }


    public static void startNew() {
        currentGame = new Game(new DefaultLayout());
    }

    public static void saveGame() {
        savedGame = currentGame.copyGameState();
    }

    public static void startFromLastSave() {
        savedGame = currentGame.copyGameState();
        currentGame = savedGame;
    }


    public static ArrayList<World> getAllWorlds() {
       return currentGame.getLayout().getWorlds();
    }


}
