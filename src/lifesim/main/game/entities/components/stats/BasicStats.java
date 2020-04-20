package lifesim.main.game.entities.components.stats;

import lifesim.main.game.entities.Entity;
import lifesim.main.game.handlers.World;
import lifesim.main.util.math.Vector2D;

import java.awt.*;


public class BasicStats implements Stats {

    protected final double speed;
    protected final double damage;

    protected final Alliance alliance;

    public BasicStats(double speed, double damage, Alliance alliance) {
        this.speed = speed;
        this.damage = damage;
        this.alliance = alliance;
    }


    @Override
    public double getCurrentSpeed() {
        return speed;
    }

    @Override
    public boolean isAlive() {
        return true;
    }


    @Override
    public boolean hasHealth() {
        return false;
    }


    @Override
    public double getHealth() {
        return 0;
    }

    @Override
    public void heal(double amount) {
    }



    @Override
    public void takeDamage(double damage) {
    }


    @Override
    public Alliance getAlliance() {
        return alliance;
    }


    @Override
    public void onCollision(Entity entity, Entity otherEntity) {
        if (entity.canDamage(otherEntity)) {
            otherEntity.getStats().takeDamage(damage);
        }
    }


    @Override
    public void update(Entity entity, World world) {
    }

    @Override
    public void renderInfo(Graphics2D g2d, Vector2D pos) { }
}
