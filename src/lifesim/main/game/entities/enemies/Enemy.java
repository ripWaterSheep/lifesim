package lifesim.main.game.entities.enemies;

import lifesim.main.game.Game;
import lifesim.main.game.entities.MovementEntity;
import lifesim.main.game.entities.Player;
import lifesim.main.game.entities.components.sprites.Sprite;
import lifesim.main.game.entities.components.stats.Stats;
import lifesim.main.game.handlers.World;

import static lifesim.main.util.math.Geometry.*;


public class Enemy extends MovementEntity {

    protected final double detectionRange;

    private boolean pursuing = false;
    protected boolean attacking = false;


    public Enemy(String name, Sprite sprite, Stats stats, double speed, double detectionRange) {
        super(name, sprite, stats, speed);
        this.detectionRange = detectionRange;
    }


    private void decideMovement() {
        Player player = Game.getSession().getPlayer();
        pursuing = getDistanceBetween(player.pos, pos) <= detectionRange;
        attacking = isTouching(player);
    }


    @Override
    protected void move() {
        super.move();
        Player player = Game.getSession().getPlayer();

        if (pursuing && !attacking) {
            movement.setMagnDir(defaultSpeed, getAngleBetween(player.pos, pos));
        } else if (attacking) {
            movement.set(movement.scale(0.7));
        }
    }


    @Override
    public void update(World world) {
        super.update(world);
        decideMovement();
    }
}
