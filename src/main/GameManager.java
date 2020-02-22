package main;

import game.Game;
import game.setting.layout.DefaultLayout;
import game.setting.layout.Layout;
import game.setting.world.World;

import java.util.ArrayList;

public class GameManager {

    static Game currentGame;

    public static Game getCurrentGame() {
        return currentGame;
    }

    private static void startFrom(Layout layout) {
        currentGame = new Game(layout);
    }

    public static void startNew() {
        startFrom(new DefaultLayout());
    }


    public static ArrayList<World> getAllWorlds() {
       return currentGame.getLayout().getWorlds();
    }


}
