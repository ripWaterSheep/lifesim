package game.ecs.components;


import game.ecs.entities.player.Player;

public class InteractionComponent implements CopyableComponent {

    /** This void needs to be overriden through an anonymous class when added to an entity when calling entity.add(InteractionComponent) */
    public void onTouch(HealthComponent health, StatsComponent stats) {}

    public void onClick(Player player) {}


    @Override
    public InteractionComponent copyInitialState() {
        InteractionComponent outerClass = this;

        return new InteractionComponent() {
            public void onTouch(HealthComponent health, StatsComponent stats) {
                outerClass.onTouch(health, stats);
            }
        };
    }

    @Override
    public InteractionComponent copyCurrentState() {
        return copyInitialState();
    }

}
