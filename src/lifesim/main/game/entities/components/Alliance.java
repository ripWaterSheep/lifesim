package lifesim.main.game.entities.components;


/** Determines which entities that the owner may attack.
 * An entity may attack any entity as long as they are not both on player alliance or both on enemy alliance.
 */
public enum Alliance {
    PLAYER,
    ENEMY,
    NEUTRAL
}
