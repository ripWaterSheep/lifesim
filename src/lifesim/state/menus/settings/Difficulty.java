package lifesim.state.menus.settings;

public enum Difficulty {

    SMOOTH(0.6, 0.8),
    MEDIUM(1, 1),
    INTENSE(1.5, 1.3),
    GOOD_LUCK(2, 1.7);


    public final double enemyDamageMultiplier;
    public final double enemySpeedMultiplier;

    Difficulty(double enemyDamageMultiplier, double enemySpeedMultiplier) {
        this.enemyDamageMultiplier = enemyDamageMultiplier;
        this.enemySpeedMultiplier = enemySpeedMultiplier;
    }

}
