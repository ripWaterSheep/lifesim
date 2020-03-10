package com.lifesim.main.game;

import com.lifesim.main.game.ecs.entities.player.Player;
import com.lifesim.main.game.setting.Layout;
import com.lifesim.main.game.setting.World;

import java.util.ArrayList;

public class GameManager {

    static Game currentGame;


    public static Game getCurrentGame() {
        return currentGame;
    }

    public static Player getPlayer() {
        return currentGame.getPlayer();
    }


    public static void startNew() {
        currentGame = new Game(new Layout());
    }


    public static ArrayList<World> getAllWorlds() {
       return currentGame.getLayout().getWorlds();
    }


}
