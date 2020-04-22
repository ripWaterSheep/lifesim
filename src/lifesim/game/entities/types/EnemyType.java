package lifesim.game.entities.types;

import lifesim.game.entities.components.sprites.AnimatedSprite;
import lifesim.game.entities.components.sprites.Animation;
import lifesim.game.entities.AIEntity;
import lifesim.game.entities.Entity;
import lifesim.game.entities.RangedAIEntity;
import lifesim.util.math.Vector2D;
import lifesim.game.entities.components.stats.Alliance;
import lifesim.game.entities.components.stats.HealthStats;


public enum EnemyType implements Spawnable {

    MELEE_1() {
        @Override
        public Entity spawnEntity() {
            return new AIEntity("Emo", new AnimatedSprite(new Animation("emo", 120, new Vector2D(11, 16), 0)),
                    new HealthStats(4, Alliance.ENEMY, 35), 3, 175);
    }},


    RANGED_1() {
        @Override
        public Entity spawnEntity() {
            return new RangedAIEntity("Nerd", new AnimatedSprite(new Animation("nerd", 300, new Vector2D(7, 16), 0)),
                    new HealthStats(0, Alliance.ENEMY, 25), 3.5, 225, 1000, ProjectileType.BALL);
    }}


}
