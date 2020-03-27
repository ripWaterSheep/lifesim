package lifesim.main.game.items;

import lifesim.main.game.controls.MouseInputManager;
import lifesim.main.game.entities.Player;
import lifesim.main.game.entities.Projectile;
import lifesim.main.game.entities.components.stats.Alliance;
import lifesim.main.game.entities.components.Vector2D;
import lifesim.main.game.entities.components.sprites.AnimatedSprite;
import lifesim.main.game.entities.components.sprites.Animation;
import lifesim.main.game.entities.components.sprites.Sprite;
import lifesim.main.game.entities.components.stats.PlayerStats;
import lifesim.main.game.entities.components.stats.Stats;
import lifesim.main.game.handlers.World;

import java.awt.*;
import static lifesim.main.util.math.MyMath.getRand;
import static lifesim.main.util.math.MyMath.getRandInt;


public class AllItems {

    public static final Item empty = new Item("", new Sprite(0, 0, Color.BLACK));


    /*************
     * Misc Items
     *************/

    public static final Item jetPack = new Item("Jet Pack", new Sprite(8, 8, Color.GRAY)) {
        @Override
        public void onClick(World world, Player player, PlayerStats stats) {
            player.movement.setMagDir(150, 180+MouseInputManager.right.getAngleFromCenter());
        }
    };


    /***********
     * Weapons
     **********/

    private static final Animation boom = new Animation("boom",40, new Vector2D(16, 16), 0);


    public static final Weapon waterGun = new Weapon("Water Gun",
            new AnimatedSprite(new Animation("weapons", 200, new Vector2D(8, 8), 2)),
        new Projectile("Water", new Sprite(2, 2, new Color(50, 80, 220, 150)),
                new Stats(12, 10, true, 1, Alliance.PLAYER), 120, false));

    public static final Weapon laserGun = new Weapon("Laser Gun", new AnimatedSprite(
            new Animation("weapons", 300, new Vector2D(8, 8), 3)),
        new Projectile("Laser", new Sprite(10, 1, new Color(255, 0, 25, 150)),
            new Stats(20, 10, true, 3, Alliance.PLAYER), 150, true, new Animation(boom)));

    public static final Weapon bomb =  new Weapon("Bomb", new AnimatedSprite(
            new Animation("weapons", 120, new Vector2D(8, 8), 0)),
        new Projectile("Bomb", new AnimatedSprite(new Animation("bomb", 75, new Vector2D(9, 16), 0)),
            new Stats(0, 10, true, 20, Alliance.PLAYER), 5, false, new Animation(boom)));


    /**************
     * Consumables
     **************/

    @ItemInfo(type = "Consumable", lore = "An innocuous loaf of bread.", abilities = "Heals 100 energy.")
    public static final Item bread = new Item("Bread", new AnimatedSprite(
            new Animation("consumables", 200, new Vector2D(8, 8), 0))) {
        @Override
        public void onClick(World world, Player player, PlayerStats stats) {
            stats.energize(100);
        }
    };

    @ItemInfo(type = "Consumable", lore = "A good source of potassium.", abilities = "Heals 200 energy.")
    public static final Item banana = new Item("Banana", new AnimatedSprite(
            new Animation("consumables", 100, new Vector2D(8, 8), 1))) {
        @Override
        public void onClick(World world, Player player, PlayerStats stats) {
            stats.energize(200);
        }
    };

    @ItemInfo(type = "Test Item", lore = "Found in your friend's medicine cabinet probably.", abilities = "???")
    public static final Item mysteriousPill = new Item("Mysterious Pill",
            new AnimatedSprite(new Animation("consumables", 300, new Vector2D(8, 8), 2))) {
        @Override
        public void onClick(World world, Player player, PlayerStats stats) {
            int randStat = getRandInt(1, 5);
            double amount = getRand(-500, 300);

            switch (randStat) {
                case 1: stats.gainHealth(amount); break;
                case 2: stats.energize(amount); break;
                case 5: stats.gainIntellect(amount); break;
            }
        }
    };

    @ItemInfo(type = "Test Item", lore = "Very unstable economy these days.", abilities = "Gambles anywhere from 0-100$")
    public static final Item virtualCoin = new Item("Virtual Coin",  new AnimatedSprite(
            new Animation("consumables", 120, new Vector2D(8, 8), 3))) {
        @Override
        public void onClick(World world, Player player, PlayerStats stats) {
            stats.gainMoney(getRand(getRand(-100, -1000), getRand(100, 1000)));
        }
    };


}
