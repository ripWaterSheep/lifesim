package lifesim.main.game.entities.components.stats;

import lifesim.main.game.entities.Entity;
import lifesim.main.game.handlers.World;

public interface Stats {

    double getCurrentSpeed();

    boolean isAlive();
    double getHealth();

    boolean hasHealth();
    void heal(double amount);
    void takeDamage(double amount);

    Alliance getAlliance();

    String getInfo();

    void onCollision(Entity entity, Entity otherEntity);
    void update(Entity entity, World world);
}
