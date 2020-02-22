package game.ecs.components;


import game.ecs.entities.player.Player;

public class InteractionComponent implements CopyableComponent {

    /** This void needs to be overriden through an anonymous class when added to an entity when calling entity.add(InteractionComponent) */
    public void interact(StatsComponent stats) {}

    public void teleport(Player player) {}


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
