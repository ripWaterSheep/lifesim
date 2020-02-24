package game.setting.layout;

import game.ecs.components.*;
import game.ecs.entities.Entity;
import game.ecs.entities.player.Player;
import game.ecs.entities.Structure;
import game.setting.world.World;

import java.awt.*;




public class DefaultLayout extends Layout {

    public DefaultLayout() {

        createWorld(new World("Town", new Color(201, 193, 126))
                .add(new Entity("Grass")
                        .add(new PositionComponent(0, 0))
                        .add(new AppearanceComponent(new Color(60, 159, 75)))
                        .add(new SpatialComponent(2000, 500, false))
                        .add(new SpatialComponent(500, 2000, false))
                        .add(new SolidComponent(true))
                )

                .add(new Entity("Road")
                        .add(new PositionComponent(0, 0))
                        .add(new SpatialComponent(6500, 200, false))
                        .add(new SpatialComponent(200, 6500, false))
                        .add(new AppearanceComponent(Color.DARK_GRAY))
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
                        .add(new PositionComponent(500, 400))
                        .add(new SpatialComponent(100, 100, false))
                        .add(new AppearanceComponent(Color.DARK_GRAY))
                        .add(new SpawnerComponent(1000, 500,
                                new Entity("")
                                        .add(new PositionComponent(0, 0))
                                        .add(new SpatialComponent(100, 100, true))
                                        .add(new AppearanceComponent(new Color(80, 103, 78)))
                                        .add(new MovementComponent(10))
                                        .add(new AIComponent(AIComponent.PathFinding.PURSUE, 400, true))
                                        .add(new HealthComponent(25))
                                        .add(new AttackComponent(5, false, false))
                                ))
                )
        );

        createWorld(new World("House Interior", new Color(100, 80, 50))
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
                        .add(new Structure("House Bed", -200, -300, 250, 150,false, new Color(0, 114, 168)))
        );
    }
}
