package game.setting.layout;

import game.ECS.components.*;
import game.ECS.entities.Entity;
import game.ECS.entities.Player;
import game.ECS.entities.Structure;
import game.setting.world.World;

import java.awt.*;

import static game.setting.world.BorderTypes.*;


public class DefaultLayout extends Layout {

    public DefaultLayout() {

        createWorld(new World("Town", 5000, 5000, new Color(56, 150, 86), new Color(201, 193, 126), LAVA_ISLAND)
                .add(new Structure("Hi", 0, 0, 100, 100, false, new Color(100, 100, 100)))
                .add(new Structure("Horizontal Road", 0, 0, 6500, 200, false, Color.DARK_GRAY))
                .add(new Structure("Vertical Road", 0, 0, 200, 6500, false, Color.DARK_GRAY))

                .add(new Entity("Gym")
                        .add(new PositionComponent(-1500, -450))
                        .add(new SpatialComponent(800, 500, false))
                        .add(new AppearanceComponent("Gym"))
                        //.add(new AppearanceComponent(Color.GRAY))
                        .add(new InteractionComponent() {

                            @Override public void interact(StatsComponent stats) {
                              stats.strengthen(3);
                              System.out.println("dab");
                            }
                        })
                )
                       /* ("Gym", -1500, -450, 800, 500, false, Color.GRAY)
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
                         public void playerTp(Player player) {
                             player.goTo(getEntity("House"));
                             System.out.println("dab");
                         }
                     })*/
        );

        createWorld(new World("House Interior", 1000, 900, new Color(230, 210, 140), new Color(100, 80, 50), WALLED)
                        .add(new Structure("House Door", 0 , 900*0.5, 150, 20, false, new Color(190, 170, 80))
                                .add(new InteractionComponent() {
                                    @Override
                                    public void interact(StatsComponent stats) {
                                        stats.energize(1);
                                    }

                                    @Override
                                    public void playerTp(Player player) {
                                        player.goTo(getEntity("House"));
                                    }
                                }))
                        .add(new Structure("House Bed", -200, -300, 250, 150,false, new Color(0, 114, 168))
                        )
        );
    }
}
