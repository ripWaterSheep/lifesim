package game.components.structures;

import game.components.World;
import game.components.entities.player.Player;
import util.MyMath;

import java.awt.*;

public class Coin extends Structure {

    double cashAmount;

    boolean randomizeCash;

    double minCash;
    double maxCash;

    public Coin(String name, double x, double y, double width, double height, World world, Color color, int fontSize, double cashAmount) {
        super(name, x, y, width, height, world, color, fontSize);

        this.cashAmount = cashAmount;
        randomizeCash = false;
    }


    public Coin(String name, double x, double y, double width, double height, World world, Color color, int fontSize, double minCash, double maxCash) {
        super(name, x, y, width, height, world, color, fontSize);

        this.minCash = minCash;
        this.maxCash = maxCash;
        randomizeCash = true;
    }



    public void collect() {
        if (randomizeCash) {
            cashAmount = MyMath.getRandInRange(minCash, maxCash);
            Player.getInstance().gainMoney(cashAmount);

        } else {
            Player.getInstance().gainMoney(cashAmount);
        }
        randomizePos();

    }


}
