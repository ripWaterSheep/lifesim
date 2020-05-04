package lifesim.state.menus.ui;

import lifesim.util.fileIO.ImageLoader;

import java.awt.*;

public enum CursorType {
    DEFAULT("ui/cursors/arrow", new Point(0, 0)),
    POINTER("ui/cursors/pointer", new Point(3, 0)),
    RETICLE("ui/cursors/reticle", new Point(16, 16)),
    HIDDEN("ui/cursors/blank", new Point(0, 0));


    public final Cursor cursor;

    CursorType(String imageName, Point clickOffset) {
        Image image = ImageLoader.loadImage(imageName);
        cursor = Toolkit.getDefaultToolkit().createCustomCursor(image, clickOffset, "");
    }

}
