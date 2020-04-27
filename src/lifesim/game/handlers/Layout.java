package lifesim.game.handlers;

import lifesim.game.entities.Entity;
import lifesim.game.entities.Player;
import lifesim.game.entities.SolidEntity;
import lifesim.game.entities.stats.PlayerStats;
import lifesim.game.entities.types.EnemyType;
import lifesim.state.Game;
import lifesim.util.sprites.ImageSprite;
import lifesim.util.sprites.ShapeSprite;

import java.awt.*;
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
