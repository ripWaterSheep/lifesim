package lifesim.main.game.entities.components.stats;

import lifesim.main.game.entities.Entity;
import lifesim.main.game.entities.components.Drops;
import lifesim.main.game.handlers.World;
import lifesim.main.util.math.Geometry;
import lifesim.main.util.math.MyMath;
import lifesim.main.util.math.Vector2D;

import java.awt.*;

public class HealthStats extends BasicStats implements Stats {

    boolean alive = true;
    protected double health;
    protected final double initialHealth;


    private final Drops drops = new Drops();


    public HealthStats(double damage, Alliance alliance, double health) {
        super(damage, alliance);
        this.health = health;
        initialHealth = health;
    }


    @Override
    public boolean isAlive() {
        return health > 0;
    }


    @Override
    public boolean hasHealth() {
        return true;
    }


    @Override
    public double getHealth() {
        return health;
    }

    @Override
    public void heal(double amount) {
        health += amount;
    }

    @Override
    public void takeDamage(double damage) {
        health -= damage;
    }

    @Override
    public void update(Entity entity, World world) {
        super.update(entity, world);

        if (health <= 0 && alive) {
            alive = false;
            drops.dropAt(entity.getPos(), world);
            entity.removeFromWorld();
        }

        health = MyMath.clamp(health, 0, initialHealth);
    }


    protected void renderStatBar(Graphics2D g2d, Vector2D pos, double currentVal, double maxVal, Color c1) {
        final double widthScale = 0.3;
        final int height = 1;

        Rectangle rect = Geometry.getCenteredRect(pos, new Vector2D(maxVal*widthScale, height));
        g2d.setColor(Color.BLACK);
        g2d.fill(rect);

        rect = new Rectangle(rect.x, rect.y, (int) (currentVal*widthScale), height);
        g2d.setColor(c1);
        g2d.fill(rect);
    }

    @Override
    public void renderInfo(Graphics2D g2d, Vector2D pos) {
        super.renderInfo(g2d, pos);
        renderStatBar(g2d, pos, health, initialHealth, alliance.teamColor);
    }

}
