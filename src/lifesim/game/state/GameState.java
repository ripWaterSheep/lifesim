package lifesim.game.state;

public enum GameState {

    PLAYING(false),
    PAUSED(true),
    INVENTORY(true),
    DEATH_SCREEN(false);


    public final boolean isMenu;

    GameState(boolean isMenu) {
        this.isMenu = isMenu;
    }
}
