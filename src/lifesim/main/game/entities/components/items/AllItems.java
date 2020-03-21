package lifesim.main.game.entities.components.items;

import lifesim.main.game.entities.components.Vector2D;
import lifesim.main.game.entities.components.sprites.AnimatedSprite;
import lifesim.main.game.entities.components.sprites.Animation;
import lifesim.main.game.entities.components.sprites.Sprite;
import lifesim.main.game.entities.components.stats.PlayerStats;

import java.awt.*;

import static lifesim.main.util.math.MyMath.getRand;
import static lifesim.main.util.math.MyMath.getRandInt;


public class AllItems {

    public static final Item empty = new Item("", new Sprite(new Vector2D(0, 0), Color.BLACK, false));


    public static final Weapon bomb =  new Weapon("Bomb", new Sprite("bomb"),
                new AnimatedSprite(new Animation(60, "bomb_1", "bomb_2", "bomb_3", "bomb_4", "bomb_5", "bomb_6")),
            15, 0, 3, true);

    public static final Weapon waterGun = new Weapon("Water Gun", new Sprite("water_gun"),
            new Sprite("water_droplet"), 1, 10, 50, false);


    public static final Consumable bread = new Consumable("Bread", new Sprite("bread")) {
        @Override
        public void consume(PlayerStats stats) {
            stats.energize(100);
        }
    };

    public static final Consumable banana = new Consumable("Bread", new Sprite("banana")) {
        @Override
        public void consume(PlayerStats stats) {
            stats.energize(250);
        }
    };

    public static final Consumable mysteriousPill = new Consumable("Mysterious Pill", new Sprite("mysterious_pill")) {
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
