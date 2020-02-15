package game.components;


public abstract class StatInteraction implements IComponent {

    /** This void needs to be overriden through an anonymous class when added to an entity when calling entity.add(StatInteraction) */
    public abstract void interact(Stats stats);

}
