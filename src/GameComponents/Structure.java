package GameComponents;

import Util.WindowSize;
import Util.Callback;

import javax.swing.*;
import java.awt.*;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;


public class Structure extends GameComponent {

    private static ArrayList<Structure> instances = new ArrayList<>();

    public static ArrayList<Structure> getInstances() { return instances; }


    private String label;

    public String getLabel() {
        return label;
    }

    private int width;
    private int height;

    private int getDisplayX() { return x - Player.getInstance().getX() - (width/2) + WindowSize.getMidWidth(); }

    private int getDisplayY() { return y - Player.getInstance().getY() - (height/2) + WindowSize.getMidHeight(); }


    private World world;

    public World getWorld() {
        return world;
    }

    public void randomizePos() {
        x = world.getRandX();
        y = world.getRandY();
    }


    public Rectangle getShape() { return new Rectangle(getDisplayX(), getDisplayY(), width, height); }


    private ArrayList<Callback> commandsOnTouch = new ArrayList<>();

    public void addCommandOnTouch(Callback command) { commandsOnTouch.add(command); }

    public ArrayList<Callback> getCommandsOnTouch() { return commandsOnTouch; }


    private ArrayList<Callback> commandsOnTap = new ArrayList<>();

    public void addCommandOnTap(Callback command) { System.out.println();
        commandsOnTap.add(command); }

    public ArrayList<Callback> getCommandsOnTap() { return commandsOnTap; }


    private boolean randomPosOnTouch;

    public boolean getRandomPosOnTouch() {
        return  randomPosOnTouch;
    }



    public Structure(String label, int x, int y, int width, int height, World world, Color color) {
        Structure.instances.add(this);

        this.label = label;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        this.world = world;
        this.color = color;

        this.randomPosOnTouch = false;
    }


    public Structure(String label, int x, int y, int width, int height, World world, Color color, Callback[] commandsOnTouch, Callback[] commandsOnTap, boolean randomPosOnTouch) {
        this(label, x, y, width, height, world, color);

        if (commandsOnTouch != null)
            this.commandsOnTouch = new ArrayList<>(Arrays.asList(commandsOnTouch));
        if (commandsOnTap != null)
            this.commandsOnTap = new ArrayList<>(Arrays.asList(commandsOnTap));

        this.randomPosOnTouch = randomPosOnTouch;
    }


    @Override
    public void setup(JPanel panel) {

    }


    @Override
    public void act() {

    }


    @Override
    public void draw(Graphics g) {
        if (Player.getInstance().getWorld() == world) {
            Graphics2D g2d = (Graphics2D) g.create();



            g2d.setColor(color);
            g2d.fill(getShape());
        }
    }

}


