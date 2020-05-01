package lifesim.game.display;

public abstract class ToggleableOverlay extends Overlay {

    private boolean showing = false;

    public void show() {
        if (!showing) start();
        showing = true;
    }

    public void hide() {
        showing = false;
    }

    public void toggle() {
        showing = !showing;
        if (showing) start();
    }

    @Override
    public boolean isShowing() {
        return showing;
    }

    public abstract void start();
}
