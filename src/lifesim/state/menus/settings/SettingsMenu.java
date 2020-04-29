package lifesim.state.menus.settings;

import lifesim.util.sprites.ImageSprite;
import lifesim.state.Game;
import lifesim.state.engine.Main;
import lifesim.state.menus.Menu;
import lifesim.state.menus.ui.Button;
import lifesim.state.menus.ui.ButtonSize;
import lifesim.state.menus.ui.MultiStateButton;
import lifesim.util.geom.Vector2D;


public class SettingsMenu extends Menu {

    public SettingsMenu(Game game) {
        super(new ImageSprite("settings_test"));
        buttons.add(new MultiStateButton("Difficulty", new Vector2D(-200, -60), ButtonSize.WIDE, 4) {
            @Override
            public void evaluateState(int state) {
                switch (state) {
                    case 0:
                        game.setDifficulty(Difficulty.SMOOTH);
                        setLabel("Smooth");
                        break;
                    case 1:
                        game.setDifficulty(Difficulty.MEDIUM);
                        setLabel("Medium");
                        break;
                    case 2:
                        game.setDifficulty(Difficulty.INTENSE);
                        setLabel("Intense");
                        break;
                    case 3:
                        game.setDifficulty(Difficulty.GOOD_LUCK);
                        setLabel("Good luck.");
                        break;
                }
            }
        });
        buttons.add(new Button("Exit", new Vector2D(220, -120), ButtonSize.MID) {
            @Override
            protected void onClick() {
                Main.goToPrevious();
            }
        });
    }

}
