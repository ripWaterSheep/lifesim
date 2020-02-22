package game.components.entities.player;

import util.drawing.MyImage;

public class PlayerImageManager {

    private final Player player;

    PlayerImageManager(Player player) {
        this.player = player;
    }

    private MyImage currentImage;

    MyImage getCurrentImage() {
        decideCorrectImage();
        return currentImage;
    }


    private final MyImage dead = new MyImage("dead", Player.getInstance());


    private void decideCorrectImage() {
        if (!player.getStats().isAlive()) {
            currentImage = dead;
        }
    }

}
