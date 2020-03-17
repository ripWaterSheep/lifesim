package lifesim.main.game.entities.components.items;

import lifesim.main.game.entities.ProjectileEntity;
import lifesim.main.game.entities.TempEntity;
import lifesim.main.game.entities.components.sprites.AnimatedSprite;
import lifesim.main.game.entities.components.sprites.Animation;
import lifesim.main.game.entities.components.sprites.Sprite;
import lifesim.main.game.entities.components.Vector2D;
import lifesim.main.game.entities.components.stats.DamageStats;

import java.awt.*;

public final class AllItems {

    public static final Weapon standardGun = new Weapon("Standard Gun", new Sprite(new Vector2D(200, 30), Color.BLACK, false),
            new ProjectileEntity("Bullet", new Sprite("Bullet"),20, new DamageStats(5, true), 1000));


    public static final Weapon bomb = new Weapon("Bomb", new Sprite("bomb_1"),
            new TempEntity("Bomb", new AnimatedSprite(new Animation(70,
                    "bomb_1", "bomb_2", "bomb_3", "bomb_4", "bomb_3", "bomb_2", "bomb_1", "bomb_2", "bomb_3", "bomb_4", "bomb_5", "bomb_6")), true));

}
