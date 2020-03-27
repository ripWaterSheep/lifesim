package lifesim.main.game.entities.components.stats.statusEffects;

public class StatusEffect {

    private final StatusEffectType type;
    private final long duration;
    private final long startTime;


    public StatusEffect(StatusEffectType type, long duration) {
        this.type = type;
        this.duration = duration;
        startTime = System.currentTimeMillis();
    }


    public boolean isOfType(StatusEffectType type2) {
        return type.equals(type2);
    }


    public boolean isOver() {
        return System.currentTimeMillis() - startTime > duration;
    }

}
