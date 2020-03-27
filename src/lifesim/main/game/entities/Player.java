package lifesim.main.game.entities;

import lifesim.main.game.Game;
import lifesim.main.game.controls.KeyInputListener;
import lifesim.main.game.controls.KeyInputManager;
import lifesim.main.game.controls.MouseInputManager;
import lifesim.main.game.entities.components.*;
import lifesim.main.game.entities.components.stats.PlayerStats;
import lifesim.main.game.items.inventory.Inventory;
import lifesim.main.game.items.Item;
import lifesim.main.game.entities.components.sprites.Animation;
import lifesim.main.game.entities.components.sprites.DirectionalAnimatedSprite;
import lifesim.main.game.handlers.World;

import java.awt.*;

import static java.lang.Math.abs;
import static lifesim.main.game.items.AllItems.*;


public final class Player extends Entity {

    private Game game;
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
            new PlayerStats(4, 100000, 1000, 0, 0, 0));
        this.game = game;
        movement.set(0, 0);

        inventory.addItem(bread, 100);
        inventory.addItem(bomb, 100);
        inventory.addItem(waterGun, 100);
        inventory.addItem(banana, 100);
        inventory.addItem(mysteriousPill, 100);
        inventory.addItem(laserGun, 100);
        inventory.addItem(virtualCoin, 100);
        inventory.addItem(jetPack, 1000);
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
        movement.set(movement.scale(0.7));// Slow down due to friction, approaching zero.
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
        if ((left || right) && abs(movement.x) < speed) movement.setXMagnDir(speed, angle);
        if ((up || down) && abs(movement.y) < speed) movement.setYMagnDir(speed, angle);
    }


    @Override
    public void handleCollision(Entity entity) {
    super.handleCollision(entity);
        entity.whileTouching(this, getStats());
        if (MouseInputManager.left.isClicked())
            entity.onClick(this, getStats());
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
