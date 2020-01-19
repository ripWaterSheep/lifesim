package game.components.structures;

import game.components.GameComponent;
import game.components.World;
import game.components.entities.player.Player;
import game.components.entities.player.Stats;
import util.Drawing.DrawString;
import util.Drawing.MyFont;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class Structure extends GameComponent {

    // Contains all subclass instances as well as Structure instances
    private static ArrayList<Structure> instances = new ArrayList<>();

    public static ArrayList<Structure> getInstances() { return instances; }


    protected Font labelFont;
    private int fontSize = 0;

    protected Stats stats;


    public Structure(String name, double x, double y, double width, double height, World world, Color color) {
        super(name, x, y, width, height, world, color);
        Structure.instances.add(this);
    }



    public Structure(String name, double x, double y, double width, double height, World world, Color color, int fontSize) {
        this(name, x, y, width, height, world, color);
        this.fontSize = fontSize;
        labelFont = new Font(MyFont.getMainFont(), Font.PLAIN, fontSize);

    }


    public Structure(String name, double x, double y, double scale, World world, String imageName) {
        super(name, x, y, scale, world, imageName);
    }


    public void randomizePos() {
        x = world.getRandX();
        y = world.getRandY();
    }


    public void onTouch() {}

    public void onClick() {}


    @Override
    public void init(JPanel panel) {
        stats = Player.getInstance().getStats();
    }


    @Override
    public void update() {


    }


    @Override
    public void draw(Graphics g) {
        if (Player.getInstance().getWorld() == world) {
            super.draw(g);

            if (fontSize > 0) DrawString.drawCenteredString(g, name, getShape().getBounds(), labelFont, Color.WHITE);
        }
    }

}


