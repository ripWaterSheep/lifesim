package lifesim.main.game.entities.enemies;

import lifesim.main.game.Main;
import lifesim.main.game.entities.MovementEntity;
import lifesim.main.game.entities.Player;
import lifesim.main.game.entities.components.sprites.Sprite;
import lifesim.main.game.entities.components.stats.Stats;
import lifesim.main.game.handlers.World;

import static lifesim.main.util.math.Geometry.*;
import static lifesim.main.util.math.MyMath.getRand;


public class Enemy extends MovementEntity {

    protected final double detectionRange;

    private boolean pursuing = false;
    protected boolean attacking = false;


    public Enemy(String name, Sprite sprite, Stats stats, double speed, double detectionRange) {
        super(name, sprite, stats, speed);
        this.detectionRange = detectionRange;
    }


    @Override
    public Enemy copyInitialState() {
        return new Enemy(name, sprite, stats.copyInitialState(), defaultSpeed, detectionRange);
    }


    private void decideMovement() {
        Player player = Main.getGame().getPlayer();
        pursuing = getDistanceBetween(player.pos, pos) <= detectionRange;
        attacking = isTouching(player);
    }


    @Override
    protected void move() {
        super.move();
        Player player = Main.getGame().getPlayer();

        // Move towards player if enemy is pursuing the player.
        if (pursuing && !attacking) {
            movement.setMagnDir(defaultSpeed, getAngleBetween(player.pos, pos)+getRand(-30, 30));
        } else if (attacking) {
            // Decelerate smoothly if on top of player because enemy doesn't need to be centered, just touching player.
            movement.set(movement.scale(0.7));
        } else {
            // Move randomly if not pursuing or attacking the player, changing directions every so often.
            if (getRand(0, 1) < 0.02)
                movement.setMagnDir(defaultSpeed, getRand(0, 360));
        }
    }


    @Override
    public void update(World world) {
        super.update(world);
        decideMovement();
    }
}
