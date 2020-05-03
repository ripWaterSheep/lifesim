package lifesim.game.items.OLD;

import lifesim.game.entities.Player;
import lifesim.game.entities.types.EnemyType;
import lifesim.util.sprites.AnimatedSprite;
import lifesim.util.sprites.Animation;
import lifesim.util.sprites.ShapeSprite;
import lifesim.game.entities.stats.PlayerStats;
import lifesim.game.entities.types.AllyType;
import lifesim.game.entities.types.ProjectileType;
import lifesim.game.handlers.World;
import lifesim.input.MouseInput;
import lifesim.util.geom.Vector2D;
import lifesim.util.MyMath;

import java.awt.*;


public class ItemTypes {

    public static final OLDItem hand = new LaunchableItem("Hand", new ShapeSprite(0, 0, Color.BLACK),
            ProjectileType.FIST, -2, false);


    /*************
     * Misc Items
     *************/

    public static final OLDItem jetPack = new ClickableItem("Teleporter", new ShapeSprite(8, 8, Color.GRAY)) {
        @Override
        public void use(World world, Player player, PlayerStats stats) {
            player.push(Vector2D.newMagDir(750, MouseInput.getCursorPos().getAngleFrom(player.getDisplayPos())));
        }
    };


    public static final OLDItem shield = new PressableItem("Shield", new ShapeSprite(8, 8, Color.YELLOW),
            new ShapeSprite(18, 18, new Color(255, 255, 0, 100)), 250) {
        @Override
        public void use(World world, Player player, PlayerStats stats) {
            stats.protect(0.15);
        }
    };


    /***********
     * Weapons
     **********/

    public static final OLDItem waterGun = new LaunchableItem("Water Gun", new AnimatedSprite(new Animation("weapons",
            200, new Vector2D(0, 16), new Vector2D(8, 8))), ProjectileType.WATER_STREAM, 1, true);

    public static final OLDItem laserGun = new LaunchableItem("Laser Gun", new AnimatedSprite(new Animation("weapons",
            300, new Vector2D(0, 24), new Vector2D(8, 8))), ProjectileType.LASER, 2, true);

    public static final OLDItem bomb =  new LaunchableItem("Bomb", new AnimatedSprite(new Animation("weapons",
            120, new Vector2D(0, 0), new Vector2D(8, 8))), ProjectileType.BOMB, 0, false);

    public static final OLDItem healer = new LaunchableItem("Healer", new ShapeSprite(8, 8, Color.RED),
            ProjectileType.HEAL_SPELL, 0, true);

    public static final OLDItem physicsTest = new LaunchableItem("Throwable Wall", new ShapeSprite(8, 8,
            new Color(100, 150, 200)), ProjectileType.THROWABLE_WALL, 10, true);


    /************
     * Utilities
     ************/

    public static final OLDItem allyTest = new SpawnableItem("Ally test", new ShapeSprite(8, 8,
            new Color(50, 100, 255)), EnemyType.RANGED_1);

    public static final OLDItem allyTest2 = new SpawnableItem("Big Boi", new ShapeSprite(8, 8,
            new Color(113, 135, 103)), AllyType.BIG_BOI);


    /**************
     * Consumables
     **************/


    public static final OLDItem bread = new ClickableItem("Bread", new AnimatedSprite(
            new Animation("consumables", 200, new Vector2D(0, 0), new Vector2D(8, 8)))) {
        @Override
        public void use(World world, Player player, PlayerStats stats) {
            stats.energize(200);
        }
    };


    public static final OLDItem banana = new ClickableItem("Banana", new AnimatedSprite(
            new Animation("consumables", 250,new Vector2D(0, 8), new Vector2D(8, 8)))) {
        @Override
        public void use(World world, Player player, PlayerStats stats) {
            stats.energize(200);
        }
    };


    public static final OLDItem mysteriousPill = new ClickableItem("Mysterious Pill",
            new AnimatedSprite(new Animation("consumables", 300, new Vector2D(0, 16), new Vector2D(8, 8)))) {
        @Override
        public void use(World world, Player player, PlayerStats stats) {
            int randStat = MyMath.getRandInt(1, 5);
            double amount = MyMath.getRand(-500, 300);

            switch (randStat) {
                case 1: stats.heal(amount); break;
                case 2: stats.energize(amount); break;
                case 5: stats.gainIntellect(amount); break;
            }
        }
    };

    public static final OLDItem virtualCoin = new ClickableItem("Virtual Coin",  new AnimatedSprite(
            new Animation("consumables", 120, new Vector2D(0, 24), new Vector2D(8, 8)))) {
        @Override
        public void use(World world, Player player, PlayerStats stats) {
            stats.gainMoney(MyMath.getRand(MyMath.getRand(-100, -1000), MyMath.getRand(100, 1000)));
        }
    };


}
