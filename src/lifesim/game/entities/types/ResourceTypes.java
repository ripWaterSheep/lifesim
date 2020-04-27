package lifesim.game.entities.types;


import lifesim.game.entities.Entity;
import lifesim.util.sprites.AnimatedSprite;
import lifesim.util.sprites.Animation;
import lifesim.util.geom.Vector2D;


public enum ResourceTypes implements Spawnable {

    COIN() {
        @Override
        public Entity spawnEntity() {
            return new Entity("Coin", new AnimatedSprite(new Animation("consumables", 120,
                    new Vector2D(8, 8), 3)));
        }
    }

}
