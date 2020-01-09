package game;

import game.components.structures.subtypes.MobileEntity;
import game.components.structures.Structure;
import game.components.World;
import game.components.player.Player;
import util.MyMath;

import java.awt.*;


public class GameLayout {



    /** World instances */

    World town = new World("Town",6500, 6500, new Color(86, 200, 93), new Color(220, 200, 140));
    World houseInterior = new World("House Interior", 1000, 850, new Color(230, 210, 140), new Color(100, 80, 50));
    World city = new World("City", 4500, 4500, new Color(180, 182, 168), new Color(138, 255, 150));
    World apartmentInterior = new World("Apartment Interior", 1125, 1575, new Color(220, 200, 140), new Color(200, 140, 50));
    World farm = new World("Farm", 3500, 5000, new Color(80, 220, 120), new Color(200, 190, 70));

    World snowLand = new World("Snow Land", 6000, 6000, new Color(180, 220, 230), new Color(100, 75, 40));
    World heck = new World("Heck", 4500, 4500, new Color(100, 35, 30), new Color(255, 150, 0)); // No swearing on my christian minecraft server
    World cheeseLand = new World("Cheese Land", 3500, 3500, new Color(255, 210, 75), new Color(255, 175, 100));
    World caveLand = new World("Cave Land", 3000, 1000, new Color(90, 90, 90), new Color(25, 25, 25));
    World daddyLand = new World("Daddy Land", 6900, 4200, new Color(255, 195, 240), new Color(255, 50, 170));
    World labInterior = new World("Lab Interior", 1500, 1500, new Color(120, 120, 120), new Color(255, 255, 255));
    World moon = new World("Moon", 2000, 2000, new Color(157, 171, 187), new Color(0, 0, 0));




    /** Structure instances */

    Structure horizontalRoad = new Structure("Vertical Road", 0, 0, town.getWidth(), 200, town, Color.DARK_GRAY);
    Structure verticalRoad = new Structure("Vertical Road", 0, 0, 200, town.getHeight(), town, Color.DARK_GRAY);


    Structure house = new Structure("House", 500, -400, 450, 350, town, new Color(100, 80, 50), 50);


    Structure gym = new Structure("Gym", -1200, -500, 400, 500, town, new Color(100, 100, 100), 50);

    Structure school = new Structure("School", 450, 550, 300, 500, town, new Color(180, 117, 84), 50);
    Structure office = new Structure("Office",  -500, 500, 400, 400, town, new Color(160, 180, 180), 50);
    Structure hospital = new Structure("Hospital", -500, -1200, 500, 500, town, new Color(210, 210, 210),50);
    Structure restaurant = new Structure("Restaurant", -500, -450, 500, 400, town, new Color(255, 213, 125), 50);
    Structure shop = new Structure("Shop", -1350, 500, 600, 400, town, new Color(200, 110, 75), 50);
    Structure townMetro = new Structure("Metro - $100", 600, 2050, 500, 600, town, new Color(100,100, 100),50);


    Structure cave = new Structure("Cave", -2000,  -2000, 650, 300, town, new Color(190, 190, 190));
    Structure lavaPit = new Structure("Lava Pit", 2000, 2000, 1000, 1000, town, new Color(255, 159, 0));

    Structure platform = new Structure("Platform", 2000, 2000, 300, 300, town, new Color(104, 100, 65));



    Structure cash = new Structure("$", town.getRandX(), town.getRandY(), 55, 30, town, new Color(100, 150, 100), 20);



    Structure houseDoor = new Structure("House Door", houseInterior.getMidWidth() , 0, 20, 150, houseInterior, new Color(190, 170, 80));
    Structure houseBed = new Structure("House Bed", 200, 300, 200, 100, houseInterior, new Color(0, 114, 168));



    Structure horizontalStreet = new Structure("Horizontal Street", 0, 0, city.getWidth(), 250, city, new Color(50, 50, 50));
    Structure verticalStreet = new Structure("Vertical Street", 0, 0, 250, city.getHeight(), city, new Color(50, 50, 50));
    Structure bank = new Structure("Bank", 700, -600, 700, 500, city, new Color(25, 150, 75), 50);
    Structure apartment = new Structure("Apartment", -550, -650, 500, 650, city, apartmentInterior.getOuterColor(), 50);
    Structure university = new Structure("University", -650, 650, 600, 600, city, new Color(220, 190, 120), 50);
    Structure museum = new Structure("Museum", 1550, 650, 600, 600, city, new Color(10, 10, 10), 50);
    Structure cityMetro = new Structure("Metro - $100", -700, -1550, 600, 600, city, new Color(100, 100, 100), 50);


    Structure creditCard = new Structure("VISA", city.getRandX(), city.getRandY(), 35, 25, city, new Color(227, 218, 159), 12);



    /** MobileEntity instances */

    MobileEntity zombie = new MobileEntity("Zombie", 1000, 1000, 50, 50, town, Color.LIGHT_GRAY, MobileEntity.MovementType.FOLLOW, 10, 1000, 45, 5, 1000, true);
    MobileEntity boyo = new MobileEntity("Boyo", 500, 500, 50, 50, town, Color.BLUE, MobileEntity.MovementType.AVOID, 6, 1000, 10, 0, 100, false);

    //MobileEntity bruh = new MobileEntity("Bruh", 500, 100, 50, 50, town, Color.RED, MobileEntity.MovementType.RECIPROCATING, 10, 300, 0, 5, 1000, true);



    /** Player instance */
    public Player player = new Player(0, 0, 30, town, Color.YELLOW);



    public void playerCollisionLogic(Structure structure) {
        switch(structure.getLabel()) {

            case "House Bed":
                player.energize(1);
                break;

            case "Gym":
                if (player.hasMoney()) {
                    player.strengthen(1);
                    player.loseMoney(1);
                    player.tire(1);
                }
                break;


            case "Restaurant":
                if (player.hasMoney()) {
                    player.grow(0.15);
                    player.loseMoney(1);
                    player.energize(1);
                }
                break;

            case "School":
                player.gainIntellect(1.5);
                player.tire(0.5);
                break;

            case "Office":
                player.gainMoney(1+(player.getIntellect()/500));
                player.tire(0.75);
                break;

            case "Hospital":
                if (player.canAfford(5)) {
                    player.heal(2);
                    player.energize(2);
                    player.loseMoney(2);
                }
                break;

            case "Lava Pit":
                player.damage(5);
                break;


            case "$":
                player.gainMoney(100);
                cash.randomizePos();
                break;

            case "VISA":
                player.gainMoney(MyMath.getRandInRange(1, 1000));
                creditCard.randomizePos();
                break;

        }
    }



    public void playerInteractLogic(Structure structure) {
        switch(structure.getLabel()) {

            case "House":
                player.goTo(houseDoor);
                break;

            case "House Door":
                player.goTo(house);
                break;


            case "Metro - $100":
                if (player.canAfford(100)) {
                    player.loseMoney(100);
                    if (player.getWorld() == town)
                        player.goTo(cityMetro);
                    else if (player.getWorld() == city)
                        player.goTo(townMetro);
                }
        }
    }


}
