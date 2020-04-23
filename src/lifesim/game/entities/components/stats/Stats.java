package lifesim.game.entities.components.stats;

import lifesim.game.entities.Entity;
import lifesim.game.handlers.World;
import lifesim.util.math.geom.Vector2D;

import java.awt.*;

public interface Stats {

    boolean isAlive();
    double getHealth();

    boolean hasHealth();
    void heal(double amount);
    void takeDamage(double amount);

    Alliance getAlliance();

    void onCollision(Entity entity, Entity otherEntity);
    void update(Entity entity, World world);
    void renderInfo(Graphics2D g2d, Vector2D pos);

}
