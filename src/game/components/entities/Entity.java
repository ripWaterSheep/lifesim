package game.components.entities;

import game.components.Component;
import game.components.entities.player.Player;
import game.components.entities.stats.EntityStats;
import util.Geometry;
import util.MyMath;

import java.awt.*;


public abstract class Entity extends Component {

    protected EntityStats stats;

    public EntityStats getStats() {
        return stats;
    }


    public void goToPos(double x, double y) {
        this.x = x;
        this.y = y;
    }


    private boolean exists = true;

    public boolean doesExist() {
        return exists;
    }

    public void delete() {
        exists = false;
    }

    protected double getAngleToPlayer() {
        return Geometry.getAngleBetween(this, Player.getInstance());
    }


    protected Entity(String name, double x, double y, double width, double height, boolean elliptical, Color color) {
        super(name, x, y, width, height, elliptical, color);
    }


    public void moveTowardsAngle() {
        x -= (getStats().getSpeed() * Math.cos(Math.toRadians(getStats().getAngle())));
        y -= (getStats().getSpeed() * Math.sin(Math.toRadians(getStats().getAngle())));
    }


    /** Keep entity within the world barrier. */
    public void borderLogic() {
        x = MyMath.clamp(x, -world.getFloor().getMidWidth() + getMidWidth(), world.getFloor().getMidWidth() - getMidWidth());
        y = MyMath.clamp(y, -world.getFloor().getMidHeight() + getMidHeight(), world.getFloor().getMidHeight() - getMidHeight());
    }


    protected abstract void movementLogic();


    @Override
    public void update() {
        movementLogic();
        borderLogic();
        getStats().update();
    }

    @Override
    public void draw(Graphics g) {
        if (exists) super.draw(g);
    }


}
