package main;

import game.Game;
import game.setting.layout.DefaultLayout;
import game.setting.layout.Layout;

public class GameManager {

    static Game currentGame;

    public static Game getCurrentGame() {
        return currentGame;
    }

    private static void startFromLayout(Layout layout) {
        currentGame = new Game(layout);
    }

    public static void startNew() {
        startFromLayout(new DefaultLayout());
    }



}
