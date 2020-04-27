package lifesim.state.menus.settings;

/** Buffs or weakens enemies and changes their spawn rate according to difficulty set in settings. */
public enum Difficulty {

    SMOOTH(0.6, 0.75),
    MEDIUM(1, 1),
    INTENSE(1.5, 1.25),
    GOOD_LUCK(2, 1.5);


    public final double damageMultiplier;
    public final double spawnRate;

    Difficulty(double damageMultiplier, double spawnRate) {
        this.damageMultiplier = damageMultiplier;
        this.spawnRate = spawnRate;
    }

}
