package lifesim.game.entities.types;

import lifesim.util.sprites.AnimatedSprite;
import lifesim.util.sprites.Animation;
import lifesim.game.entities.AIEntity;
import lifesim.game.entities.Entity;
import lifesim.game.entities.RangedAIEntity;
import lifesim.util.geom.Vector2D;
import lifesim.game.entities.stats.Alliance;
import lifesim.game.entities.stats.HealthStats;
import lifesim.util.sprites.DirectionalAnimatedSprite;


public enum EnemyType implements Spawnable {

    MELEE_1() {
        @Override
        public Entity spawnEntity() {
            return new AIEntity("Melee Basic", new DirectionalAnimatedSprite(
                    new Animation("melee", 200, new Vector2D(0, 0), new Vector2D(12, 18)),
                    new Animation("melee", 100, new Vector2D(0, 18), new Vector2D(12, 18)),
                    new Animation("melee", 100, new Vector2D(0, 36), new Vector2D(12, 18)),
                    new Animation("melee", 100, new Vector2D(0, 54), new Vector2D(12, 18)),
                    new Animation("melee", 100, new Vector2D(0, 72), new Vector2D(12, 18))
            ),
                    new HealthStats(4, Alliance.ENEMY, 35), 3, 175);
    }},


    RANGED_1() {
        @Override
        public Entity spawnEntity() {
            return new RangedAIEntity("Nerd", new AnimatedSprite(new Animation("nerd", 300,
                    new Vector2D(0, 0), new Vector2D(7, 16))),
                    new HealthStats(0, Alliance.ENEMY, 25), 3.5, 225, 1000, ProjectileType.CANNONBALL);
    }}


}
