package game.components.world;

import game.components.GameComponent;
import game.components.player.Player;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;




public class World extends GameComponent {

    private static ArrayList<World> instances = new ArrayList<>();

    public static ArrayList<World> getInstances() { return instances; }



    /** Since player position indicates the position of everything relative
     * to the player instead of the player itself, the world must be translated based on the player's position
     * as well as the display size to keep the player in the same place when windows is resized.
     */


    public int getRandX() { return (int)(Math.random() * width)-getMidWidth(); }

    public int getRandY() { return (int)(Math.random() * height)-getMidHeight(); }


    private Color outerColor;

    public Color getOuterColor() {
        return outerColor;
    }





    public World(String label, int width, int height, Color color, Color outerColor) {
        World.instances.add(this);

        this.label = label;
        this.width = width;
        this.height = height;
        this.world = this;
        this.color = color;
        this.outerColor = outerColor;

        isEllipse = false;
    }


    @Override
    public void setup(JPanel panel) {

    }


    @Override
    public void act() {


    }


    @Override
    public void draw(Graphics g) {
        if (Player.getInstance().getWorld() == this) {
            Graphics2D g2d = (Graphics2D) g.create();

            g2d.setColor(color);
            g2d.fillRect(getDisplayX(), getDisplayY(), getWidth(), getHeight());
        }
    }


}
