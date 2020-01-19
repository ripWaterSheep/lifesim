package game.components.entities;

import game.components.entities.player.Player;

import java.awt.*;
import java.util.ArrayList;

import static util.MyMath.getRandInRange;
import static util.TimeUtil.getCurrentTime;


public class Particle extends Projectile {

    //TODO: Fix bug: only one type of particle able to spawn per frame.

    private static final double SPEED = 6;
    private static final long INTERVAL = 90;
    private static final int MIN_DISTANCE = 20;
    private static final int MAX_DISTANCE = 60;

    private static long lastSpawnStartTime = 0;


    private static ArrayList<Particle> spawnQueue = new ArrayList<>();


    public static void spawnParticles(Color color, boolean rising) {
        double angle = 90;
        if (!rising) angle = 270;
        spawnQueue.add(new Particle(5, color, angle));
    }


    public static void startQueuedParticles() {
        if (getCurrentTime() - lastSpawnStartTime > INTERVAL) {
            spawnQueue.forEach((particle)-> particle.started = true);
            lastSpawnStartTime = getCurrentTime();
            spawnQueue.clear();
        }
    }


    private boolean started = false;


    private Particle(int radius, Color color, double angle) {
        super("", 0, 0, radius, Player.getInstance().getWorld(), color, SPEED, angle, getRandInRange(MIN_DISTANCE, MAX_DISTANCE), 0, 1, false);
        setPosition();

    }


    private Particle(double scale, String imageName, double angle) {
        super("", 0, 0, scale, Player.getInstance().getWorld(), imageName, SPEED, angle, getRandInRange(MIN_DISTANCE, MAX_DISTANCE), 0, 1, false);
        setPosition();
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
        if (started) {
            if (currentDistance < range) {
                moveTowardsAngle();
                currentDistance += speed;
            } else {
                alive = false;
            }
        }
    }


    /*@Override
    public void update() {
        super.update();
        movementLogic();
    }*/



}
