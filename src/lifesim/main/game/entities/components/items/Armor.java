package lifesim.main.game.entities.components.items;

import lifesim.main.game.entities.Entity;
import lifesim.main.game.entities.components.sprites.Sprite;
import lifesim.main.game.setting.World;

public class Armor extends Item {

    private final double protectionFactor;

    public Armor(String name, Sprite sprite, double protectionFactor) {
        super(name, sprite);
        this.protectionFactor = protectionFactor;
    }

    @Override
    public void whileHolding(World world, Entity entity) {

    }

}
