package lifesim.game.handlers;

import lifesim.state.Game;

import java.util.ArrayList;
import java.util.List;


public abstract class Layout {

    protected final Game game;

    protected final List<World> worlds = new ArrayList<>();

    public final List<World> getWorlds() {
        return worlds;
    }

    public Layout(Game game) {
        this.game = game;
    }


    public abstract void init();
}
