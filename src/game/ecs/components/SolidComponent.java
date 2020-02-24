package game.ecs.components;

public class SolidComponent implements CopyableComponent {

    private final boolean keepInside;

    public boolean willKeepInside() {
        return keepInside;
    }


    public SolidComponent(boolean keepInside) {
        this.keepInside = keepInside;
    }


    @Override
    public SolidComponent copyInitialState() {
        return new SolidComponent(keepInside);
    }

    @Override
    public SolidComponent copyCurrentState() {
        return new SolidComponent(keepInside);
    }

}
