package game.setting.world;


import main.Main;

import java.awt.*;

public class WorldRenderer {

    private Color outerColor;

    WorldRenderer(Color outerColor) {
        this.outerColor = outerColor;
    }

    public void run() {
        Main.setPanelColor(outerColor);
    }

}
