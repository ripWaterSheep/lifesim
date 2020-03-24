package lifesim.main.game.entities.components.items;

import lifesim.main.game.controls.MouseInputManager;
import lifesim.main.game.entities.Player;
import lifesim.main.game.entities.Projectile;
import lifesim.main.game.entities.components.Alliance;
import lifesim.main.game.entities.components.Vector2D;
import lifesim.main.game.entities.components.sprites.AnimatedSprite;
import lifesim.main.game.entities.components.sprites.Animation;
import lifesim.main.game.entities.components.sprites.Sprite;
import lifesim.main.game.entities.components.stats.DamageStats;
import lifesim.main.game.entities.components.stats.PlayerStats;
import lifesim.main.game.handlers.World;

import java.awt.*;
import static lifesim.main.util.math.MyMath.getRand;
import static lifesim.main.util.math.MyMath.getRandInt;


public class AllItems {

    public static final Item empty = new Item("", new Sprite(0, 0, Color.BLACK));


    /**
     * Weapons
     */

    private static Animation boom = new Animation("boom",40, new Vector2D(16, 16), 0);


    public static final Weapon waterGun = new Weapon("Water Gun",
            new AnimatedSprite(new Animation("weapons", 200, new Vector2D(8, 8), 2)),
        new Projectile("Water", new Sprite(2, 2, new Color(50, 80, 200, 150)), new DamageStats(1, Alliance.PLAYER, true),
            12, 100, false));

    public static final Weapon laserGun = new Weapon("Laser Gun", new AnimatedSprite(
            new Animation("weapons", 300, new Vector2D(8, 8), 3)),
        new Projectile("Laser", new Sprite(10, 1, new Color(255, 0, 25, 150)),
            new DamageStats(3, Alliance.PLAYER, true), 35, 100, true, new Animation(boom)));


    public static final Weapon bomb =  new Weapon("Bomb", new AnimatedSprite(
            new Animation("weapons", 120, new Vector2D(8, 8), 0)),
        new Projectile("Bomb", new AnimatedSprite(new Animation("bomb", 75, new Vector2D(9, 16), 0)),
            new DamageStats(25, Alliance.PLAYER, true), 0, 5, false, new Animation(boom)));


    /**
     * Consumables
     */

    public static final Consumable bread = new Consumable("Bread", new AnimatedSprite(
            new Animation("consumables", 200, new Vector2D(8, 8), 0))) {
        @Override
        public void consume(Player player, PlayerStats stats) {
            stats.energize(100);
        }
    };

    public static final Consumable banana = new Consumable("Bread", new AnimatedSprite(
            new Animation("consumables", 200, new Vector2D(8, 8), 1))) {
        @Override
        public void consume(Player player, PlayerStats stats) {
            stats.energize(250);
        }
    };


    public static final Consumable coin = new Consumable("Coin",  new AnimatedSprite(
            new Animation("consumables", 120, new Vector2D(8, 8), 3))) {
        @Override
        public void consume(Player player, PlayerStats stats) {
            stats.gainMoney(getRand(-100, 100));
        }
    };

    public static final Consumable mysteriousPill = new Consumable("Mysterious Pill",
            new AnimatedSprite(new Animation("consumables", 300, new Vector2D(8, 8), 2))) {
        @Override
        public void consume(Player player, PlayerStats stats) {
            int randStat = getRandInt(1, 5);
            double amount = getRand(-500, 300);

            switch (randStat) {
                case 1: stats.gainHealth(amount); break;
                case 2: stats.energize(amount); break;
                case 5: stats.gainIntellect(amount); break;
            }
        }
    };

    public static final Consumable jetPack = new Consumable("Jet Pack", new Sprite(8, 8, Color.GRAY)) {
        @Override
        public void consume(Player player, PlayerStats stats) {
            player.movement.setMagnDir(150, 180+MouseInputManager.right.getAngleFromCenter());
        }
    };

}
