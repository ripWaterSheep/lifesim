package lifesim.main.game.entities.types;

import lifesim.main.game.entities.Projectile;
import lifesim.main.game.entities.components.stats.Alliance;

public interface Launchable {

    Projectile launchNew(Alliance alliance, double angle);

}
