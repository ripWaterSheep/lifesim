package lifesim.main.game.ecs.components;


import lifesim.main.game.ecs.entities.Entity;
import lifesim.main.game.ecs.entities.player.Player;
import lifesim.main.game.setting.World;

public class InteractionComponent implements CopyableComponent {

    // Access to player and its health/stats.
    protected World world;
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

    public void setWorld(World world) {
        if (this.world == null) {
            this.world = world;
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
