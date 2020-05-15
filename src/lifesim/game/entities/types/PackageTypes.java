package lifesim.game.entities.types;

import lifesim.game.entities.Entity;
import lifesim.game.entities.itemEntites.ItemPackage;
import lifesim.game.entities.stats.Alliance;
import lifesim.game.entities.stats.HealthStats;
import lifesim.util.sprites.ImageSprite;

public enum PackageTypes implements Spawnable {

    ITEM_PACKAGE() {
        @Override
        public Entity spawnEntity() {
            return new ItemPackage("Package", new ImageSprite("package"), new HealthStats(0, Alliance.NEUTRAL, 3),
                    8, 2, 3, 1);
        }

        @Override
        public int getMaxPerWorld() {
            return 4;
        }
    },

    REINFORCED_PACKAGE() {
        @Override
        public Entity spawnEntity() {
            return new ItemPackage("Reinforced Package", new ImageSprite("reinforced_package"),
                    new HealthStats(0, Alliance.NEUTRAL, 50), 10, 4, 6, 50);
        }

        @Override
        public int getMaxPerWorld() {
            return 1;
        }
    };



}
