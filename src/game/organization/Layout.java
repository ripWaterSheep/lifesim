package game.organization;

import game.components.entities.Projectile;
import game.components.entities.RangedCreature;
import game.components.structures.Collectable;
import game.components.structures.Spawner;
import game.components.structures.Structure;
import game.components.entities.Creature;
import game.components.entities.player.Player;

import java.awt.*;

import static util.MyMath.getRand;


public class Layout {


    World town = new World("Town", 6500, 6500, new Color(86, 200, 93), new Color(220, 200, 140))
            .add(new Player(0, 0))
            .add(new Structure("Horizontal Road", 0, 0, 6500, 200, false, Color.DARK_GRAY))
            .add(new Structure("Vertical Road", 0, 0, 200, 6500, false, Color.DARK_GRAY))

            .add(new Structure("House", 500, -400, 450, 350, false,  new Color(100, 80, 50), 50) {
                public void onClick() {
                    player.goToStructure("House Door");
                }
            })
            .add(new Spawner("Zombie Spawner", -2500 , 2500, 350, 250,false, new Color(45, 59, 43), 9000,
                    new Creature("Zombie", 65, 65, true, new Color(83, 105, 70), Creature.Behaviors.PURSUE, 11, 800, 25, 5, false, 100)
            ))
            .add(new Structure("Gym", -1500, -450, 2.5, "Gym") {
                public void onTouch() {
                    if (stats.hasMoney()) {
                        stats.strengthen(1);
                        stats.loseMoney(1.25);
                        stats.tire(0.5);
                    }
                }
            })
            .add(new Structure("School", 550, 600, 350, 550, false, new Color(180, 117, 84), 50) {
                public void onTouch() {
                    stats.gainIntellect(1);
                    stats.tire(0.2);
                }
            })
            .add(new Structure("Office", -500, 500, 400, 400, false, new Color(150, 150, 160), 50) {
                public void onTouch() {
                    stats.gainMoney((stats.getIntellect()/400)+0.1);
                    stats.tire(0.25);
                }
            })
            .add(new Structure("Restaurant", -500, -450, 500, 400, true, new Color(255, 213, 125), 50) {
                public void onTouch() {
                    if (stats.hasMoney()) {
                        //stats.grow(0.05);
                        stats.loseMoney(0.5);
                        stats.energize(2);
                    }
                }
            })
            .add(new Structure("Hospital", -500, -1250, 500, 500, false, new Color(210, 210, 210),50) {
                public void onTouch() {
                    if (stats.canAfford(3)) {
                        stats.heal(2);
                        stats.energize(2);
                        stats.loseMoney(2);
                    }
                }
            })
            .add(new Structure("Shop", -1350, 500, 600, 400, false,  new Color(200, 110, 75), 50))
            .add(new Structure("Teleporter - $10000", 600, 2050, 500, 600, false, new Color(100,100, 100),50) {
                public void onClick() {
                    if (stats.canAfford(10000)) {
                        stats.loseMoney(10000);
                        player.goToWorld(city);
                    }
                }
            })
            .add(new Structure("Cave", -2000, -2000, 650, 300,true, new Color(190, 190, 190)))
            .add(new Structure("Lava Pit", 2000, 2000, 1000, 1000, true, new Color(255, 159, 0)) {
                public void onTouch() {
                    stats.takeDamage(5);
                }
            })
            .add(new Structure("Safety Platform", 2000, 2000, 300, 300, false, new Color(104, 100, 65)))
            .add(new Collectable("$", 55, 30, false, new Color(100, 150, 100), 20) {
                public void onTouch() {
                    stats.gainMoney(100);
                }
            })
            .add(new Creature("yourDad", 500, 500, 36, 36, false, Color.BLUE, Creature.Behaviors.EVADE, 25, 700, 10, 10, true, 10000))
                        ;

    World houseInterior = new World("House Interior", 1000, 900, new Color(230, 210, 140), new Color(100, 80, 50))
            .add(new Structure("House Door", 0 , 900*0.5, 150, 20, false, new Color(190, 170, 80)){
                public void onClick() {
                    player.goToStructure("House");
                }
            })
            .add(new Structure("House Bed", -200, -300, 250, 150,false, new Color(0, 114, 168)) {
                public void onTouch() {
                    stats.energize(1);
                }
            })
            ;

