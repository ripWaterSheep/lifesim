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

        inventory.addItem(new Weapon("Player Weapon", new Sprite("bread"), new Sprite("bread"),
                10, 15, 100, false), 100);

        inventory.addItem(new Weapon("Bomb", new Sprite("bomb"),
                new AnimatedSprite(new Animation(60, "bomb_1", "bomb_2", "bomb_3", "bomb_4", "bomb_5", "bomb_6")),
                15, 0, 3, true), 100);

        inventory.addItem(new Weapon("Water Gun", new Sprite("water_gun"),
                new Sprite("water_droplet"),
                1, 10, 50, false), 100);
    }


    public Vector2D getDisplayPos() {
        return new Vector2D(0, 0);
    }


    public World getWorld() {
        return world;
    }

    public void setWorld(World newWorld) {
        world = newWorld;
        newWorld.add(this, new Vector2D(0, 0));
    }


    public void acquireItem(Item item, int amount) {
        inventory.addItem(item, amount);
    }


    public void controlMovement() {
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
        double frictionDecelerationFactor = 0.85;
        if (left || right) movement.setXMagnDir(getIntendedSpeed(), angle);
        else movement.x *= frictionDecelerationFactor;
        if (up || down) movement.setYMagnDir(getIntendedSpeed(), angle);
        else movement.y *= frictionDecelerationFactor;
    }


    private double getIntendedSpeed() {
        double speed = defaultSpeed;
        // Base speed on current energy level.
        speed *= ((PlayerStats) stats).getEnergy()/1000;
        speed += 0.6;

        if (KeyInputManager.k_space.isPressed()) {
            speed *= 1.5;
        }
        return speed;
    }



    public void controlInventory() {
        Item selectedItem = inventory.getSelectedItem();
        if (MouseInputManager.right.isClicked()) {
            selectedItem.onClick(world, this);
            inventory.depleteSelectedItem();
        }
        selectedItem.whileHolding(world, this);

        if (KeyInputManager.k_q.isTyped()) {
            // Drop the item behind player so it isn't picked back up when moving forward.
            if (movement.getMagnitude() < 0.5) inventory.dropStackInWorld(world, pos.translate(0, 25));
            else inventory.dropStackInWorld(world, pos.translate(movement.scale(-3)));
        }
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
}
