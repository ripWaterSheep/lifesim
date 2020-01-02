package game.components.arrangement;

import game.components.player.Player;
import game.components.entities.Structure;

import static java.awt.Color.YELLOW;

public abstract class GameLayout {

    /** Player instance */
    public Player player = new Player(0, 0, 30, YELLOW);


    public abstract void playerTapLogic(Structure objectTouching);

    public abstract void playerTouchLogic(Structure objectTouching);


}
