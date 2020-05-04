package lifesim.game.overlay;


import java.awt.*;

public abstract class Overlay {


     public abstract void update();
     public abstract void render(Graphics2D g2d);

     public boolean isShowing() {
          return true;
     }

}
