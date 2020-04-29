package lifesim.game.entities.stats;

import lifesim.game.entities.Entity;
import lifesim.game.handlers.World;
import lifesim.util.geom.Vector2D;

import java.awt.*;


public class InanimateStats implements Stats {

    @Override
    public boolean isAlive() {
        return true;
    }


    @Override
    public boolean hasHealth() {
        return false;
    }

    @Override
    public void heal(double amount) {
    }

    @Override
    public void takeDamage(double amount) {
    }


    @Override
    public Alliance getAlliance() {
        return Alliance.NEUTRAL;
    }

    @Override
    public void onCollision(Entity entity, Entity otherEntity) {
    }

    @Override
    public void update(Entity entity, World world) {
    }

    @Override
    public void renderInfo(Graphics2D g2d, Vector2D pos) {

    }

}
