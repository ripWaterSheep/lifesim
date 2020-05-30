package lifesim.state.menus.ui;

import lifesim.util.sprites.ImageSprite;
import lifesim.util.sprites.Sprite;
import lifesim.util.geom.Vector2D;



public enum ButtonType {

    TINY(new ImageSprite("ui/buttons", new Vector2D(0, 0), new Vector2D(24, 24))),
    SMALL(new ImageSprite("ui/buttons", new Vector2D(24, 0), new Vector2D(48, 24))),
    MID(new ImageSprite("ui/buttons", new Vector2D(72, 0), new Vector2D(72, 24))),
    WIDE(new ImageSprite("ui/buttons", new Vector2D(0, 24), new Vector2D(108, 24))),
    BEEFY(new ImageSprite("ui/buttons", new Vector2D(0, 48), new Vector2D(96, 48)));

    ButtonType(Sprite sprite) {
        this.sprite = sprite;
    }

    public final Sprite sprite;

}
