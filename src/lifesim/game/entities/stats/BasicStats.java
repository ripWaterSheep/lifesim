package lifesim.game.entities.stats;

import lifesim.game.entities.Entity;
import lifesim.game.handlers.World;
import lifesim.util.geom.Vector2D;

import java.awt.*;


public class BasicStats implements Stats {

    protected final double damage;

    protected final Alliance alliance;

    public BasicStats(double damage, Alliance alliance) {
        this.damage = damage;
        this.alliance = alliance;
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
