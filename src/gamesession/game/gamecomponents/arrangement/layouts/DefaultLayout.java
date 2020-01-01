package gamesession.game.gamecomponents.arrangement.layouts;

import gamesession.game.gamecomponents.arrangement.GameLayout;
import gamesession.game.gamecomponents.entities.Entity;
import gamesession.game.gamecomponents.World;
import util.MyMath;

import java.awt.*;

import static java.awt.Color.*;

public class DefaultLayout extends GameLayout {

    /** World instances */

    public World city = new World("City", 2500, 2500, new Color(190, 188, 169), new Color(138, 255, 150));
    public World houseInterior = new World("House Interior", 1000, 850, new Color(230, 210, 140), new Color(100, 80, 50));
    public World town = new World("Town",5000, 5000, new Color(86, 200, 93), new Color(220, 200, 140));


    /** Structure instances */

    public Entity horizontalRoad = new Entity("Vertical Road", 0, 0, town.getWidth(), 200, town, DARK_GRAY);
    public Entity verticalRoad = new Entity("Vertical Road", 0, 0, 200, town.getHeight(), town, DARK_GRAY);


    public Entity house = new Entity("House", 500, -400, 450, 400, town, new Color(100, 80, 50), 30);
    public Entity gym = new Entity("Gym", -1200, -450, 400, 500, town, new Color(100, 100, 100), 40);



    public Entity cash = new Entity("$", town.getRandX(), town.getRandY(), 55, 35, town, new Color(100, 150, 100), 20);

    public Entity lavaPit = new Entity("Lava Pit", 1000, 1000, 300, 300, town, new Color(255, 159, 0));


    public Entity houseDoor = new Entity("House Door", houseInterior.getMidWidth(), 0, 20, 150, houseInterior, new Color(190, 170, 80));
    public Entity houseBed = new Entity("House Bed", 200, 300, 200, 100, houseInterior, Color.BLUE);


    public Entity creditCard = new Entity("VISA", city.getRandX(), city.getRandY(), 35, 25, city, new Color(181, 181, 105), 15);



    @Override
    public void playerTouchInteraction(Entity entity) {
        switch(entity.getLabel()) {

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
    public void playerTapInteraction(Entity entity) {
        switch(entity.getLabel()) {

            case "House":
                player.goTo(houseDoor);
                break;

            case "House Door":
                player.goTo(house);
                break;
        }
    }


}
