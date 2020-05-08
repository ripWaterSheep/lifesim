package lifesim.game.entities;

import lifesim.game.items.ItemType;
import lifesim.engine.output.GamePanel;
import lifesim.util.sprites.Animation;
import lifesim.util.sprites.DirectionalAnimatedSprite;
import lifesim.game.handlers.World;
import lifesim.state.Game;
import lifesim.game.entities.stats.PlayerStats;
import lifesim.engine.input.KeyInput;
import lifesim.engine.input.MouseInput;
import lifesim.game.items.inventory.Inventory;
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
            new PlayerStats(1000, 1000, 0, 0, 0, game), 3.5, 0);
        velocity.set(0, 0);
        this.game = game;
        setWorld(startingWorld);
        inventory = new Inventory(this, game);
    }


    public void init() {
        acquireItem(ItemType.STARTER_FACTORY, 25);
        acquireItem(ItemType.WALLBOT, 25);
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
    }

    public void goToEntity(String name) {
        for (World world: game.getWorlds()) {
            for (Entity entity: world.getEntities()) {
                if (entity.name.equals(name)) {
                    setWorld(world);
                    pos.set(entity.pos);
                    return;
                }
            }
        }
    }


    public void acquireItem(ItemType item, int amount) {
        inventory.addItem(item, amount);
    }


    @Override
    public void removeFromWorld() {
        super.removeFromWorld();
        // Summon some type of death effect
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
            speed *= 1.45;
        }

        Vector2D tempVel = new Vector2D(0, 0);
        // Move according to W-A-S-D key presses;
        if (KeyInput.k_w.isPressed()) tempVel.translate(0, -speed); //U
        if (KeyInput.k_a.isPressed()) tempVel.translate(-speed, 0); //L
        if (KeyInput.k_s.isPressed()) tempVel.translate(0, speed);  //D
        if (KeyInput.k_d.isPressed()) tempVel.translate(speed, 0);  //R

        // Maintain momentum even if not currently walking in direction of momentum.
        if (abs(velocity.x) > 0 && tempVel.x == 0) tempVel.x = velocity.x;
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
