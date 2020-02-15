package game.components;

import util.FontLoader;

import java.awt.*;

public class Label implements IComponent {

    private final int fontSize;

    private final Font font;
    public Font getFont() {
        return font;
    }


    public Label(int fontSize) {
        this.fontSize = fontSize;
        this.font = new Font(FontLoader.mainFont, Font.PLAIN, fontSize);
    }

}
