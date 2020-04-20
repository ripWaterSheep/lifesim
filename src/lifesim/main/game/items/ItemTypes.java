package lifesim.main.game.items;

import lifesim.main.game.entities.Player;
import lifesim.main.game.entities.components.sprites.ShapeSprite;
import lifesim.main.game.input.MouseInput;
import lifesim.main.util.math.Vector2D;
import lifesim.main.game.entities.components.sprites.AnimatedSprite;
import lifesim.main.game.entities.components.sprites.Animation;
import lifesim.main.game.entities.components.stats.PlayerStats;
import lifesim.main.game.entities.types.AllyType;
import lifesim.main.game.entities.types.ProjectileType;
import lifesim.main.game.handlers.World;

import java.awt.*;

import static lifesim.main.util.math.MyMath.getRand;
import static lifesim.main.util.math.MyMath.getRandInt;


public class ItemTypes {

    public static final Item hand = new Weapon("Hand", new ShapeSprite(0, 0, Color.BLACK), ProjectileType.FIST);


    /*************
     * Misc Items
     *************/

    public static final Item teleporter = new ClickableItem("Teleporter", new ShapeSprite(8, 8, Color.GRAY)) {
        @Override
        public void use(World world, Player player, PlayerStats stats) {
            player.push(Vector2D.newMagDir(500, player.getDisplayPos().getAngleFrom(MouseInput.getPos())));
        }
    };


    public static final Item shield = new HoldableItem("Shield", new ShapeSprite(8, 8, Color.YELLOW),
            new ShapeSprite(18, 18, new Color(255, 255, 0, 100)), 500) {
        @Override
        public void use(World world, Player player, PlayerStats stats) {
            stats.protect(0.25);
        }
    };


    /***********
     * Weapons
     **********/

    public static final Item waterGun = new Weapon("Water Gun", new AnimatedSprite(new Animation(
            "weapons", 200, new Vector2D(8, 8), 2)), ProjectileType.WATER_DROP);

    public static final Item laserGun = new Weapon("Laser Gun", new AnimatedSprite(new Animation(
            "weapons", 300, new Vector2D(8, 8), 3)), ProjectileType.LASER);

    public static final Item bomb =  new Weapon("Bomb", new AnimatedSprite(new Animation(
            "weapons", 120, new Vector2D(8, 8), 0)), ProjectileType.BOMB);

    public static final Item healer = new Weapon("Healer", new ShapeSprite(8, 8, Color.RED), ProjectileType.HEAL_ORB);

    public static final Item pushTestWeapon = new Weapon("Push Test", new ShapeSprite(8, 8,
            new Color(100, 150, 200)), ProjectileType.PUSH_TEST);


    /************
     * Utilities
     ************/

    public static final Item allyTest = new SpawnItem("Ally test", new ShapeSprite(8, 8,
            new Color(50, 100, 255)), AllyType.ALLY_TEST);

    public static final Item allyTest2 = new SpawnItem("Big Boi", new ShapeSprite(8, 8,
            new Color(113, 135, 103)), AllyType.BIG_BOI);


    /**************
     * Consumables
     **************/


    public static final Item bread = new ClickableItem("Bread", new AnimatedSprite(
            new Animation("consumables", 200, new Vector2D(8, 8), 0))) {
        @Override
        public void use(World world, Player player, PlayerStats stats) {
            stats.energize(200);
        }
    };


    public static final Item banana = new ClickableItem("Banana", new AnimatedSprite(
            new Animation("consumables", 250, new Vector2D(8, 8), 1))) {
        @Override
        public void use(World world, Player player, PlayerStats stats) {
            stats.energize(200);
        }
    };


    public static final Item mysteriousPill = new ClickableItem("Mysterious Pill",
            new AnimatedSprite(new Animation("consumables", 300, new Vector2D(8, 8), 2))) {
        @Override
        public void use(World world, Player player, PlayerStats stats) {
            int randStat = getRandInt(1, 5);
            double amount = getRand(-500, 300);

            switch (randStat) {
                case 1: stats.heal(amount); break;
                case 2: stats.energize(amount); break;
                case 5: stats.gainIntellect(amount); break;
            }
        }
    };

    public static final Item virtualCoin = new ClickableItem("Virtual Coin",  new AnimatedSprite(
            new Animation("consumables", 120, new Vector2D(8, 8), 3))) {
        @Override
        public void use(World world, Player player, PlayerStats stats) {
            stats.gainMoney(getRand(getRand(-100, -1000), getRand(100, 1000)));
        }
    };


}
