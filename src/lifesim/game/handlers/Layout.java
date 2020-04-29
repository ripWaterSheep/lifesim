package lifesim.game.handlers;

import lifesim.state.Game;

import java.util.ArrayList;


public abstract class Layout {

    protected final Game game;

    protected final ArrayList<World> worlds = new ArrayList<>();

    public final ArrayList<World> getWorlds() {
        return worlds;
    }

    public Layout(Game game) {
        this.game = game;
    }


    public abstract void init();
}
