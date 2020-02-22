package game.setting.layout;

import game.ecs.components.*;
import game.ecs.entities.Entity;
import game.ecs.entities.player.Player;
import game.ecs.entities.Structure;
import game.setting.world.World;

import java.awt.*;

import static game.setting.world.BorderTypes.*;


public class DefaultLayout extends Layout {

    public DefaultLayout() {

        createWorld(new World("Town", 5000, 5000, new Color(60, 159, 75), new Color(201, 193, 126), LAVA_ISLAND)
                //.add(new Structure("Hi", 0, 0, 100, 100, false, new Color(100, 100, 100)))
                //.add(new Structure("Horizontal Road", 0, 0, 6500, 200, false, Color.DARK_GRAY))
                //.add(new Structure("Vertical Road", 0, 0, 200, 6500, false, Color.DARK_GRAY))


                .add(new Entity("Gym")
                        .add(new PositionComponent(-1500, -450))
                        .add(new SpatialComponent(800, 500, false))
                        .add(new AppearanceComponent("Gym"))
                        //.add(new AppearanceComponent(Color.GRAY))

                        .add(new InteractionComponent() {
                            @Override
                             public void interact(StatsComponent stats) {
                                 if (stats.hasEnoughMoney(1.125)) {
                                     stats.strengthen(1);
                                     stats.loseMoney(1.25);
                                     stats.tire(0.5);
                                 }
                             }
                             @Override
                             public void teleport(Player player) {
                                 player.goTo(getEntity("House"));
                                 System.out.println("dab");
                             }
                         })
                )

                .add(new Entity("Creature test")
                        .add(new PositionComponent(250, 250))
                        .add(new SpatialComponent(100, 100, false))
                        .add(new AppearanceComponent(Color.BLUE))
                        .add(new MovementComponent(6))
                        .add(new AIComponent(AIComponent.PathFinding.PURSUE, 400, true))
                        .add(new HealthComponent(10))
                        .add(new AttackComponent(5))
                    )
        );

        createWorld(new World("House Interior", 1000, 900, new Color(230, 210, 140), new Color(100, 80, 50), WALLED)
                        .add(new Structure("House Door", 0 , 900*0.5, 150, 20, false, new Color(190, 170, 80))
                                .add(new InteractionComponent() {
                                    @Override
                                    public void interact(StatsComponent stats) {
                                        stats.energize(1);
                                    }

                                    @Override
                                    public void teleport(Player player) {
                                        player.goTo(getEntity("House"));
                                    }
                                }))
                        .add(new Structure("House Bed", -200, -300, 250, 150,false, new Color(0, 114, 168))
                        )
        );
    }
}
