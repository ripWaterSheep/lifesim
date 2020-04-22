package lifesim.game.display;

public abstract class ToggleableDisplay extends GameDisplay {

    private boolean open = false;

    public void open() {
        open = true;
    }

    public void close() {
        open = false;
    }

    public void toggle() {
        open = !open;
    }

    @Override
    public boolean isOpen() {
        return open;
    }

}
