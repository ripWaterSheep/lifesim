package GameSession;

import GameComponents.GameComponent;
import GameComponents.Player;
import GameComponents.Structure;
import Util.Callback;

import java.awt.*;

import static java.awt.Color.DARK_GRAY;
import static java.awt.Color.YELLOW;

public abstract class GameLayout {

    /** Player instance */
    public Player player = new Player(0, 0, 35, YELLOW);


    public abstract void playerTouchInteraction(Structure objectTouching);

    public abstract void playerTapInteraction(Structure objectTouching);

    public abstract void playerPressInteraction(Structure objectTouching);


}
