package game.components.structures;

import game.components.GameComponent;
import game.components.World;
import game.components.player.Player;
import util.DrawString;
import util.MyFonts;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;



public class Structure extends GameComponent {

    // Contains all subclass instances as well as Structure instances
    protected static ArrayList<Structure> instances = new ArrayList<>();

    public static ArrayList<Structure> getInstances() { return instances; }


    protected final String label;

    public String getLabel() {
        return label;
    }

    protected Font labelFont;
    public int fontSize = 0;


    public void randomizePos() {
        x = world.getRandX();
        y = world.getRandY();
    }


    @Override
    public Rectangle getShape() { return new Rectangle(getDisplayX(), getDisplayY(), width, height); }


    public Structure(String label, int x, int y, int width, int height, World world, Color color) {
        Structure.instances.add(0, this);

        this.label = label;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        this.world = world;
        this.color = color;

    }


    public Structure(String label, int x, int y, int width, int height, World world, Color color, int fontSize) {
        this(label, x, y, width, height, world, color);
        this.fontSize = fontSize;
        labelFont = new Font(MyFonts.getMainFont(), Font.PLAIN, fontSize);

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

            if (fontSize > 0)
                DrawString.drawCenteredString(g, label, getShape(), labelFont, Color.WHITE);
        }
    }

}


