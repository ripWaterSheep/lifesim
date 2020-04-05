package lifesim.main.game.overlay;

public class GameMessage {

    public GameMessage(String message, long duration) {
        this.message = message;
        this.duration = duration;
    }

    public final String message;
    public final long duration;

}
