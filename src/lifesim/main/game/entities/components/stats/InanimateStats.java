package lifesim.main.game.entities.components.stats;

import lifesim.main.game.entities.Entity;

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
    public void buffSpeed(double multiplier) {
    }

    @Override
    public void buffProtection(double multiplier) {
    }

    @Override
    public void buffDamage(double multiplier) {
    }

    @Override
    public void onCollision(Entity entity, Entity otherEntity) {
    }

    @Override
    public void update(Entity owner) {
    }

}
