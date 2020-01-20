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
    private static ArrayList<Structure> structureInstances = new ArrayList<>();

    public static ArrayList<Structure> getStructureInstances() { return structureInstances; }


    protected Font labelFont;
    private int fontSize = 0;

    protected Stats stats;

    // Used for interacting with player
    protected Player player;


    private boolean randomizePos;


    public Structure(String name, double x, double y, double width, double height, Color color, int fontSize) {
        super(name, x, y, width, height, color);
        Structure.structureInstances.add(0, this);
        this.fontSize = fontSize;
        labelFont = new Font(MyFont.getMainFont(), Font.PLAIN, fontSize);

    }

    public Structure(String name, double x, double y, double width, double height, Color color) {
        this(name, x, y, width, height, color, 0);
    }

    public Structure(String name, double x, double y, double scale, String imageName) {
        super(name, x, y, scale, imageName);
    }


    public Structure(String name, double width, double height, Color color) {
        this(name, 0, 0, width, height, color);
        randomizePos = true;
    }

    public Structure(String name, double width, double height, Color color, int fontSize) {
        this(name, 0, 0, width, height, color, fontSize);
        randomizePos = true;
    }

    public Structure(String name, double scale, String imageName) {
        this(name, 0, 0, scale, imageName);
        randomizePos = true;
    }



    public Structure setWorld(World world) {
        this.world = world;
        return this;
    }


    public void randomizePos() {
        x = world.getRandX();
        y = world.getRandY();
    }


    public void onTouch() {}

    public void onClick() {}


    @Override
    public void init() {
        player = Player.getInstance();
        stats = player.getStats();
    }


    @Override
    public void update() {


    }


    @Override
    public void draw(Graphics g) {
        if (Player.getInstance().getWorld() == world) {
            super.draw(g);

            if (fontSize > 0)
                DrawString.drawCenteredString(g, name, getShape().getBounds(), labelFont, Color.WHITE);
        }
    }

}


