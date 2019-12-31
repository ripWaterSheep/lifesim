package GameSession;

import GameComponents.Player;
import GameComponents.Structure;
import GameComponents.World;
import Util.MyMath;

import java.awt.*;

import static java.awt.Color.*;

public class DefaultLayout extends GameLayout {

    /** World instances */

    public World town = new World("Town",5000, 5000, new Color(86, 200, 93), new Color(220, 200, 140));
    public World houseInterior = new World("House Interior", 1000, 850, new Color(230, 210, 140), new Color(100, 80, 50));

    public World city = new World("City", 2500, 2500, new Color(190, 188, 169), new Color(138, 255, 150));


    /** Structure instances */

    public Structure horizontalRoad = new Structure("Vertical Road", 0, 0, town.getWidth(), 200, town, DARK_GRAY);
    public Structure verticalRoad = new Structure("Vertical Road", 0, 0, 200, town.getHeight(), town, DARK_GRAY);


    public Structure house = new Structure("House", 500, 400, 250, 200, town, new Color(100, 80, 50));

    public Structure cash = new Structure("$", town.getRandX(), town.getRandY(), 50, 35, town, new Color(100, 150, 100));

    public Structure lavaPit = new Structure("Lava Pit", 1000, 1000, 300, 300, town, new Color(255, 159, 0));


    public Structure houseDoor = new Structure("House Door", houseInterior.getMidWidth(), 0, 20, 150, houseInterior, new Color(190, 170, 80));
    public Structure houseBed = new Structure ("House Bed", 200, 300, 200, 100, houseInterior, Color.BLUE);


    public Structure creditCard = new Structure("VISA", city.getRandX(), city.getRandY(), 35, 25, city, new Color(254, 247, 137));



    @Override
    public void playerTouchInteraction(Structure structure) {
        switch(structure.getLabel()) {

            case ("House Bed"):
                player.energize(1);
                break;

            case "$":
                player.gainMoney(100);
                cash.randomizePos();
                break;
            case "VISA":
                player.gainMoney(MyMath.getRandRange(1, 1000));
                creditCard.randomizePos();
                break;
        }
    }


    @Override
    public void playerTapInteraction(Structure structure) {
        switch(structure.getLabel()) {

            case "House":
                player.goTo(houseDoor);
                break;

            case "House Door":
                player.goTo(house);
                break;
        }
    }


    @Override
    public void playerPressInteraction(Structure objectTouching) {

    }


}
