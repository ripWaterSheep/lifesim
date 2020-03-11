package lifesim.main.game.entities;

import lifesim.main.game.setting.World;
import lifesim.main.util.drawing.Sprite;
import lifesim.main.util.math.Vector2D;

public class HealthEntity extends DamageEntity {

    private double health;
    private final double initialHealth;

    public HealthEntity(String name, Sprite sprite, Vector2D pos, double health, double damage) {
        super(name, sprite, pos, damage);
        this.health = health;
        initialHealth = health;
    }

    public void gainHealth(double amount) {
        health += amount;
    }

    public void loseHealth(double amount) {
        health -= amount;
    }


    @Override
    public void collision(Entity entity) {

    }

}
