package game.components.entities;

import game.activity.collision.CollisionLogic;
import game.components.GameComponent;
import game.components.World;
import game.components.entities.player.Player;
import game.components.structures.Structure;
import util.Drawing.DrawString;
import util.Drawing.MyFont;
import util.MyMath;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

public abstract class Entity extends GameComponent {

    // Contains all subclass instances as well as Entity instances
    private static ArrayList<Entity> entityInstances = new ArrayList<>();

    public static ArrayList<Entity> getEntityInstances() { return entityInstances; }


    protected double speed; // Pixels to move per frame
    protected double angle = 0;

    protected Stats stats;

    public Stats getStats() { return stats; }



    @Override
    public Ellipse2D.Double getShape() { return new Ellipse2D.Double(getDisplayX(), getDisplayY(), width, height); }



    protected Entity(String name, double x, double y, double radius, Color color, double speed, double health, double damage, boolean canDamagePlayer) {
        super(name, x, y, radius*2, radius*2, color);
        entityInstances.add(this);

        this.speed = speed;
        this.stats = new Stats(this, health, damage, canDamagePlayer, 0);
    }


    public Entity setWorld(World world) {
        this.world = world;
        return this;
    }


    protected void moveTowardsAngle() {
        x -= (speed*Math.cos(Math.toRadians(angle)));
        y -= (speed*Math.sin(Math.toRadians(angle)));
    }


    /** Keep entity within the world barrier. */
    protected void borderLogic() {
        x = MyMath.clamp(x, -world.getMidWidth() + getMidWidth(), world.getMidWidth() - getMidWidth());
        y = MyMath.clamp(y, -world.getMidHeight() + getMidHeight(), world.getMidHeight() - getMidHeight());
    }

    protected abstract void movementLogic();


    @Override
    public void init() {  }



    @Override
    public void update()  {
        if (getStats().isAlive()) {
            if (Player.getInstance().getWorld() == world) movementLogic();
        }
        borderLogic();
        getStats().update();
    }


    @Override
    public void draw(Graphics g) {
        super.draw(g);
        DrawString.drawCenteredString(g, getStats().getHealth()+"", new Rectangle(getDisplayX(), getDisplayY(), (int)width, (int)height), new Font(MyFont.getMainFont(), Font.PLAIN, 25), Color.WHITE);

    }



}
