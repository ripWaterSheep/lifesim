package lifesim.main.game.entities.types;

import lifesim.main.game.entities.AttackEntity;
import lifesim.main.game.entities.Entity;
import lifesim.main.game.entities.RangedAttackEntity;
import lifesim.main.game.entities.components.Vector2D;
import lifesim.main.game.entities.components.sprites.AnimatedSprite;
import lifesim.main.game.entities.components.sprites.Animation;
import lifesim.main.game.entities.components.stats.Alliance;
import lifesim.main.game.entities.components.stats.HealthStats;


public enum EnemyType implements Spawnable {

    MELEE_1() {
        @Override
        public Entity spawnNew() {
            return new AttackEntity("Emo", new AnimatedSprite(new Animation("emo", 120, new Vector2D(11, 16), 0)),
                    new HealthStats(3, 8, Alliance.ENEMY, 25), 150);
    }},


    RANGED_1() {
        @Override
        public Entity spawnNew() {
            return new RangedAttackEntity("Nerd", new AnimatedSprite(new Animation("nerd", 300, new Vector2D(7, 16), 0)),
                    new HealthStats(2.5, 5, Alliance.ENEMY, 20), 200, 1000, ProjectileType.BALL);
    }}


}
