package gamesession.game.gamecomponents.arrangement;

import gamesession.game.gamecomponents.Player;
import gamesession.game.gamecomponents.entities.Entity;

import static java.awt.Color.YELLOW;

public abstract class GameLayout {

    /** Player instance */
    public Player player = new Player(0, 0, 35, YELLOW);


    public abstract void playerTapInteraction(Entity objectTouching);

    public abstract void playerTouchInteraction(Entity objectTouching);


}
