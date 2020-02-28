package game.ecs.components;


public interface CopyableComponent {

    /** Allows instances of implemented class to duplicate their initial state.
     * This enabled entities to spawn based on an original template by copying all components into a new entity.
     */
    CopyableComponent copyInitialState();

}
