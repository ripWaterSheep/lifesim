package lifesim.game.entities;

import lifesim.game.entities.types.EffectType;
import lifesim.game.item.ItemType;
import lifesim.io.output.GamePanel;
import lifesim.util.sprites.Animation;
import lifesim.util.sprites.DirectionalAnimatedSprite;
import lifesim.game.handlers.World;
import lifesim.state.Game;
import lifesim.game.entities.stats.PlayerStats;
import lifesim.io.input.KeyInput;
import lifesim.io.input.MouseInput;
import lifesim.game.item.inventory.Inventory;
import lifesim.util.geom.Vector2D;

import static java.lang.Math.abs;
import static java.lang.Math.min;


public class Player extends MovementEntity {

    private final Game game;
    private World world;

    public final Inventory inventory;


    public Player(Game game, World startingWorld) {
        super("Player", new DirectionalAnimatedSprite(
                new Animation("player", 200, new Vector2D(0, 0), new Vector2D(12, 16)),
                new Animation("player", 100, new Vector2D(0, 16), new Vector2D(12, 16)),
                new Animation("player", 100, new Vector2D(0, 32), new Vector2D(12, 16)),
                new Animation("player", 100, new Vector2D(0, 48), new Vector2D(12, 16)),
                new Animation("player", 100, new Vector2D(0, 64), new Vector2D(12, 16))
                ),
            new PlayerStats(500, 500, 0, 0, 0, game), 3.5, 0);
        velocity.set(0, 0);
        this.game = game;
        setWorld(startingWorld);
        inventory = new Inventory(this, game);
    }


    public void init() {
        //acquireItem(ItemType.ADVANCED_FACTORY, 25);
        //acquireItem(ItemType.WALLBOT, 25);
        //acquireItem(ItemType.BOMB, 25);
        //acquireItem(ItemType.HAMMER, 50);
    }



    public Vector2D getDisplayPos() {
        return GamePanel.getCenterPos();
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
        newWorld.add(this, 0, 0);
    }


    public void goToWorld(String name) {
        for (World world: game.getWorlds()) {
            if (world.name.equals(name)) {
                setWorld(world);
                return;
            }
        }
        System.err.println("World " + name + " not found.");
    }

    public void goToEntity(String name) {
        for (World world: game.getWorlds()) {
            for (Entity entity : world.getEntities()) {
                if (entity.name.equals(name)) {
                    setWorld(world);
                    // Automatically go to the front part of entity's sprite if entity is 3D.
                    if (entity.shouldBeSorted()) {
                        pos.set(entity.getPos().x, entity.getHitbox().getMaxY());
                    } else { // If the entity is flat, then just go to its center.
                        pos.set(entity.getPos());
                    }
                    return;
                }
            }
        }
        System.err.println("Entity " + name + " not found.");
    }


    public void acquireItem(ItemType item, int amount) {
        inventory.addItem(item, amount);
    }


    @Override
    public void removeFromWorld() {
        super.removeFromWorld();
        // Summon some type of death effect.
        world.add(EffectType.SMALL_BOOM.spawnEntity(), getPos());
    }

    private double getCurrentSpeed() {
        double currentSpeed = defaultSpeed;
        // Speed ranges from 100% to 75% depending on energy level.
        currentSpeed *= (getStats().getEnergy() / 4000) + 0.75;
        currentSpeed = min(currentSpeed, 7);
        return currentSpeed;
    }


    private void controlMovement() {
        stop();
        double speed = getCurrentSpeed();

        if (KeyInput.k_shift.isPressed()) {
            speed *= 0.35;
        } else if (KeyInput.k_space.isPressed()) {
            speed *= 1.35;
        }

        Vector2D tempVel = new Vector2D(0, 0);
        // Move according to WASD key presses;
        if (KeyInput.k_w.isPressed()) tempVel.translate(0, -speed); //U
        if (KeyInput.k_a.isPressed()) tempVel.translate(-speed, 0); //L
        if (KeyInput.k_s.isPressed()) tempVel.translate(0, speed);  //D
        if (KeyInput.k_d.isPressed()) tempVel.translate(speed, 0);  //R

        // Maintain momentum even if not currently walking in direction of momentum.
        if (abs(velocity.x) >  0 && tempVel.x == 0) tempVel.x = velocity.x;
        if (abs(velocity.y) > 0 && tempVel.y == 0) tempVel.y = velocity.y;

        // Make sure that speed stays the same even if going diagonally.
        tempVel.clampMagnitude(speed);
        velocity.set(tempVel);
    }



    @Override
    public void handleCollision(Entity entity, World world) {
    super.handleCollision(entity, world);
        // Do special events for touching or pressing interact while touching the entity.
        entity.playerCollision(game, this, getStats());
        if (MouseInput.left.isClicked()) {
            entity.interact(game, this, getStats());
        }
    }

    @Override
    public void update(World world) {
        super.update(world);
        controlMovement();
    }
}
