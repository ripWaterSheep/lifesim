package game.ECS.components;


public interface Copyable {

    /** Allows instances of implemented class to duplicate their initial state.
     * This enabled entities to spawn based on an original template by copying all components into a new entity.
     */
    Copyable copyInitialState();

    /** Allows instances of implemented class to duplicate their current state.
     * This enables the game to save the current game state for a savepoint that the payer returns to on death..
     */
    Copyable copyCurrentState();

}
