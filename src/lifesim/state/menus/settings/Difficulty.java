package lifesim.state.menus.settings;

/** Buffs or weakens enemies and changes their spawn rate according to difficulty set in settings. */
public enum Difficulty {

    SMOOTH(0.6, 1.5),
    MEDIUM(1, 1),
    INTENSE(1.5, 0.66),
    GOOD_LUCK(2, 0.33);


    public final double damageMultiplier;
    public final double spawnInterval;

    Difficulty(double damageMultiplier, double spawnInterval) {
        this.damageMultiplier = damageMultiplier;
        this.spawnInterval = spawnInterval;
    }

}
