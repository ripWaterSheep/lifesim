package lifesim.main.game.entities;

import javafx.geometry.Pos;
import lifesim.main.game.Game;
import lifesim.main.game.GamePanel;
import lifesim.main.game.entities.player.Player;
import lifesim.main.game.setting.World;
import lifesim.main.util.drawing.Sprite;
import lifesim.main.util.math.Vector2D;

import java.awt.*;

import static lifesim.main.util.math.Geometry.testIntersection;


public abstract class Entity {

    public final String name;
    public final Vector2D pos;
    public final Sprite sprite;

    public Entity(String name, Sprite sprite, Vector2D pos) {
        this.name = name;
        this.sprite = sprite;
        this.pos = pos;
    }

    public Shape getHitBox() {
        return sprite.getShapeAt(getDisplayPos());
    }

    public boolean isTouching(Entity entity) {
        return testIntersection(getHitBox(), entity.getHitBox());
    }


    public Vector2D getDisplayPos() {
        Player player = Game.getSession().getPlayer();
        GamePanel panel = Game.getPanel();
        return new Vector2D(pos.getTranslated(player.pos.getScaled(-1).getTranslated(sprite.size.scale(-0.5).getTranslated(panel.getDimensions()))));
    }

    public abstract void update(World world);

    public abstract void collision(Entity entity);

    public void render(Graphics2D g2d) {

        sprite.draw(g2d, getDisplayPos());
    }

}
