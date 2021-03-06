package lifesim.game.entities.stats;

import lifesim.game.entities.Entity;
import lifesim.game.handlers.World;
import lifesim.util.geom.Vector2D;

import java.awt.*;

public interface Stats {

    boolean isAlive();

    boolean hasHealth();
    void heal(double amount);
    void takeDamage(double amount);

    Alliance getAlliance();

    void onCollision(Entity entity, Entity otherEntity);
    void update(Entity entity, World world);
    void renderInfo(Graphics2D g2d, Vector2D pos);

}
