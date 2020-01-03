package game;

import game.components.player.Player;
import game.components.GameComponent;
import game.components.Structure;
import game.components.World;
import game.overlay.Overlay;
import game.overlay.Stat;
import util.ArrayListMethods;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;


public class GameSession {


    private static GameLayout usedLayout = new GameLayout();

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
