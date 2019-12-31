package Main;

import Controls.MiscControlStuff;
import GameComponents.*;
import Drawing.Stat;
import GameSession.DefaultLayout;
import GameSession.GameLayout;
import Util.MiscUtil;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;


public class EventLoop {

    /** Holds object with every instance used in the game */
    private static GameLayout usedGameLayout;

    public static GameLayout getUsedGameLayout() { return usedGameLayout; }


    /** Holds all instances used in the game */
    private static ArrayList<GameComponent> usedInstances;

    public static ArrayList<GameComponent> getUsedInstances() { return usedInstances; }


    private static int currentFrame = 0;

    public static int CurrentFrame() { return currentFrame; }


    public void debug() {
        currentFrame++;
    }


    public void init(JPanel panel) {
        usedGameLayout = new DefaultLayout();

        usedInstances = new ArrayList<>();
        usedInstances.add(Player.getInstance());
        usedInstances.addAll(MiscUtil.reverse(Structure.getInstances()));
        usedInstances.addAll(World.getInstances());

        Collections.reverse(usedInstances);
        for (GameComponent instance : usedInstances) {
            instance.setup(panel);
        }
    }


    public void loop(Graphics g) {
        for (GameComponent instance : usedInstances)
            instance.act();
        Stat.retrieveValues();
        for (GameComponent instance : usedInstances)
            instance.draw(g);
        Stat.drawAll(g);
        debug();
        MiscControlStuff.resetControls();
    }


}