    World city = new World("City", 5000, 5000, new Color(180, 180, 180), new Color(138, 255, 150))
            .add(new Structure("Horizontal Street", 0, 0, 5000, 250, false,  new Color(50, 50, 50)))
            .add(new Structure("Vertical Street", 0, 0, 250, 5000, false, new Color(50, 50, 50)))

            .add(new Structure("Bank", 700, -600, 700, 500, false,  new Color(25, 150, 75), 50) {
                public void onTouch() {
                    stats.gainMoney(stats.getIntellect()/800);
                }
            })
            .add(new Structure("Apartment", -550, -650, 450, 650, false, new Color(200, 140, 50), 50) {
                public void onClick() {
                    player.goToStructure("Apartment Door");
                }
            })
            .add(new Structure("University", -650, 650, 600, 600, false, new Color(220, 190, 120), 50) {
                public void onTouch() {
                    if (stats.canAfford(1)) {
                        stats.loseMoney(1);
                        stats.gainIntellect(2);
                    }
                }
            })
            .add(new Structure("Museum", 650, 650, 600, 600, false, new Color(10, 10, 10), 50))
            .add(new Collectable("VISA", 50, 35, false, new Color(209, 199, 139), 15) {
                public void onTouch() {
                    stats.gainMoney(getRand(1, 1000));
                }
            })
            .add(new Spawner("Prison", -1700, -1900, 700, 550, false, new Color(110, 110, 110), 50, 7000,
                    new RangedCreature("Skelebro", 0, 0, 50, 50, true, new Color(210, 210, 210), Creature.Behaviors.PURSUE,
                        11, 700, 15, 1, false, 100, 1000,
                        new Projectile("Arrow", 15, 15, true, new Color(80, 80, 80), 35, 500, 40, false))
                ))
            ;

    World apartmentInterior = new World("Apartment Interior", 1000, 1200, new Color(220, 200, 140), new Color(200, 140, 50))
            .add(new Structure("Apartment Door", 1000*0.5 , 0, 20, 150, false, new Color(77, 57, 38)) {
                public void onClick() {
                    player.goToStructure("Apartment");
                }
            })
            .add(new Structure("Apartment Bed", -400, -400, 150, 250,false, new Color(168, 69, 79)) {
                public void onTouch() {
                    stats.energize(1.5);
                }
            })
            ;

    World farm = new World("Farm", 3500, 5000, new Color(80, 220, 120), new Color(200, 190, 70));

    World snowLand = new World("Snow Land", 6000, 6000, new Color(180, 220, 230), new Color(100, 75, 40));
    World heck = new World("Heck", 4500, 4500, new Color(100, 35, 30), new Color(255, 150, 0)); // No swearing on my christian minecraft server
    World cheeseLand = new World("Cheese Land", 3500, 3500, new Color(255, 210, 75), new Color(255, 175, 100));
    World caveLand = new World("Cave Land", 5000, 3000, new Color(90, 90, 90), new Color(25, 25, 25))
            .add(new Creature("Sub", -750, 500, 0.4, "sub", Creature.Behaviors.EVADE, 22, 800, 100, 13, false, 2000))
            ;
    World daddyLand = new World("Daddy Land", 6900, 4200, new Color(255, 195, 240), new Color(255, 50, 170))
            .add(new RangedCreature("Joshe", 4000, 3000, 0.76, "gotcha", Creature.Behaviors.EVADE, 25, 800, 500, 25, false, 3000, 1000,
                    new Projectile("Joshe Ball", 20, 20, true, new Color(0, 0, 0), 65, 1000, 50, false)
            ))
    ;
    World labInterior = new World("Lab Interior", 1500, 1500, new Color(120, 120, 120), new Color(255, 255, 255));
    World moon = new World("Moon", 2000, 2000, new Color(157, 171, 187), new Color(0, 0, 0))
            .add(new Creature("Ken", 100, 1000, 0.2, "kenface", Creature.Behaviors.PURSUE, 15, 300,1000,8, false, 5000))
            ;


}
