package lifesim.main.game.entities.components.stats;

import lifesim.main.game.entities.Entity;
import lifesim.main.game.handlers.World;
import lifesim.main.util.math.Vector2D;

import java.awt.*;


public class InanimateStats implements Stats {

    @Override
    public double getCurrentSpeed() {
        return 0;
    }

    @Override
    public boolean isAlive() {
        return true;
    }


    @Override
    public double getHealth() {
        return 1;
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
