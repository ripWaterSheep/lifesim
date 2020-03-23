package lifesim.main.game.entities.components.items;

import javafx.scene.AmbientLight;
import lifesim.main.game.entities.components.Vector2D;
import lifesim.main.game.entities.components.sprites.AnimatedSprite;
import lifesim.main.game.entities.components.sprites.Animation;
import lifesim.main.game.entities.components.sprites.Sprite;
import lifesim.main.game.entities.components.stats.PlayerStats;

import java.awt.*;

import static lifesim.main.util.math.MyMath.getRand;
import static lifesim.main.util.math.MyMath.getRandInt;


public class AllItems {

    public static final Item empty = new Item("", new Sprite(0, 0, Color.BLACK));


    public static final Weapon waterGun = new Weapon("Water Gun",
            new AnimatedSprite(new Animation("weapons", 200, new Vector2D(8, 8), 2)),
            new Sprite(2, 2, new Color(50, 80, 200, 150)),
            1, 12   , 100, true);


    public static final Weapon laserGun = new Weapon("Laser Gun",
            new AnimatedSprite(new Animation("weapons", 300, new Vector2D(8, 8), 3)),
            new Sprite(10, 1, new Color(255, 0, 21, 175)),
            3, 35, 100, true);


    public static final Weapon bomb =  new Weapon("Bomb", new AnimatedSprite(
            new Animation("weapons", 120, new Vector2D(8, 8), 0)),
            new AnimatedSprite(new Animation("bomb", 75, new Vector2D(9, 16), 0)),
            50, 0, 3, true);


    public static final Consumable bread = new Consumable("Bread", new AnimatedSprite(
            new Animation("consumables", 200, new Vector2D(8, 8), 0))) {
        @Override
        public void consume(PlayerStats stats) {
            stats.energize(100);
        }
    };

    public static final Consumable banana = new Consumable("Bread", new AnimatedSprite(
            new Animation("consumables", 200, new Vector2D(8, 8), 1))) {
        @Override
        public void consume(PlayerStats stats) {
            stats.energize(250);
        }
    };


    public static final Consumable coin = new Consumable("coin",  new AnimatedSprite(
            new Animation("consumables", 120, new Vector2D(8, 8), 3))) {
        @Override
        public void consume(PlayerStats stats) {
            stats.gainMoney(100);
        }
    };

    public static final Consumable mysteriousPill = new Consumable("Mysterious Pill",
            new AnimatedSprite(new Animation("consumables", 300, new Vector2D(8, 8), 2))) {
        @Override
        public void consume(PlayerStats stats) {
            int randStat = getRandInt(1, 5);
            double amount = getRand(-500, 300);
            switch (randStat) {
                case 1:
                    stats.gainHealth(amount);
                    break;
                case 2:
                    stats.energize(amount);
                    break;
                case 5:
                    stats.gainIntellect(amount);
                    break;
            }
        }
    };

}
