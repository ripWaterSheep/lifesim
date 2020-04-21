package lifesim.game.entities.types;

import lifesim.game.entities.Entity;
import lifesim.game.entities.Projectile;
import lifesim.game.entities.components.stats.Alliance;

public interface Launchable {

    Projectile launchEntity(Entity owner, Alliance alliance, double angle);

}
