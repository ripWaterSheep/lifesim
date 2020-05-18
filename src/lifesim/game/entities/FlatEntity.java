package lifesim.game.entities;

import lifesim.game.entities.stats.Stats;
import lifesim.util.sprites.Sprite;

import java.awt.*;

public class FlatEntity extends Entity {

    public FlatEntity(String name, Sprite sprite, Stats stats) {
        super(name, sprite, stats);
    }

    public FlatEntity(String name, Sprite sprite) {
        super(name, sprite);
    }

    @Override
    public boolean isFlat() {
        return true;
    }

    @Override
    protected void renderShadow(Graphics2D g2d) {
    }

}
