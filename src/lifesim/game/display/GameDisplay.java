package lifesim.game.display;


import java.awt.*;

public abstract class GameDisplay {

     public abstract void update();
     public abstract void render(Graphics2D g2d);

     public boolean isOpen() {
          return true;
     }

}
