package lifesim.main.game.entities;

import lifesim.main.game.setting.World;
import lifesim.main.util.drawing.Sprite;
import lifesim.main.util.math.Vector2D;

public class DamageEntity extends Entity {

    private double damage;

    public DamageEntity(String name, Sprite sprite, Vector2D pos, double damage) {
        super(name, sprite, pos);
        this.damage = damage;
    }

    @Override
    public void update(World world) {

    }


    @Override
    public void collision(Entity entity) {
        if (entity instanceof HealthEntity) {
            ((HealthEntity) entity).loseHealth(damage);
        }
    }

}
