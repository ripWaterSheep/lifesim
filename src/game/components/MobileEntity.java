package game.components;

import game.components.player.Player;

import javax.swing.*;
import java.awt.*;

import static util.MyMath.betterRound;
import static util.MyMath.clamp;


public class MobileEntity extends Structure {

    //TODO: finish movement stuff
    
    /* Since mobile entity extends structure, each instance is added to the Structures class instance Arraylist because they share the same constructor lol
     * 
     */

    public enum MovementType { LINEAR, FOLLOW, AVOID }


    private MovementType movement;

    private int startAngle; // Angle to start moving towards when object is created
    private int speed; // Pixels to move per frame
    private int turnAngle;  // How many degrees to turn after getting to the end of the range
    private final int range; // How far to go before turning

    private boolean launched = false;

    private int currentDistance = 0;
    private int currentAngle;
    private boolean currentlyReversed = false;




    public MobileEntity(String label, int x, int y, int width, int height, World world, Color color,
                        int speed, int startAngle, int turnAngle, int range) {

        super(label, x, y, width, height, world, color);

        this.speed = speed;
        this.startAngle = startAngle;
        this.turnAngle = turnAngle;
        this.range = range;

        launch();
    }




    public MobileEntity(String label, int x, int y, int width, int height, World world, Color color,
                        int speed, MovementType movement, int startAngle, int turnAngle, int range, int fontSize) {

        this(label, x, y, width, height, world, color, speed, startAngle, turnAngle, range);

        this.fontSize = fontSize;
        labelFont = new Font("Comic Sans MS", Font.PLAIN, fontSize);

    }


    public void launch() {
        currentAngle = startAngle;
        this.launched = true;
    }



    @Override
    public void setup(JPanel panel) {

    }



    @Override
    public void act() {
        Player player = Player.getInstance();
        switch(movement) {
            case LINEAR:
                x = (currentDistance*(betterRound(Math.sin(currentAngle)))) + x;
                y += (currentDistance*(betterRound(Math.sin(currentAngle)))) + y;

                if (currentlyReversed) {
                    currentDistance += speed;
                } else {
                    currentDistance -= speed;
                }
            break;

        case FOLLOW:
            x += clamp(player.getX()-x,0.1,speed);
            y += clamp(player.getY()-y,0.1,speed);
            break;

        case AVOID:
            x -= clamp(player.getX()-x,0.1,speed);
            y -= clamp(player.getY()-y,0.1,speed);
            break;
        }

    }

}


