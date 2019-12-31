package Main;

import GameComponents.*;
import Controls.KeyboardControls;
import TextDisplay.Stat;
import Util.MiscUtil;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;


public class EventLoop {

    /** Holds all instances used in the game */
    public static ArrayList<GameComponent> usedInstances;


    private static int currentFrame = 0;

    public static int CurrentFrame() { return currentFrame; }


    public void debug() {
        currentFrame++;
    }


    public void init(JPanel panel) {
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
        KeyboardControls.resetKeys();
    }


}
