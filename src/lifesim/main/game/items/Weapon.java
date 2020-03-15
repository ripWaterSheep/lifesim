package lifesim.main.game.items;

import lifesim.main.game.entities.Entity;
import lifesim.main.game.entities.components.sprites.Sprite;
import lifesim.main.game.entities.components.stats.DamageStats;
import lifesim.main.game.setting.World;

import javax.swing.border.EmptyBorder;

public class Weapon extends Item {

    private Entity spawnEntity;

    public Weapon(String name, Sprite sprite, Entity spawnEntity) {
        super(name, sprite);
        this.spawnEntity = spawnEntity;
    }


    @Override
    public void onClick(World world, Entity entity) {
        world.add(spawnEntity);
    }

}
