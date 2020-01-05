package game.components.structures.subtypes;

import game.components.GameComponent;
import game.components.World;
import game.components.player.Player;
import game.components.structures.Structure;
import util.MyMath;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static util.MyMath.*;
import static util.ShapeUtil.testIntersection;


public class MobileEntity extends Structure {

    //TODO: finish movement stuff


    // Not actually used for iterating in the GameSession loop.
    // This is actually just used for other things like collisionLogic() in this class to allow use of uninherited fields and methods
    protected static ArrayList<MobileEntity> instances = new ArrayList<>();


    public enum MovementType { LINEAR, FOLLOW, AVOID }

    private MovementType movementType;

    private final int startX;
    private final int startY;


    private int speed; // Pixels to move per frame
    private int turnAngle;  // How many degrees to turn after getting to the end of the range
    private final int range; // How far to go before turning

    private final int damage;
    private final boolean canDamagePlayer;
    private int cumulativeDamage;

    private int health = -1;


    private boolean started = false;

    private int currentDistance = 0;
    private int currentAngle;
    private boolean currentlyReversed = false;


    private ArrayList<GameComponent> touching = new ArrayList<>(); // This needs to be an actual variable instead of just a function because it needs to track



    public MobileEntity(String label, int x, int y, int width, int height, World world, Color color,
                        MovementType movementType, int speed, int range, int startAngle, int damage,
                        boolean canDamagePlayer, boolean startInitially) {

        super(label, x, y, width, height, world, color);

        instances.add(0, this);
        startX = x;
        startY = y;

        this.speed = speed;
        currentAngle = startAngle;
        this.range = range;
        this.movementType = movementType;
        this.damage = damage;
        this.canDamagePlayer = canDamagePlayer;

        if (startInitially)
            start();
    }


    public MobileEntity(String label, int x, int y, int width, int height, World world, Color color,
                        MovementType movementType, int speed, int range, int startAngle,
                        int damage, int health, boolean canDamagePlayer, boolean startInitially) {

        this(label, x, y, width, height, world, color, movementType, speed, range, startAngle, damage, canDamagePlayer, startInitially);
        this.health = health;


    }



    public void start() {
        this.started = true;
    }


    public void collisionLogic() {

        for (MobileEntity entity: instances) {
            if (testIntersection(getShape(), entity.getShape()))
                if (!touching.contains(entity)) {
                    touching.add(entity);
                    entity.health -= damage;
                    cumulativeDamage += damage;
                }
        }

        if (testIntersection(getShape(), Player.getInstance().getShape())) {
            Player player = Player.getInstance();
            if(canDamagePlayer)
                player.damage(damage);
        }

    }


    private void borderLogic() {
        x = MyMath.clamp(x, -world.getMidWidth() + getMidWidth(), world.getMidWidth() - getMidWidth());
        y = MyMath.clamp(y, -world.getMidHeight() + getMidHeight(), world.getMidHeight() - getMidHeight());
    }


    @Override
    public void setup(JPanel panel) {

    }



    @Override
    public void act() {
        Player player = Player.getInstance();
        if (started) {
            switch (movementType) {
                case LINEAR:
                    x = (currentDistance * (betterRound(Math.sin(Math.toRadians(currentAngle))))) + startX;
                    y = (currentDistance * (betterRound(Math.cos(Math.toRadians(currentAngle))))) + startY;

                    if (currentlyReversed)
                        currentDistance += speed;
                    else
                        currentDistance -= speed;

                    // Flip around when at edge of range
                    if(Math.abs(currentDistance) <= 0 || Math.abs(currentDistance) >= range) {
                        currentlyReversed = !currentlyReversed;
                        cumulativeDamage = 0;
                    }

                    System.out.println(currentDistance);
                    System.out.println(currentlyReversed);

                    break;

                case FOLLOW:
                    x += clampSigned(player.getX() - x, 1, speed);
                    y += clampSigned(player.getY() - y, 1, speed);
                    break;

                case AVOID:
                    x -= clampSigned(player.getX() - x, 1, speed);
                    y -= clampSigned(player.getY() - y, 1, speed);
                    break;
            }
        }

        borderLogic();
        collisionLogic();

    }



}


