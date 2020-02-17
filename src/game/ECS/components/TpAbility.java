package game.ECS.components;

import game.ECS.entities.Entity;
import game.ECS.entities.Player;
import game.setting.world.World;

public class TpAbility implements IComponent {

    private World destinationWorld;
    private double destinationX;
    private double destinationY;

    private double tpPrice;



    public TpAbility(World world, double x, double y, double tpPrice) {
        destinationWorld = world;
        destinationX = x;
        destinationY = y;
        this.tpPrice = tpPrice;
    }


    public TpAbility(Entity entity, double tpPrice) {
        this(entity.getWorld(), entity.get(Position.class).getX(), entity.get(Position.class).getY(), tpPrice);
    }


    public TpAbility(World world, double tpPrice) {
        this(world, 0, 0, tpPrice);
    }


    public void teleport(Player player) {
        player.get(Position.class).set(destinationX, destinationY);
        player.setWorld(destinationWorld);
        player.get(Stats.class).loseMoney(tpPrice);
    }

    @Override
    public TpAbility copy() {
        return new TpAbility(destinationWorld, destinationX, destinationY, tpPrice);
    }
}
