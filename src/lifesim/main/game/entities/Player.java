package lifesim.main.game.entities;

import lifesim.main.game.Game;
import lifesim.main.game.controls.KeyInputListener;
import lifesim.main.game.controls.KeyInputManager;
import lifesim.main.game.controls.MouseInputManager;
import lifesim.main.game.entities.components.*;
import lifesim.main.game.entities.components.stats.PlayerStats;
import lifesim.main.game.items.ItemTypes;
import lifesim.main.game.items.inventory.Inventory;
import lifesim.main.game.items.Item;
import lifesim.main.game.entities.components.sprites.Animation;
import lifesim.main.game.entities.components.sprites.DirectionalAnimatedSprite;
import lifesim.main.game.handlers.World;

import java.awt.*;

import static java.lang.Math.abs;


public final class Player extends Entity {

    private final Game game;
    private World world;

    public final Inventory inventory = new Inventory();


    public Player(Game game) {
        super("Player", new DirectionalAnimatedSprite(
                new Animation("player", 200, new Vector2D(12, 16), 0),
                new Animation("player", 100, new Vector2D(12, 16), 1),
                new Animation("player", 100, new Vector2D(12, 16), 2),
                new Animation("player", 100, new Vector2D(12, 16), 3),
                new Animation("player", 100, new Vector2D(12, 16), 4)
                ),
            new PlayerStats(6, 1000, 1000, 0, 0, 0, game));
        this.game = game;
        velocity.set(0, 0);

        inventory.addItem(ItemTypes.bread, 100);
        inventory.addItem(ItemTypes.bomb, 100);
        inventory.addItem(ItemTypes.waterGun, 100);
        inventory.addItem(ItemTypes.banana, 100);
        inventory.addItem(ItemTypes.mysteriousPill, 100);
        inventory.addItem(ItemTypes.laserGun, 100);
        inventory.addItem(ItemTypes.virtualCoin, 100);
        inventory.addItem(ItemTypes.jetPack, 100);
        inventory.addItem(ItemTypes.allyTest, 100);
        inventory.addItem(ItemTypes.healer, 100);
        inventory.addItem(ItemTypes.pushTestWeapon, 100);
    }


    public Vector2D getDisplayPos() {
        return new Vector2D(0, 0);
    }


    public PlayerStats getStats() {
        return (PlayerStats) stats;
    }


    public World getWorld() {
        return world;
    }

    public void setWorld(World newWorld) {
        if (world != null) world.remove(this);
        world = newWorld;
        newWorld.add(this, new Vector2D(0, 0));
    }


    public void goTo(String name) {
        for (World world: game.getWorlds()) {
            if (world.name.equals(name)) {
                setWorld(world);
                return;
            }
            for (Entity entity: world.getEntities()) {
                if (entity.name.equals(name)) {
                    setWorld(world);
                    pos.set(entity.pos);
                    return;
                }
            }
        }
    }


    public void acquireItem(Item item, int amount) {
        inventory.addItem(item, amount);
    }


    public void controlMovement() {
        stop(); // unless
        double speed = stats.getCurrentSpeed();
        boolean up, down, left, right;
        final KeyInputListener upKey = KeyInputManager.k_w,
                leftKey = KeyInputManager.k_a,
                downKey = KeyInputManager.k_s,
                rightKey = KeyInputManager.k_d;
        // Determine intended direction based on key presses.
        // If both keys in opposite directions are pressed, then keys pressed more recently have precedence over earlier pressed keys.
        left = leftKey.isPressed();
        right = rightKey.isPressed();
        if (left && right) {
            left = leftKey.getPressTime() < rightKey.getPressTime();
            right = !left;
        }
        up = upKey.isPressed();
        down = downKey.isPressed();
        if (up && down) {
            up = upKey.getPressTime() < downKey.getPressTime();
            down = !up;
        }
        if (!(up || down || left || right)) return;
        // Get the correct angle based on combination of up/down and left/right.
        double angle = 0;
        if (up) {
            if (left) angle = 45;
            else if (right) angle = 135;
            else angle = 90;
        } else if (down) {
            if (left) angle = 315;
            else if (right) angle = 225;
            else angle = 270;
        } else if (left) angle = 0;
        else if (right) angle = 180;
        // Move along x or y axis only if needed.
        if ((left || right) && abs(velocity.x) < speed) velocity.setXMagDir(speed, angle);
        if ((up || down) && abs(velocity.y) < speed) velocity.setYMagDir(speed, angle);
    }


    @Override
    public void handleCollision(Entity entity, World world) {
    super.handleCollision(entity, world);
        entity.eventWhileTouching(game, this, getStats());
        if (MouseInputManager.left.isClicked())
            entity.eventOnClick(game, this, getStats());
    }

    @Override
    public void update(World world) {
        super.update(world);
        controlMovement();
        inventory.doGarbageCollection();
        inventory.control(world, this, getStats());
    }


    @Override
    public void render(Graphics2D g2d) {
        super.render(g2d);
        //g2d.translate(inventory.getSelectedItem().sprite.getSize().x, inventory.getSelectedItem().sprite.getSize().y);
        //inventory.getSelectedItem().render(g2d, getDisplayPos());
    }
}
