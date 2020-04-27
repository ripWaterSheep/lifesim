package lifesim.state.menus.ui;

import lifesim.util.geom.Rect;
import lifesim.util.sprites.ImageSprite;
import lifesim.util.sprites.Sprite;
import lifesim.util.geom.Vector2D;



public enum ButtonSize {

    TINY(new ImageSprite("buttons", new Vector2D(0, 0), new Vector2D(16, 16))),
    SMALL(new ImageSprite("buttons", new Vector2D(16, 0), new Vector2D(32, 16))),
    MID(new ImageSprite("buttons", new Vector2D(48, 0), new Vector2D(48, 16))),
    WIDE(new ImageSprite("buttons", new Vector2D(0, 16), new Vector2D(64, 16))),
    BEEFY(new ImageSprite("buttons", new Vector2D(0, 32), new Vector2D(64, 32)));


    ButtonSize(Sprite sprite) {
        this.sprite = sprite;
    }

    public final Sprite sprite;

}
