package lifesim.main.game.entities;

import lifesim.main.game.Game;
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
            new PlayerStats(8, 1000, 1000, 0, 0, 0, game));
        this.game = game;
        velocity.set(0, 0);

        /*inventory.addItem(ItemTypes.bread, 100);
        inventory.addItem(ItemTypes.waterGun, 100);
        inventory.addItem(ItemTypes.banana, 100);
        inventory.addItem(ItemTypes.mysteriousPill, 100);
        inventory.addItem(ItemTypes.virtualCoin, 100);*/
        inventory.addItem(ItemTypes.laserGun, 100);
        inventory.addItem(ItemTypes.bomb, 100);
        inventory.addItem(ItemTypes.jetPack, 100);
        inventory.addItem(ItemTypes.allyTest, 100);
        inventory.addItem(ItemTypes.allyTest2, 100);
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
        stop();
        double speed = stats.getCurrentSpeed();


        if (KeyInputManager.k_space.isPressed() && getStats().getEnergy() > 0)
            speed *= 1.45;
        if (KeyInputManager.k_shift.isPressed())
            speed *= 0.3;


        Vector2D tempVel = new Vector2D(0, 0);
        // Move according to W-A-S-D key presses;

        if (KeyInputManager.k_a.isPressed()) tempVel.translate(-speed, 0); //L
        if (KeyInputManager.k_d.isPressed()) tempVel.translate(speed, 0);  //R
        if (KeyInputManager.k_w.isPressed()) tempVel.translate(0, -speed); //U
        if (KeyInputManager.k_s.isPressed()) tempVel.translate(0, speed);  //D

        // Maintain momentum even if not currently walking in direction of momentum.
        if (abs(velocity.x) > 0 && tempVel.x == 0) tempVel.x = velocity.x;
        if (abs(velocity.y) > 0 && tempVel.y == 0) tempVel.y = velocity.y;


        // Make sure that
        tempVel.clampMagnitude(speed);
        velocity.set(tempVel);
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
