package lifesim.state.menus.ui;

import lifesim.util.sprites.ImageSprite;
import lifesim.util.sprites.Sprite;
import lifesim.util.geom.Vector2D;



public enum ButtonType {

    TINY(new ImageSprite("buttons", new Vector2D(0, 0), new Vector2D(20, 20))),
    SMALL(new ImageSprite("buttons", new Vector2D(20, 0), new Vector2D(40, 20))),
    MID(new ImageSprite("buttons", new Vector2D(60, 0), new Vector2D(60, 20))),
    WIDE(new ImageSprite("buttons", new Vector2D(0, 20), new Vector2D(80, 20))),
    BEEFY(new ImageSprite("buttons", new Vector2D(0, 40), new Vector2D(80, 40)));


    ButtonType(Sprite sprite) {
        this.sprite = sprite;
    }

    public final Sprite sprite;

}
