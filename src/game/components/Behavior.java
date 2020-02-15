package game.components;

public class Behavior implements IComponent {

    public enum BehaviorTypes {
        PURSURE,
        EVADE
    }

    private final BehaviorTypes behaviorType;

    public BehaviorTypes getType() {
        return behaviorType;
    }

    public Behavior(BehaviorTypes behaviorType) {
        this.behaviorType = behaviorType;
    }

}
