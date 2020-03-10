package lifesim.main.game.setting;

import lifesim.main.game.ecs.components.*;
import lifesim.main.game.ecs.entities.Entity;
import lifesim.main.util.MyMath;

import java.awt.*;
import java.util.ArrayList;


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
                        .add(new SpatialComponent(8000, 7000, false))
                        .add(new SolidComponent(true))
                )
                .add(new Entity("Road")
                        .add(new PositionComponent(0, 0))
                        .add(new SpatialComponent(8000, 250, false))
                        .add(new SpatialComponent(250, 7000, false))
                        .add(new AppearanceComponent(Color.DARK_GRAY))
                )
                .add(new Entity("House")
                        .add(new PositionComponent(600, -500))
                        .add(new SpatialComponent(500, 400, false))
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
                        .add(new PositionComponent(550, 650))
                        .add(new SpatialComponent(400, 600, false))
                        .add(new AppearanceComponent(new Color(180, 117, 84)))
                        .add(new LabelComponent(50))
                        .add(new InteractionComponent() {
                            @Override
                            public void onTouch() {
                                stats.gainIntellect(0.45);
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
                                stats.gainMoney(Math.max(stats.getIntellect()/300, 0.1));
                                stats.tire(0.25);
                            }
                        })
                )
                .add(new Entity("Restaurant")
                        .add(new PositionComponent(-600, -550))
                        .add(new SpatialComponent(500, 450, false))
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
                        .add(new PositionComponent(-1600, -550))
                        .add(new SpatialComponent(850, 500, false))
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
                         })
                )
                .add(new Entity("Hospital")
                        .add(new PositionComponent(-600, -1500))
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
                        .add(new PositionComponent(600, 2200))
                        .add(new SpatialComponent(400, 600, false))
                        .add(new AppearanceComponent(new Color(100, 100, 100)))
                        .add(new LabelComponent(50))
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
                        .add(new PositionComponent(-1500, 1500))
                        .add(new SpatialComponent(100, 100, false))
                        .add(new AppearanceComponent(Color.DARK_GRAY))
                        .add(new SpawnerComponent(2000,
                                new Entity("Zombie")
                                        //.add(new PositionComponent(-1200, 1200))
                                        .add(new SpatialComponent(65, 65, true))
                                        .add(new AppearanceComponent(new Color(80, 103, 78)))
                                        .add(new MovementComponent(10))
                                        .add(new AIComponent(AIComponent.PathFinding.PURSUE, 800, 8))
                                        .add(new HealthComponent(25))
                                        .add(new AttackComponent(3, false, false))
                        ))
                )
                .add(new Entity("$")
                        .add(new PositionComponent(MyMath.getRand(-8000, 8000), MyMath.getRand(-7000, 7000)))
                        .add(new SpatialComponent(65, 40, false))
                        .add(new AppearanceComponent(new Color(100, 150, 100)))
                        .add(new InteractionComponent() {
                            @Override
                            public void onTouch() {
                                stats.gainMoney(100);
                                entity.getPos().set(MyMath.getRand(-8000, 8000), MyMath.getRand(-7000, 7000));
                            }
                        })
                    )
        );

        worlds.add(new World("House Interior", new Color(100, 80, 50))
                .add(new Entity("Hard Wood Flooring")
                        .add(new PositionComponent(0, 0))
                        .add(new SpatialComponent(1500, 900, false))
                        .add(new AppearanceComponent(new Color(201, 190, 127)))
                        .add(new SolidComponent(true))
                )
                .add(new Entity("Hallway")
                        .add(new PositionComponent(1250, 0))
                        .add(new SpatialComponent(1000, 300, false))
                        .add(new AppearanceComponent(new Color(201, 190, 127)))
                        .add(new SolidComponent(true))
                ).add(new Entity("Bedroom")
                        .add(new PositionComponent(2150, 0))
                        .add(new SpatialComponent(800, 800, false))
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
                       .add(new PositionComponent(2300, -250))
                        .add(new SpatialComponent(250, 150, false))
                        .add(new AppearanceComponent(new Color(0, 115, 169)))
                        .add(new InteractionComponent() {
                            @Override
                            public void onTouch() {
                                stats.energize(1.25);
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
                .add(new Entity("VISA")
                        .add(new PositionComponent(MyMath.getRand(-6000, 6000), MyMath.getRand(-6000, 6000)))
                        .add(new SpatialComponent(60, 40, false))
                        .add(new AppearanceComponent(new Color(210, 200, 140)))
                        .add(new LabelComponent(16))
                        .add(new InteractionComponent(){
                            @Override
                            public void onTouch() {
                                stats.gainMoney(MyMath.getRand(1, 1000));
                                entity.getPos().set(MyMath.getRand(-6000, 6000), MyMath.getRand(-6000, 6000));
                            }
                        })
                )
        );

        worlds.add(new World("Farm", new Color(87, 176, 62))

        );

        worlds.add(new World("Moon", new Color(5, 6, 10))
                .add(new Entity("Surface")
                        .add(new PositionComponent(0, 0))
                        .add(new SpatialComponent(3000, 3000, false))
                        .add(new AppearanceComponent(new Color(140, 140, 140)))
                        .add(new SolidComponent(true))
                )
                .add(new Entity("Ken")
                        .add(new PositionComponent(100, 1000))
                        .add(new SpatialComponent(300, 250, false))
                        .add(new AppearanceComponent("kenface"))
                        .add(new MovementComponent(15))
                        .add(new AIComponent(AIComponent.PathFinding.PURSUE, 300, 5))
                        .add(new HealthComponent(1000))
                        .add(new AttackComponent(8, false, false))
                )
        );

        worlds.add(new World("Daddyland", new Color(255, 200, 240))
                .add(new Entity("Joshe")
                        .add(new PositionComponent(-1000, -1000))

                )

        );

    }

}
