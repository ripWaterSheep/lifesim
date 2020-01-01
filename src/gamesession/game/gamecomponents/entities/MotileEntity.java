package gamesession.game.gamecomponents.entities;

import gamesession.game.GameComponent;
import gamesession.game.gamecomponents.Player;
import gamesession.game.gamecomponents.World;
import util.WindowSize;
import util.drawing.DrawString;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class MotileEntity extends Entity {

    //TODO: finish movement stuff

    public enum MovementType { LINEAR, FOLLOW_PLAYER, AVOID_PLAYER }


    private MovementType movementType;

    private int startAngle; // Angle to start moving towards when object is created
    private int speed; // Pixels to move per frame
    private int turnAngle;  // How many degrees to turn after getting to the end of the range
    private final int range; // How far to go before turning

    private boolean launched = false;



    public MotileEntity(String label, int x, int y, int width, int height, World world, Color color,
                        MovementType movementType, int speed, int startAngle, int turnAngle, int range) {

        super(label, x, y, width, height, world, color);

        this.movementType = movementType;
        this.speed = speed;
        this.startAngle = startAngle;
        this.turnAngle = turnAngle;
        this.range = range;

        launch();
    }



    /*
    public MotileEntity(String label, int x, int y, int width, int height, World world, Color color, int fontSize) {
        this(label, x, y, width, height, world, color);
        this.fontSize = fontSize;
        labelFont = new Font("Comic Sans MS", Font.PLAIN, fontSize);

    }*/



    public void launch() {
        this.launched = true;
    }



    @Override
    public void setup(JPanel panel) {

    }


    @Override
    public void act() {

        switch(movementType) {
            case LINEAR:

            break;

            case FOLLOW_PLAYER:

            break;

            case AVOID_PLAYER:

            break;

        }

    }


    @Override
    public void draw(Graphics g) {
        if (Player.getInstance().getWorld() == world) {
            Graphics2D g2d = (Graphics2D) g.create();

            g2d.setColor(color);
            g2d.fill(getShape());

            if (fontSize > 0)
                DrawString.centerStringInRect(g, label, getShape(), labelFont, Color.WHITE);
        }
    }

}


