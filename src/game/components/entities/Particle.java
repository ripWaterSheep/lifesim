package game.components.entities;

import game.GameSession;
import game.components.entities.player.Player;
import util.ColorMethods;

import java.awt.*;
import java.util.ArrayList;

import static util.MyMath.getRandInRange;
import static util.MyMath.roundToPlace;
import static util.TimeUtil.getCurrentTime;


public class Particle extends Projectile {

    private static final double SPEED = 6;
    private static final long INTERVAL = 3;

    private static final int MIN_DISTANCE = 25;
    private static final int MAX_DISTANCE = 80;

    private static final double ANGLE_VARIATION = 20;



    public static void spawnParticles(Color color, boolean rising) {
        if (GameSession.getCurrentFrame() % INTERVAL == 1) {
            double angle = 90;
            if (!rising) angle = 270;
            new Particle(getRandInRange(6, 11), color, getRandInRange(angle-ANGLE_VARIATION, angle+ANGLE_VARIATION));
        }
    }



    private Particle(int radius, Color color, double angle) {
        super("", 0, 0, radius, color, SPEED, angle, getRandInRange(MIN_DISTANCE, MAX_DISTANCE), 0, 1, false);
        setPosition();
        setWorld(Player.getInstance().getWorld());

    }


    private Particle(double scale, String imageName, double angle) {
        super("", 0, 0, scale, imageName, SPEED, angle, getRandInRange(MIN_DISTANCE, MAX_DISTANCE), 0, 1, false);
        setPosition();
        setWorld(Player.getInstance().getWorld());
    }



    private void setPosition() {
        Player player = Player.getInstance();
        double r = Math.sqrt(Math.random());
        double angle = Math.random()*Math.PI*2;

        x = player.getX() + (r * Math.cos(angle) * player.getMidWidth());
        y = player.getY() + (r * Math.sin(angle) * player.getMidHeight());
    }


    @Override
    protected void movementLogic() {
        if (currentDistance < range) {
            moveTowardsAngle();
            currentDistance += speed;
        } else {
            alive = false;
            visible = false;
        }
    }



}
