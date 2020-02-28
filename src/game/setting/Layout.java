package game.setting;

import game.ecs.components.*;
import game.ecs.entities.Entity;
import game.ecs.entities.player.Player;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;


public class Layout {

    private ArrayList<World> worlds = new ArrayList<>();

    public ArrayList<World> getWorlds() {
        return worlds;
    }


    public Layout() {

        worlds.add(new World("Town", new Color(201, 193, 126))
                .add(new Entity("Grass")
                        .add(new PositionComponent(0, 0))
                        .add(new AppearanceComponent(new Color(60, 159, 75)))
                        .add(new SpatialComponent(8000, 7000, true))
                )
                .add(new Entity("Road")
                        .add(new PositionComponent(0, 0))
                        .add(new SpatialComponent(8000, 20, false))
                        .add(new SpatialComponent(250, 7000, false))
                        .add(new AppearanceComponent(Color.DARK_GRAY))
                )
                .add(new Entity("House")
                        .add(new PositionComponent(600, -500))
                        .add(new SpatialComponent(450, 350, false))
                        .add(new AppearanceComponent(new Color(100, 80, 50)))
                        .add(new LabelComponent(50))
                        .add(new InteractionComponent() {
                            @Override
                            public void onClick() {
                                player.goToEntity("House Door");
                            }
                        })
                )
                .add(new Entity("School")
                        .add(new PositionComponent(650, 700))
                        .add(new SpatialComponent(400, 600, false))
                        .add(new AppearanceComponent(new Color(180, 117, 84)))
                        .add(new LabelComponent(50))
                        .add(new InteractionComponent() {
                            @Override
                            public void onTouch() {
                                stats.gainIntellect(1);
                                stats.tire(0.2);
                            }
                        })
                )
                .add(new Entity("Office")
                        .add(new PositionComponent(-600, 600))
                        .add(new SpatialComponent(500, 500, false))
                        .add(new AppearanceComponent(new Color(150, 150, 160)))
                        .add(new LabelComponent(50))
                        .add(new InteractionComponent() {
                            @Override
                            public void onTouch() {
                                stats.gainMoney(Math.max(stats.getIntellect()/350, 0.1));
                                stats.tire(0.25);
                            }
                        })
                )
                .add(new Entity("Restaurant")
                        .add(new PositionComponent(-600, -550))
                        .add(new SpatialComponent(500, 400, false))
                        .add(new AppearanceComponent( new Color(255, 213, 125)))
                        .add(new LabelComponent(50))
                        .add(new InteractionComponent(){
                            @Override
                            public void onTouch() {
                                if (stats.hasEnoughMoney(0.5) ) {
                                    stats.energize(1);
                                    stats.loseMoney(0.5);
                                }
                            }
                        })
                )
                .add(new Entity("Gym")
                        .add(new PositionComponent(-1650, -550))
                        .add(new SpatialComponent(800, 500, false))
                        .add(new AppearanceComponent("Gym"))
                        .add(new InteractionComponent() {
                            @Override
                             public void onTouch() {
                                 if (stats.hasEnoughMoney(1.125)) {
                                     stats.strengthen(1);
                                     stats.loseMoney(1.25);
                                     stats.tire(0.5);
                                 }
                             }
                             @Override
                             public void onClick() {
                                 player.goToEntity("House");
                             }
                         })
                )
                .add(new Entity("Hospital")
                        .add(new PositionComponent(-600, -1350))
                        .add(new SpatialComponent(500, 500, false))
                        .add(new AppearanceComponent(new Color(210, 210, 210)))
                        .add(new LabelComponent(50))
                        .add(new InteractionComponent(){
                            public void onTouch() {
                                if (stats.hasEnoughMoney(3)) {
                                    health.gainHealth(2);
                                    stats.energize(2);
                                    stats.loseMoney(2);
                                }
                            }
                        })
                )
                .add(new Entity("Train Station")
                        .add(new PositionComponent(700, 2100))
                        .add(new SpatialComponent(600, 400, false))
                        .add(new AppearanceComponent(new Color(100, 100, 100)))
                        .add(new InteractionComponent() {
                            public void onClick() {
                                if (stats.hasEnoughMoney(10000)) {
                                    stats.loseMoney(10000);
                                    player.goToWorld("City");
                                }
                            }
                        })
                )
                .add(new Entity("Zombie Spawner")
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
        worlds.add(new World("House Interior", new Color(100, 80, 50))
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
                            @Override
                            public void onClick() {
                                player.goToEntity("House");
                            }
                        }))

                .add(new Entity("House Bed")
                       .add(new PositionComponent(-200, -300))
                        .add(new SpatialComponent(250, 150, false))
                        .add(new AppearanceComponent(new Color(0, 115, 169)))
                        .add(new InteractionComponent() {
                            @Override
                            public void onTouch() {
                                stats.energize(1.5);
                            }
                        })
                )
        );

        worlds.add(new World("City", new Color(100, 205, 131))
                .add(new Entity("City Sidewalk")
                        .add(new PositionComponent(0, 0))
                        .add(new SpatialComponent(6000, 6000, false))
                        .add(new AppearanceComponent(new Color(180, 180, 180)))
                )
                .add(new Entity("Road")
                        .add(new PositionComponent(0, 0))
                        .add(new SpatialComponent(6000, 200, false))
                        .add(new SpatialComponent(200, 6000, false))
                        .add(new AppearanceComponent(Color.DARK_GRAY))
                )
        );

    }

}
