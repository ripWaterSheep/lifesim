package lifesim.game.entities.stats;

import java.awt.*;


/** Determines which entities that the owner may attack or be attacked by, or which entities an entity's AI targets. */
public enum Alliance {

    PLAYER(Color.GREEN), // Can attack or be attacked by entities on enemy alliance.
    ENEMY(Color.RED), // Can attack or be attacked by entities on player alliance.
    NEUTRAL(Color.GRAY); // Can attack but cannot be attacked on any alliance.


    Alliance(Color teamColor) {
        this.teamColor = teamColor;
    }

    public boolean opposes(Alliance otherAlliance) {
        return (!equals(otherAlliance) || equals(NEUTRAL)) && !otherAlliance.equals(NEUTRAL);
    }

    public final Color teamColor;

}
