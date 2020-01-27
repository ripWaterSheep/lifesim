package game.organization.components.structures;

import game.organization.World;

import java.awt.*;


public class Collectable extends Structure {


    public Collectable(String name, double width, double height, boolean elliptical, Color color, int fontSize) {
        super(name, 0, 0, width, height, elliptical, color, fontSize);

    }

    public Collectable(String name, double width, double height, boolean elliptical, Color color) {
        this(name, width, height, elliptical, color, 0);
    }


    public Collectable(String name, double scale, String imageName) {
        super(name, 0, 0, scale, imageName);
    }


    public void randomizePos(World world) {
        x = (int) (Math.random() * world.getFloor().getMidWidth()) - world.getFloor().getMidWidth();
        y = (int) (Math.random() * world.getFloor().getMidHeight()) - world.getFloor().getMidHeight();
    }


    @Override
    public void init(World world) {
        super.init(world);
        randomizePos(world);
    }


}


