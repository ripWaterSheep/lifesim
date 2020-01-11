package game.components.structures;

import game.components.GameComponent;
import game.components.World;
import game.components.entities.player.Player;
import util.DrawString;
import util.MyFont;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static util.MyMath.betterRound;


public class Structure extends GameComponent {

    // Contains all subclass instances as well as Structure instances
    private static ArrayList<Structure> instances = new ArrayList<>();

    public static ArrayList<Structure> getInstances() { return instances; }

    protected Font labelFont;
    private int fontSize = 0;

    private double damage = 0;

    public double getDamage() { return damage; }


    public void randomizePos() {
        x = world.getRandX();
        y = world.getRandY();
    }



    public Structure(String name, double x, double y, double width, double height, World world, Color color) {
        super(name, x, y, width, height, world, color);
        Structure.instances.add(this);
    }


    public Structure(String name, double x, double y, double width, double height, World world, Color color, double damage) {
        super(name, x, y, width, height, world, color);
        Structure.instances.add(this);
        this.damage = damage;
    }


    public Structure(String name, double x, double y, double width, double height, World world, Color color, int fontSize) {
        this(name, x, y, width, height, world, color);
        this.fontSize = fontSize;
        labelFont = new Font(MyFont.getMainFont(), Font.PLAIN, fontSize);

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
            super.draw(g);

            if (fontSize > 0) DrawString.drawCenteredString(g, name, getShape().getBounds(), labelFont, Color.WHITE);
        }
    }

}


