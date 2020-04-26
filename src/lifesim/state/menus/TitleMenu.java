package lifesim.state.menus;


import lifesim.game.entities.components.sprites.ImageSprite;
import lifesim.state.Game;
import lifesim.state.engine.Main;
import lifesim.state.menus.ui.Button;
import lifesim.state.menus.ui.ButtonSize;
import lifesim.util.geom.Vector2D;

public class TitleMenu extends Menu {


    public TitleMenu(Game game) {
        super(new ImageSprite("menu_test"), game);

        buttons.add(new Button("Play", new Vector2D(0, 50), ButtonSize.WIDE) {
            @Override
            public void onClick() {
                Main.resumeGame();
            }
        });
        buttons.add(new Button("Settings",  new Vector2D(0, 75), ButtonSize.MID) {
            @Override
            protected void onClick() {
                Main.goToSettings();
            }
        });
    }

}