package GameComponents;

import Util.Callback;

import java.awt.*;

import static java.awt.Color.*;

public class DefaultInstances {

    /** World instances */

    public static World town = new World("Town",5000, 5000, new Color(86, 200, 93), new Color(220, 200, 140));
    public static World houseInterior = new World("House Interior", 1000, 850, new Color(230, 210, 140), new Color(100, 80, 50));
    public static World city = new World("City", 2500, 2500, new Color(170, 170, 170), new Color(110, 220, 130));


    /** Player instance */
    public static Player player = new Player(0, 0, 35, town, YELLOW);


    /** Structure instances */

    public static Structure horizontalRoad = new Structure("Vertical Road", 0, 0, town.getWidth(), 200, town, DARK_GRAY);
    public static Structure verticalRoad = new Structure("Vertical Road", 0, 0, 200, town.getHeight(), town, DARK_GRAY);


    public static Structure house = new Structure("House", 500, 400, 250, 200, town, new Color(100, 80, 50));

    public static Structure cash = new Structure("$", town.getRandX(), town.getRandY(), 40, 25, town, new Color(100, 150, 100), new Callback[]{()->player.pay(5)}, null, true);

    public static Structure lavaPit = new Structure("Lava Pit", 1000, 1000, 300, 300, town, new Color(255, 159, 0), new Callback[]{()->player.heal(-4)}, null, false);


    public static Structure houseDoor = new Structure("House Door", houseInterior.getMidWidth(), 0, 20, 150, houseInterior, new Color(190, 170, 80));
    public static Structure houseBed = new Structure ("House Bed", 200, 300, 300, 100, houseInterior, Color.BLUE, new Callback[]{()->player.energize(3)}, null, false);




    /** Portal instances */

    public static Portal housePortal = new Portal(house, houseDoor, false);



}
