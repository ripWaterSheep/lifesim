package lifesim.state.engine;

import lifesim.game.entities.Player;
import lifesim.util.fileIO.FontLoader;


public class Main {

    private static GameWindow window;
    private static StateManager stateManager;

    public static GameWindow getWindow() {
        return window;
    }

    public static Player getCurrentPlayer() {
        return stateManager.getCurrentPlayer();
    }


    public static void main(String[] args) {
        window = new GameWindow();
        FontLoader.init();
        stateManager = new StateManager(window);
    }

}
