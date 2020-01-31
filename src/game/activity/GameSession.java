package game.activity;

import game.organization.Layout;
import game.activity.controls.MouseControls;
import game.organization.World;
import game.components.entities.player.Player;
import game.overlay.GameMessage;
import game.overlay.Overlay;
import main.Main;
import main.MainPanel;

import java.awt.*;
import java.util.ArrayList;

public class GameSession {


    private static int currentFrame = 0;
    public static int getCurrentFrame() {
        return currentFrame;
    }

    public static void debug() {
        currentFrame++;
    }


    Overlay overlay;


    private ArrayList<World> worlds = new ArrayList<>();

    // Return a copy of encapsulated object to prevent external modification.
    public ArrayList<World> getWorlds() {
        return new ArrayList<>(worlds);
    }

    public void addWorld(World world) {
        worlds.add(world);
    }



    public void init() {
        new Layout();
        overlay = new Overlay();

        for (World world: getWorlds()) {
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
