package lifesim.game.entities.itemEntites;

import lifesim.game.entities.Player;
import lifesim.game.entities.SolidEntity;
import lifesim.game.entities.stats.PlayerStats;
import lifesim.game.entities.stats.Stats;
import lifesim.game.handlers.World;
import lifesim.game.items.ItemType;
import lifesim.state.Game;
import lifesim.util.geom.Vector2D;
import lifesim.util.sprites.Sprite;

import java.util.ArrayList;
import java.util.List;

import static lifesim.util.MyMath.*;


public class ItemPackage extends SolidEntity {

    // All of the items that are contained inside.
    private final List<DroppedItem> loot = new ArrayList<>();
    // Number of item types that the package contains
    private final int lootCount = getRandInt(2, 4);

    private final int luck; // Increases chance of generating rarer items.

    public ItemPackage(String name, Sprite sprite, Stats stats, double baseDepth, int luck) {
        super(name, sprite, stats, baseDepth);
        this.luck = luck;
    }

    @Override
    public void playerCollision(Game game, Player player, PlayerStats stats) {
        super.playerCollision(game, player, stats);
        game.displayMessage("Break me open!");
    }

    private void generateLoot() {
        for (int i = 0; i < lootCount; i++) {
            ItemType type = ItemType.getRandomLoot(luck);
            int amount = type.avgLootAmount;
            // Make amount vary by a little bit (not enough to make it change if amount == 1)
            amount += betterRound(amount * getRand(-0.49, 0.49));

            loot.add(new DroppedItem(type, amount));
        }
    }

    private void dropLoot(World world) {
        // Drop loot in a circle spread around this package's position.
        generateLoot();
        for (DroppedItem item : loot) {
            Vector2D spread = Vector2D.newMagDir(getRand(15, 25), getRand(0, 360));
            world.add(item, getPos().translate(spread));
        }
    }

    @Override
    public void update(World world) {
        super.update(world);
        if (!stats.isAlive()) {
            dropLoot(world);
            removeFromWorld();
        }
    }
}
