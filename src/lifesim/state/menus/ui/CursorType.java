package lifesim.state.menus.ui;

import lifesim.util.fileIO.ImageLoader;

import java.awt.*;

public enum CursorType {
    DEFAULT("arrow", new Point(0, 0)),
    POINTER("pointer", new Point(3, 0)),
    RETICLE("reticle", new Point(16, 16)),
    HIDDEN("blank", new Point(0, 0));


    public Cursor cursor;

    CursorType(String imageName, Point clickOffset) {
        Image image = ImageLoader.loadImage(imageName);
        cursor = Toolkit.getDefaultToolkit().createCustomCursor(image, clickOffset, "");
    }

}
