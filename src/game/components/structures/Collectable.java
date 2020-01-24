package game.components.structures;

import game.components.World;
import game.components.entities.player.Player;

import java.awt.*;

import static game.activity.collision.CollisionLogic.collectableCollisionLogic;


public class Collectable extends Structure {


    public Collectable(String name, double width, double height, Color color, int fontSize) {
        super(name, 0, 0, width, height, color, fontSize);

    }
    public Collectable(String name, double width, double height, Color color) {
        this(name, width, height, color, 0);
    }



    public Collectable(String name, double scale, String imageName) {
        super(name, 0, 0, scale, imageName);
    }


    @Override
    public Collectable setWorld(World world) {
        this.world = world;
        randomizePos();
        return this;
    }


    public void randomizePos() {
        x = world.getRandX();
        y = world.getRandY();
    }


    public void onTouch() {}

    public void onClick() {}


    @Override
    public void init() {
        player = Player.getInstance();
        stats = player.getStats();
    }


    @Override
    public void update() {
        collectableCollisionLogic(this);
    }

}


