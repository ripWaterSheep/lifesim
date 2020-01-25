package game.components.entities;

import game.GameSession;
import game.components.GameComponent;
import game.components.World;
import game.components.entities.player.Player;

import java.awt.*;

import static java.lang.Math.sqrt;
import static util.ColorMethods.applyOpacity;
import static util.MyMath.*;


public class StatParticle extends Projectile{

    private static final long INTERVAL = 3;

    private static final int MIN_DISTANCE = 45;
    private static final int MAX_DISTANCE = 70;

    private static final double ANGLE_VARIATION = 20;


    public static void spawnParticles(Entity spawnTarget, Color color, double rate, boolean rising) {
        if (GameSession.getCurrentFrame() % INTERVAL == 1) {
            double angle = 90;
            if (!rising) angle = 270;
            angle += getRandInRange(-ANGLE_VARIATION, ANGLE_VARIATION);

            double range = getRandInRange(MIN_DISTANCE, MAX_DISTANCE);
            double speed = sqrt(rate*6)+1;

            if (rate > 0) new StatParticle(getRandInRange(6, 11), color, speed, angle, range, spawnTarget);
        }
    }


    private Entity spawnTarget;
   // private int opacity = 255;


    private StatParticle(double radius, Color color, double speed, double angle, double range, Entity spawnTarget) {
        super("", 0, 0, radius, color, spawnTarget.getWorld(), speed, angle, range, 0, 1000, false);

        setWorld(Player.getInstance().getWorld());

        this.spawnTarget = spawnTarget;
        System.out.println(spawnTarget);
        setPosition();
    }


    private void setPosition() {
        double r = sqrt(Math.random());
        double angle = Math.random()*Math.PI*2;

        x = spawnTarget.getX() + (r * Math.cos(angle) * spawnTarget.getMidWidth());
        y = spawnTarget.getY() + (r * Math.sin(angle) * spawnTarget.getMidHeight());
    }


    protected void movementLogic() {
        super.movementLogic();
        //stats.speed = sqrt(opacity)*0.45;
    }



    protected void fadeLogic() {
        int opacity = 255 - clamp(betterRound(currentDistance/range*255), 0, 255);
        color = applyOpacity(color, opacity);
        if (opacity <= 0) visible = false;
    }


    @Override
    public void update() {
        super.update();
        if (visible) fadeLogic();
    }



}
