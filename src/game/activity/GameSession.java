package game.activity;

import game.organization.Layout;
import game.activity.controls.MouseControls;
import game.organization.World;
import game.organization.components.entities.Player;
import game.overlay.StatBar;
import game.overlay.Overlay;

import java.awt.*;

public class GameSession {

    private Layout layout;

    public Layout getLayout() {
        return layout;
    }


    private static int currentFrame = 0;

    public static int getCurrentFrame() {
        return currentFrame;
    }

    public void debug() {
        currentFrame++;
    }



    public void init() {
        layout = new Layout();

        for (World world: World.getWorlds()) {
            world.init();
        }
    }


    public void run(Graphics g) {

        Player.getInstance().getWorld().run(g);

        StatBar.retrieveValues();
        Overlay.drawOverlays(g);
        debug();
        MouseControls.reset();
    }



}
