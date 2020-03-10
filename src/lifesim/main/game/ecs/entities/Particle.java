package lifesim.main.game.ecs.entities;

import lifesim.main.game.ecs.components.*;

import java.awt.*;

import static lifesim.main.util.MyMath.getRand;

public class Particle extends Entity {

    public Particle(Entity host, double rate, boolean goingUp, Color color) {
        super("Particle");

        for (PositionComponent pos: host.getAll(PositionComponent.class)) {
            add(pos.copyCurrentState());
        }
        add(new SpatialComponent(getRand(7, 16), getRand(7, 16), true));
        add(new AppearanceComponent(color));

        double angle = getRand(-30, 30);
        if (goingUp) angle -= 180;
        add(new MovementComponent(rate, angle));
        add(new ProjectileComponent(getRand(rate*0.5, rate*2)));
    }


}
