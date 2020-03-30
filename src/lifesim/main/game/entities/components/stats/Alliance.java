package lifesim.main.game.entities.components.stats;


/** Determines which entities that the owner may attack or be attacked by.
 */
public enum Alliance {

    PLAYER, // Can attack or be attacked by entities on enemy alliance.
    ENEMY, // Can attack or be attacked by entities on player alliance.
    NEUTRAL, // Can attack or be attacked by entities on any alliance.
    PASSIVE, // Cannot attack but may be attacked.
    INANIMATE; // Cannot attack or be attacked.

    public boolean canAttack(Alliance otherAlliance) {
        return (!equals(otherAlliance) || equals(NEUTRAL)) && !equals(PASSIVE) && !equals(INANIMATE) && !otherAlliance.equals(INANIMATE);
    }

}
