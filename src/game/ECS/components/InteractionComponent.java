package game.ECS.components;


import game.ECS.entities.Player;

public class InteractionComponent implements Copyable {

    /** This void needs to be overriden through an anonymous class when added to an entity when calling entity.add(InteractionComponent) */
    public void interact(StatsComponent stats) {}

    public void playerTp(Player player) {}


    @Override
    public InteractionComponent copyInitialState() {
        InteractionComponent outerClass = this;
        return new InteractionComponent() {
            public void interact(StatsComponent stats) {
                outerClass.interact(stats);
            }
        };
    }

    @Override
    public InteractionComponent copyCurrentState() {
        return copyInitialState();
    }
}
