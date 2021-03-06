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

    MELEE() {
        @Override
        public Entity spawnEntity() {
            return new AIEntity("Melee enemy", new DirectionalAnimatedSprite(
                    new Animation("bot", 200, new Vector2D(0, 0), new Vector2D(13, 16)),
                    new Animation("bot", 100, new Vector2D(0, 16), new Vector2D(13, 16)),
                    new Animation("bot", 100, new Vector2D(0, 32), new Vector2D(13, 16)),
                    new Animation("bot", 100, new Vector2D(6, 48), new Vector2D(10, 16)),
                    new Animation("bot", 100, new Vector2D(6, 64), new Vector2D(10, 16))
            ), new HealthStats(1, Alliance.ENEMY, 50), 3, 175);
    }},


    RANGED() {
        @Override
        public Entity spawnEntity() {
            return new RangedAIEntity("Ranged", new DirectionalAnimatedSprite(
                    new Animation("ranged", 150, new Vector2D(0, 0), new Vector2D(12, 16)),
                    new Animation("ranged", 150, new Vector2D(0, 16), new Vector2D(12, 16)),
                    new Animation("ranged", 150, new Vector2D(0, 32), new Vector2D(12, 16)),
                    new Animation("ranged", 150, new Vector2D(8, 48), new Vector2D(8, 16)),
                    new Animation("ranged", 150, new Vector2D(8, 64), new Vector2D(8, 16))
            ), new HealthStats(0, Alliance.ENEMY, 65), 2.85, 225, 1000, ProjectileType.LASER);
    }},


    CUBORG() {
        @Override
        public Entity spawnEntity() {
            return new RangedAIEntity("Cuborg", new DirectionalAnimatedSprite(
                    new Animation("cuborg", 200, new Vector2D(0, 0), new Vector2D(14, 17)),
                    new Animation("cuborg", 100, new Vector2D(0, 17), new Vector2D(14, 17)),
                    new Animation("cuborg", 100, new Vector2D(0, 34), new Vector2D(14, 17)),
                    new Animation("cuborg", 100, new Vector2D(4, 51), new Vector2D(12, 17)),
                    new Animation("cuborg", 100, new Vector2D(4, 68), new Vector2D(12, 17))
            ), new HealthStats(0, Alliance.ENEMY, 60), 3.35, 175, 500, ProjectileType.LASER);
        }};


    @Override
    public int getMaxPerWorld() {
        return 12;
    }
}
