package lifesim.main.game.entities.components.stats;

import lifesim.main.game.entities.Entity;

public class InanimateStats implements Stats {

    @Override
    public Stats copyInitialState() {
        return new InanimateStats();
    }

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
    public void hit(double amount) {
    }


    @Override
    public Alliance getAlliance() {
        return Alliance.INANIMATE;
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
    public void onCollision(Entity owner, Entity otherEntity) {
    }

    @Override
    public void update(Entity owner) {
    }

}
