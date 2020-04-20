package lifesim.main.game.entities.components.stats;

import lifesim.main.game.entities.Entity;
import lifesim.main.game.handlers.World;


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
        return Alliance.INANIMATE;
    }

    @Override
    public String getInfo() {
        return "";
    }

    @Override
    public void onCollision(Entity entity, Entity otherEntity) {
    }

    @Override
    public void update(Entity entity, World world) {
    }

}
