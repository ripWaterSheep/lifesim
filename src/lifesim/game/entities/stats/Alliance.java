package lifesim.game.entities.stats;

import java.awt.*;


/** Determines which entities that the owner may attack or be attacked by, or which entities an entity's AI targets. */
public enum Alliance {

    PLAYER(Color.GREEN), // Can attack or be attacked by entities on enemy alliance.
    ENEMY(Color.RED), // Can attack or be attacked by entities on player alliance.
    NEUTRAL(Color.GRAY), // Can attack or be attacked by anything.
    INVINCIBLE(Color.GRAY); // Can attack any other alliance but cannot be attacked or targeted.


    Alliance(Color teamColor) {
        this.teamColor = teamColor;
    }

    public boolean opposes(Alliance otherAlliance) {
        return (!equals(otherAlliance) || equals(INVINCIBLE) || equals(NEUTRAL)) && !otherAlliance.equals(INVINCIBLE);
    }

    public final Color teamColor;

}
