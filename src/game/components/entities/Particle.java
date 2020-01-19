package game.components.entities;

import game.components.entities.player.Player;

import java.awt.*;

import static util.MyMath.getRandInRange;
import static util.TimeUtil.getCurrentTime;


public class Particle extends Projectile {

    //TODO: Fix bug: only one type of particle able to spawn per frame.

    private static final double SPEED = 3;
    private static final long INTERVAL = 40;

    private static long lastParticleStartTime = 0;


    public static void spawnRisingParticles(Color color) {
        if (getCurrentTime() - lastParticleStartTime > INTERVAL) {
            new Particle(10, color, 90);
            lastParticleStartTime = getCurrentTime();
        }
    }


    public static void spawnFallingParticles(Color color) {
        if (getCurrentTime() - lastParticleStartTime > INTERVAL) {
            new Particle(10, color, 270);
            lastParticleStartTime = getCurrentTime();
        }
    }


    private Particle(int radius, Color color, double angle) {
        super("", 0, 0, radius, Player.getInstance().getWorld(), color, SPEED, angle, getRandInRange(25, 90), 0, 1, false);
        setPosition();

    }


    private Particle(double scale, String imageName, double angle) {
        super("", 0, 0, scale, Player.getInstance().getWorld(), imageName, SPEED, angle, getRandInRange(25, 90), 0, 1, false);
        setPosition();
    }



    private void setPosition() {
        Player player = Player.getInstance();
        double r = Math.sqrt(Math.random());
        double angle = Math.random()*Math.PI*2;

        x = player.getX() + (r * Math.cos(angle) * player.getWidth());
        y = player.getY() + (r * Math.sin(angle) * player.getHeight());

    }


    @Override
    protected void movementLogic() {
        if (currentDistance < range) {
            moveTowardsAngle();
            currentDistance += speed;
        } else {
            alive = false;
        }
    }


    @Override
    public void act() {
        super.act();
        movementLogic();
    }



}
