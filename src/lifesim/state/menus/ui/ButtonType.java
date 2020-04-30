package lifesim.state.menus.ui;

import lifesim.util.sprites.ImageSprite;
import lifesim.util.sprites.Sprite;
import lifesim.util.geom.Vector2D;



public enum ButtonType {

    TINY(new ImageSprite("buttons", new Vector2D(0, 0), new Vector2D(18, 18))),
    SMALL(new ImageSprite("buttons", new Vector2D(18, 0), new Vector2D(36, 18))),
    MID(new ImageSprite("buttons", new Vector2D(54, 0), new Vector2D(54, 18))),
    WIDE(new ImageSprite("buttons", new Vector2D(0, 18), new Vector2D(72, 18))),
    BEEFY(new ImageSprite("buttons", new Vector2D(0, 36), new Vector2D(72, 36)));


    ButtonType(Sprite sprite) {
        this.sprite = sprite;
    }

    public final Sprite sprite;

}
