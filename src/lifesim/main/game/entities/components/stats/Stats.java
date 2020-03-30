package lifesim.main.game.entities.components.stats;

import lifesim.main.game.entities.Entity;

public interface Stats {

    Stats copyInitialState();

    double getCurrentSpeed();

    boolean isAlive();

    double getHealth();

    void heal(double amount);

    void hit(double amount);

    Alliance getAlliance();

    void buffSpeed(double multiplier);

    void buffProtection(double multiplier);

    void buffDamage(double multiplier);

    void onCollision(Entity owner, Entity otherEntity);

    void update(Entity owner);
}
