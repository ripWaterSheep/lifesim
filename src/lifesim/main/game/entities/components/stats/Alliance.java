package lifesim.main.game.entities.components.stats;

/** Determines which entities that the owner may attack or be attacked by, or which entities an entity's AI targets. */
public enum Alliance {

    PLAYER, // Can attack or be attacked by entities on enemy alliance.
    ENEMY, // Can attack or be attacked by entities on player alliance.
    NEUTRAL; // Can attack but cannot be attacked on any alliance.

    public boolean opposes(Alliance otherAlliance) {
        return (!equals(otherAlliance) || equals(NEUTRAL)) && !otherAlliance.equals(NEUTRAL);
    }

}
