package game.setting.layout;

import game.ecs.components.*;
import game.ecs.entities.Entity;
import game.ecs.entities.player.Player;
import game.setting.world.World;

import java.awt.*;




public class DefaultLayout extends Layout {

    public DefaultLayout() {

        createWorld(new World("Town", new Color(201, 193, 126))
                .add(new Entity("Grass")
                        .add(new PositionComponent(0, 0))
                        .add(new AppearanceComponent(new Color(60, 159, 75)))
                        .add(new SpatialComponent(8000, 6000, true))
                )
                .add(new Entity("Road")
                        .add(new PositionComponent(0, 0))
                        .add(new SpatialComponent(7000, 200, false))
                        .add(new SpatialComponent(200, 7000, false))
                        .add(new AppearanceComponent(Color.DARK_GRAY))
                )
                .add(new Entity("House")
                        .add(new PositionComponent(500, -400))
                        .add(new SpatialComponent(450, 350, false))
                        .add(new AppearanceComponent(new Color(201, 190, 127)))
                        .add(new InteractionComponent() {
                            @Override
                            public void teleport(Player player) {
                                player.goToEntity("House Door");
                                System.out.println("EW byb");
                            }
                        })

                )
                .add(new Entity("Gym")
                        .add(new PositionComponent(-1500, -450))
                        .add(new SpatialComponent(800, 500, false))
                        .add(new AppearanceComponent("Gym"))
                        .add(new SolidComponent(false))
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
                                 //player.goTo(getEntity("House"));
                             }
                         })
                )

                .add(new Entity("ZombieSpawner")
                        .add(new PositionComponent(-2200, 300))
                        .add(new SpatialComponent(100, 100, false))
                        .add(new AppearanceComponent(Color.DARK_GRAY))
                        .add(new SpawnerComponent(1000, 500,
                                new Entity("")
                                        .add(new PositionComponent(0, 0))
                                        .add(new SpatialComponent(65, 65, true))
                                        .add(new AppearanceComponent(new Color(80, 103, 78)))
                                        .add(new MovementComponent(11))
                                        .add(new AIComponent(AIComponent.PathFinding.PURSUE, 600, 6))
                                        .add(new HealthComponent(25))
                                        .add(new AttackComponent(5, false, false))
                                ))
                )
        );

        createWorld(new World("House Interior", new Color(100, 80, 50))
                .add(new Entity("Hard Wood Flooring")
                        .add(new PositionComponent(0, 0))
                        .add(new SpatialComponent(1500, 900, false))
                        .add(new AppearanceComponent(new Color(201, 190, 127)))
                        .add(new SolidComponent(true))
                )
                .add(new Entity("House Door")
                        .add(new PositionComponent(0, 900*0.5))
                        .add(new SpatialComponent(150, 20, false))
                        .add(new AppearanceComponent(new Color(184, 163, 71)))
                        .add(new InteractionComponent() {
                            public void interact(StatsComponent stats) {
                                stats.energize(1);
                            }
                            public void teleport(Player player) {
                                player.goToEntity("House");
                            }
                        }))

                .add(new Entity("House Bed")
                    .add(new PositionComponent(-200, -300))
                    .add(new SpatialComponent(250, 150, false))
                    .add(new AppearanceComponent(new Color(0, 115, 169))
                ))
        );
    }
}
