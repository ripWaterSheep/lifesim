package lifesim.state.menus;


import lifesim.game.entities.components.sprites.ImageSprite;
import lifesim.state.engine.Main;
import lifesim.state.menus.ui.Button;
import lifesim.state.menus.ui.ButtonSize;
import lifesim.util.math.geom.Vector2D;

public class TitleMenu extends Menu {


    public TitleMenu() {
        super(new ImageSprite("menu_test"));
        buttons.add(new Button("Play", new Vector2D(0, 50), ButtonSize.WIDE) {
            @Override
            public void onClick() {
                Main.resumeGame();
            }
        });
    }

}