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
import lifesim.util.sprites.ImageSprite;



public enum EnemyType implements Spawnable {

    MELEE() {
        @Override
        public Entity spawnEntity() {
            return new AIEntity("Melee enemy", new DirectionalAnimatedSprite(
                    new Animation("bot", 200, new Vector2D(0, 0), new Vector2D(13, 16)),
                    new Animation("bot", 100, new Vector2D(0, 16), new Vector2D(13, 16)),
                    new Animation("bot", 100, new Vector2D(0, 32), new Vector2D(13, 16)),
                    new Animation("bot", 100, new Vector2D(0, 48), new Vector2D(13, 16)),
                    new Animation("bot", 100, new Vector2D(0, 64), new Vector2D(13, 16))
            ),
                    new HealthStats(2.5, Alliance.ENEMY, 50), 3.25, 200);
    }},


    RANGED() {
        @Override
        public Entity spawnEntity() {
            return new RangedAIEntity("Ranged", new AnimatedSprite(
                    new Animation("ranged", 150, new Vector2D(0, 0), new Vector2D(12, 16))),
                    new HealthStats(0, Alliance.ENEMY, 65), 3, 250, 1000, ProjectileType.LASER);
    }};


    @Override
    public int getMaxPerWorld() {
        return 10;
    }
}
