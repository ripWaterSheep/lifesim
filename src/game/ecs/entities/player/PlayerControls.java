package game.ecs.entities.player;

import com.sun.tools.javac.Main;
import game.controls.KeyboardControls;
import game.ecs.components.*;
import game.controls.MouseControls;
import game.ecs.entities.Entity;
import javafx.geometry.Pos;
import main.MainPanel;
import util.Geometry;

import static game.controls.KeyboardControls.*;
import static java.lang.Math.sqrt;

public class PlayerControls {

    private Player player;

    PlayerControls(Player player) {
        this.player = player;
    }


    public void run() {
        sprintControls();
        movementControls();
        weaponControls();
    }


    private void movementControls() {

        boolean up = false, down = false, left = false, right = false;

        if (getLeftPressed() && getRightPressed()) {
            if (getLeftReadTime() > getRightReadTime()) right = true;
            else left = true;
        } else if (getLeftPressed()) left = true;
        else if (getRightPressed()) right = true;

        if (getUpPressed() && getDownPressed()) {
            if (getUpReadTime() > getDownReadTime()) up = true;
            else down = true;
        } else if (getUpPressed()) up = true;
        else if (getDownPressed()) down = true;

        for (MovementComponent movement : player.getAll(MovementComponent.class)) {
            movement.setAngle(getIntendedAngle(up, down, left, right));
            movement.setMoving(up || down || left || right);
        }
    }


    private double getIntendedAngle(boolean up, boolean down, boolean left, boolean right) {

        double angle = 0;
        // Get angles for different key directions (makes going diagonal the same speed as horizontal or vertical)
        if (up) {
            if (left) angle = 45;
            else if (right) angle = 135;
            else angle = 90;
        } else if (down) {
            if (left) angle = 315;
            else if (right) angle = 225;
            else angle = 270;
        } else if (right) angle = 180;
        else if (left) angle = 0;

        return angle;
    }


    private void sprintControls() {
        if (getSpacePressed()) {
            player.get(MovementComponent.class).setSpeedRatio(1.5);
        }
    }



    private void weaponControls() {

        if (MouseControls.getRightClicked()) {
            double strength = player.get(StatsComponent.class).getStrength();

            double size = 9 + (strength / 275);
            double angle = Geometry.getAngleBetween(MouseControls.getLastClickX(), MouseControls.getLastClickY(), main.Main.getPanel().getMidWidth(), main.Main.getPanel().getMidHeight());
            double damage = sqrt((strength / 16) + 1);
            System.out.println(damage);


            if (MouseControls.getRightClicked()) {
                player.getWorld().add(new Entity("Player Bullet")
                        .add(player.getPos().copyCurrentState())
                        .add(new SpatialComponent(size, size, true))
                        .add(new MovementComponent(40, angle))
                        .add(new AttackComponent(damage))
                        .add(new ProjectileComponent(700, true))
                );
                /*player.get(SpawnerComponent.class).attemptSpawn(
                        player.getPos().getX(), player.getPos().getY(), player.getWorld());*/
            }
        }
    }

}
