package game.components.entities;

import game.activity.GameSession;
import game.components.Component;

import java.awt.*;

import static java.lang.Math.min;
import static java.lang.Math.sqrt;
import static util.ColorMethods.applyOpacity;
import static util.MyMath.*;


public class StatParticle extends Projectile {

    private static final long INTERVAL = 4;

    private static final int MIN_DISTANCE = 50;
    private static final int MAX_DISTANCE = 90;

    private static final double ANGLE_VARIATION = 25;


    public static void spawnParticles(Component spawnTarget, boolean elliptical, Color color, double rate, boolean rising) {
        if (GameSession.getCurrentFrame() % INTERVAL == 1) {
            double angle = 90;
            if (!rising) angle = 270;
            angle += getRand(-ANGLE_VARIATION, ANGLE_VARIATION);

            double range = getRand(MIN_DISTANCE, MAX_DISTANCE);
            double speed = clamp(sqrt(rate*5),2.5, 11);

            if (rate > 0) new StatParticle(spawnTarget, getRand(7, 13), elliptical, color, speed, angle, range);
        }
    }


    private Component spawnTarget;


    private StatParticle(Component c, double size, boolean elliptical, Color color, double speed, double angle, double range) {
        super("", c.getX(), c.getY(), size, size, elliptical, color, c.getWorld(), speed,angle, range, 0, false);
        this.spawnTarget = c;
        setPosition();
    }


    private void setPosition() {
        double r = sqrt(Math.random());
        double angle = Math.random()*Math.PI*2;

        x = spawnTarget.getX() + (r * Math.cos(angle) * spawnTarget.getMidWidth());
        y = spawnTarget.getY() + (r * Math.sin(angle) * spawnTarget.getMidHeight());
    }


    protected void fadeLogic() {
        int opacity = clamp(255 - (int)(currentDistance/range*255), 0, 255)^2;
        color = applyOpacity(color, opacity);
        if (opacity <= 0) delete();
    }


    @Override
    public void update() {
        super.update();
        if (doesExist()) fadeLogic();
    }


}
