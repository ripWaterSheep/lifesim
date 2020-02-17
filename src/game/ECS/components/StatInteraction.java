package game.ECS.components;


public class StatInteraction implements IComponent {

    /** This void needs to be overriden through an anonymous class when added to an entity when calling entity.add(StatInteraction) */
    public void interact(Stats stats) {}


    @Override
    public StatInteraction copy() {
        StatInteraction outerClass = this;
        return new StatInteraction() {
            public void interact(Stats stats) {
                outerClass.interact(stats);
            }
        };
    }
}
