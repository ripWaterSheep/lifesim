package lifesim.state.menus;


import lifesim.util.sprites.ImageSprite;
import lifesim.state.engine.Main;
import lifesim.state.menus.ui.Button;
import lifesim.state.menus.ui.ButtonSize;
import lifesim.util.geom.Vector2D;

public class TitleMenu extends Menu {


    public TitleMenu() {
        super(new ImageSprite("menu_test"));

        buttons.add(new Button("Play", new Vector2D(0, 25), ButtonSize.WIDE) {
            @Override
            public void onClick() {
                Main.resumeGame();
            }
        });
        buttons.add(new Button("Settings",  new Vector2D(0, 50), ButtonSize.MID) {
            @Override
            protected void onClick() {
                Main.goToSettings();
            }
        });
        buttons.add(new Button("Exit",  new Vector2D(0, 75), ButtonSize.MID) {
            @Override
            protected void onClick() {
                Main.exit();
            }
        });
    }

}