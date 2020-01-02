package game;

import game.components.player.Player;
import game.components.GameComponent;
import game.components.entities.Structure;
import game.components.world.World;
import game.overlay.Overlay;
import game.overlay.overlays.GameMessage;
import game.overlay.overlays.Stat;
import game.components.arrangement.GameLayout;
import game.components.arrangement.layouts.DefaultLayout;
import util.ArrayListMethods;
import util.MiscUtil;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;


public class GameSession {

    /** Holds object with every instance used in the game */
    private static GameLayout usedLayout;

    public static GameLayout getUsedLayout() { return usedLayout; }


    /** Holds all instances used in the game */
    private static ArrayList<GameComponent> usedComponents;

    public static ArrayList<GameComponent> getUsedComponents() { return usedComponents; }


    private static int currentFrame = 0;

    public static int CurrentFrame() { return currentFrame; }


    public void debug() {
        currentFrame++;
    }


    public void init(JPanel panel) {
        usedLayout = new DefaultLayout();

        usedComponents = new ArrayList<>();
        usedComponents.add(Player.getInstance());
        usedComponents.addAll(ArrayListMethods.getReverse(Structure.getInstances()));
        usedComponents.addAll(World.getInstances());

        Collections.reverse(usedComponents);
        for (GameComponent instance : usedComponents) {
            instance.setup(panel);
        }
    }


    public void loop(Graphics g) {
        for (GameComponent instance : usedComponents)
            instance.act();
        Stat.retrieveValues();

        for (GameComponent instance : usedComponents)
            instance.draw(g);
        Overlay.drawOverlays(g);
        debug();
    }


}
