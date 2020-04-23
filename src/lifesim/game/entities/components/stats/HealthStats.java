package lifesim.game.entities.components.stats;

import lifesim.game.entities.Entity;
import lifesim.game.handlers.World;
import lifesim.game.entities.components.Drops;
import lifesim.util.math.MyMath;
import lifesim.util.math.geom.Rect;
import lifesim.util.math.geom.Vector2D;

import java.awt.*;

public class HealthStats extends BasicStats implements Stats {

    boolean alive = true;
    protected double health;
    protected double maxHealth;


    private final Drops drops = new Drops();


    public HealthStats(double damage, Alliance alliance, double health) {
        super(damage, alliance);
        this.health = health;
        maxHealth = health;
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

        health = MyMath.clamp(health, 0, maxHealth);
    }


    protected void renderStatBar(Graphics2D g2d, Vector2D pos, double currentVal, double maxVal, Color c1) {
        final double widthScale = 0.3;
        final int height = 1;

        Rect rect = new Rect(pos, new Vector2D(maxVal*widthScale, height));
        g2d.setColor(Color.BLACK);
        g2d.fill(rect);

        rect.width = (int) (currentVal*widthScale);//Rect.fromCorner(rect.getCornerPos(), currentVal*widthScale, height);
        g2d.setColor(c1);
        g2d.fill(rect);
    }

    @Override
    public void renderInfo(Graphics2D g2d, Vector2D pos) {
        super.renderInfo(g2d, pos);
        renderStatBar(g2d, pos, health, maxHealth, alliance.teamColor);
    }

}
