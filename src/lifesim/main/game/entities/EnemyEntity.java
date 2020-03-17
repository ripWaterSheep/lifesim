package lifesim.main.game.entities;

import lifesim.main.game.Game;
import lifesim.main.game.entities.components.items.Item;
import lifesim.main.game.entities.components.sprites.Sprite;
import lifesim.main.game.entities.components.stats.Stats;
import lifesim.main.game.entities.components.items.Weapon;
import lifesim.main.game.setting.World;

import static lifesim.main.util.math.Geometry.*;


public class EnemyEntity extends InventoryEntity {

    private final double detectionRange;

    private boolean detectingPlayer = false;
    private boolean attackingPlayer = false;


    public EnemyEntity(String name, Sprite sprite, Stats stats, double speed, double detectionRange) {
        super(name, sprite, stats, speed);
        this.detectionRange = detectionRange;
    }

    @Override
    public EnemyEntity acquireItem(Item item) {
        return (EnemyEntity) super.acquireItem(item);
    }

    //TODO: make entity attack any other entities
    private void detectPlayer() {
        Player player = Game.getSession().getPlayer();

        detectingPlayer = (getDistanceBetween(player.pos, pos) <= detectionRange);
        attackingPlayer = isTouching(player);
    }


    @Override
    protected void move() {
        super.move();
        Player player = Game.getSession().getPlayer();

        if (detectingPlayer && !attackingPlayer) {
            movement.setMagnDir(defaultSpeed, getAngleBetween(player.pos, pos));
        } else {
            movement.set(movement.scale(0.7));
        }
        System.out.println(isTouching(player));
    }


    @Override
    public void update(World world) {
        super.update(world);
        detectPlayer();
    }
}
