package game.activity;

import game.organization.Layout;
import game.activity.controls.MouseControls;
import game.organization.World;
import game.components.entities.player.Player;
import game.overlay.Overlay;

import java.awt.*;

public class GameSession {

    Overlay overlay;

    private static int currentFrame = 0;

    public static int getCurrentFrame() {
        return currentFrame;
    }

    public void debug() {
        currentFrame++;
    }

    //TODO: Add player instance here.
    
    public void init() {
        new Layout();
        overlay = new Overlay();

        for (World world: World.getWorlds()) {
            world.init();
        }
    }


    public void run(Graphics g) {
        Player.getInstance().getWorld().run(g);

        overlay.run(g);
        debug();
        MouseControls.reset();
    }



}
