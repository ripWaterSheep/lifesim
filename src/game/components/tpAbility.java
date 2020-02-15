package game.components;

import game.entities.Entity;
import game.entities.Player;
import game.world.World;

public class tpAbility implements IComponent {

    private World destinationWorld;
    private double destinationX;
    private double destinationY;

    private double tpPrice;



    public tpAbility(World world, double x, double y, double tpPrice) {
        destinationWorld = world;
        destinationX = x;
        destinationY = y;
        this.tpPrice = tpPrice;
    }


    public tpAbility(Entity entity, double tpPrice) {
        this(entity.getWorld(), entity.get(Position.class).getX(), entity.get(Position.class).getY(), tpPrice);
    }




    public tpAbility(World world, double tpPrice) {
        this(world, 0, 0, tpPrice);
    }


    public void teleport(Player player) {
        player.get(Position.class).set(destinationX, destinationY);
        player.setWorld(destinationWorld);
        player.get(Stats.class).loseMoney(tpPrice);
    }

}
