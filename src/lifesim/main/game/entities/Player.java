package lifesim.main.game.entities;

import lifesim.main.game.controls.KeyInputListener;
import lifesim.main.game.controls.KeyInputManager;
import lifesim.main.game.controls.MouseInputManager;
import lifesim.main.game.entities.components.*;
import lifesim.main.game.entities.components.items.inventory.Inventory;
import lifesim.main.game.entities.components.items.Item;
import lifesim.main.game.entities.components.items.Weapon;
import lifesim.main.game.entities.components.sprites.AnimatedSprite;
import lifesim.main.game.entities.components.sprites.Animation;
import lifesim.main.game.entities.components.sprites.DirectionalAnimatedSprite;
import lifesim.main.game.entities.components.sprites.Sprite;
import lifesim.main.game.entities.components.stats.PlayerStats;
import lifesim.main.game.handlers.World;

import java.awt.*;

import static lifesim.main.game.entities.components.items.AllItems.*;


public final class Player extends MovementEntity {

    private World world;

    public final Inventory inventory = new Inventory();


    public Player() {
        super("Player", new DirectionalAnimatedSprite(
                new Animation(200, "player_idle_1", "player_idle_2"),
                        new Animation(120, "player_right_1", "player_right_2"),
                new Animation(120, "player_forward_1", "player_forward_2"),
                        new Animation(120, "player_left_1", "player_left_2"),
                new Animation(120, "player_backward_1", "player_backward_2")
        ),
                new PlayerStats(1000, 1000, 0, 0, 0), 4);
        movement.set(0, 0);

        inventory.addItem(bread, 100);
        inventory.addItem(bomb, 100);
        inventory.addItem(waterGun, 100);
        inventory.addItem(banana, 100);
        inventory.addItem(mysteriousPill, 100);
    }


    public Vector2D getDisplayPos() {
        return new Vector2D(0, 0);
    }


    public World getWorld() {
        return world;
    }

    public void setWorld(World newWorld) {
        if (world != null) world.remove(this);
        world = newWorld;
        newWorld.add(this, new Vector2D(0, 0));
    }


    public void acquireItem(Item item, int amount) {
        inventory.addItem(item, amount);
    }


    public void controlMovement() {
        double frictionDeceleration = 0.85;
        movement.set(movement.scale(frictionDeceleration));

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
        // Get the correct angle based on combination of up, down, left, and right.
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
        // Move along x or y axis if needed, else slow down due to friction.
        if (left || right) movement.setXMagnDir(getIntendedSpeed(), angle);
        if (up || down) movement.setYMagnDir(getIntendedSpeed(), angle);
    }


    public double getIntendedSpeed() {
        double speed = defaultSpeed;
        // Base speed on current energy level.
        double energy = ((PlayerStats) stats).getEnergy();
        speed *= (energy/1200)+0.625;

        if (KeyInputManager.k_space.isPressed() && energy > 0)
            speed *= 1.4;

        return speed;
    }



    public void controlInventory() {
        inventory.control(this);
    }


    @Override
    protected void move() {
        super.move();
        controlMovement();
    }


    @Override
    public void update(World world) {
        super.update(world);
        inventory.doGarbageCollection();
        controlInventory();
    }


    @Override
    public void render(Graphics2D g2d) {
        super.render(g2d);
        //g2d.translate(inventory.getSelectedItem().sprite.size.x, inventory.getSelectedItem().sprite.size.y);
        //inventory.getSelectedItem().render(g2d, getDisplayPos());
    }
}
