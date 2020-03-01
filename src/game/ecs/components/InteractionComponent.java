package game.ecs.components;


import game.GameManager;
import game.ecs.entities.Entity;
import game.ecs.entities.player.Player;

public class InteractionComponent implements CopyableComponent {

    // Access to player and its health/stats.
    protected Player player;
    protected HealthComponent health;
    protected StatsComponent stats;
    protected MovementComponent movement;
    protected Entity entity;


    public void setPlayer(Player player) {
        if (this.player == null) {
            this.player = player;
            health = player.get(HealthComponent.class);
            stats = player.get(StatsComponent.class);
            movement = player.get(MovementComponent.class);
        }
    }

    public void setEntity(Entity entity) {
        if (this.entity == null) {
            this.entity = entity;
        }
    }

    /** This void needs to be overriden through an anonymous class when added to an entity when calling entity.add(InteractionComponent) */
    public void onTouch() {}

    public void onClick() {}


    @Override
    public InteractionComponent copyInitialState() {
        InteractionComponent outerClass = this;

        return new InteractionComponent() {
            @Override
            public void onTouch() {
                outerClass.onTouch();
            }
            @Override
            public void onClick() {
                outerClass.onClick();
            }
        };
    }

}
