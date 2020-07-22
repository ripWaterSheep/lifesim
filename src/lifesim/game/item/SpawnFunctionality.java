package lifesim.game.item;

import lifesim.io.output.CursorType;
import lifesim.game.entities.Entity;
import lifesim.game.entities.Player;
import lifesim.game.entities.stats.PlayerStats;
import lifesim.game.entities.types.Spawnable;
import lifesim.game.handlers.World;
import lifesim.io.input.MouseInput;
import lifesim.io.output.GameWindow;
import lifesim.util.GraphicsMethods;
import lifesim.util.geom.Vector2D;

import java.awt.*;

class SpawnFunctionality extends ConsumeFunctionality {

    private final Spawnable spawnable;
    private final Entity spawnHint; // Translucent sneak peak of entity when hovering mouse over somewhere when it can be used.

    public SpawnFunctionality(Spawnable spawnable) {
        this.spawnable = spawnable;
        spawnHint = spawnable.spawnEntity();
    }


    /** Get the position that the entity will spawn at, it is the mouse cursor's location in the game-world. */
    public Vector2D getHoveringPos(Player player) {
        return MouseInput.getCursorPosFrom(player.getPos());
    }


    /** Make sure no entities that aren't flat are in the way of the entity at its spawn position */
    public boolean isSpawnPosClear(World world) {
        boolean spawnPosClear = true;
        for (Entity entity: world.getEntities()) {
            if (entity.shouldBeSorted() && entity.isTouching(spawnHint)) {
                spawnPosClear = false;
            }
        }
        return spawnPosClear;
    }


    /** Only use the item if right-clicked, the spawn position is clear, and the world doesn't have too many of that type of entity */
    @Override
    public boolean canBeUsed(World world, Player player) {
        return super.canBeUsed(world, player) && isSpawnPosClear(world) && world.canSpawn(spawnable);
    }

    @Override
    public void use(World world, Player player , PlayerStats stats) {
        world.add(spawnable.spawnEntity(), getHoveringPos(player));
    }

    @Override
    public void render(Graphics2D g2d, Player player, GameWindow window) {
        GraphicsMethods.setOpacity(g2d, 0.35);

        // Show translucent hint at what the entity looks like at the mouse cursor.
        spawnHint.setPos(getHoveringPos(player));

        g2d.setColor(Color.RED);
        if (isSpawnPosClear(player.getWorld()) && player.getWorld().canSpawn(spawnable)) {
            window.changeCursor(CursorType.POINTER);
            g2d.setColor(Color.GREEN);
        }

        g2d.fill(spawnHint.sprite.getBoundsAt(spawnHint.getDisplayPos()));
        spawnHint.render(g2d);
    }

}
