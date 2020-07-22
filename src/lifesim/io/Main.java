package lifesim.io;

import lifesim.io.output.GameWindow;
import lifesim.game.entities.Player;
import lifesim.state.StateManager;
import lifesim.util.fileIO.FontLoader;


public class Main {

    private static StateManager stateManager;

    public static Player getCurrentPlayer() {
        return stateManager.getCurrentPlayer();
    }


    public static void main(String[] args) {
        GameWindow window = new GameWindow();
        FontLoader.init();
        stateManager = new StateManager(window);
    }

}
