package game.components;

import util.FontLoader;

import java.awt.*;

public class Label implements IComponent {

    private final int fontSize;

    private final Color textColor;

    public Color getTextColor() {
        return textColor;
    }


    private final Font font;
    public Font getFont() {
        return font;
    }


    public Label(int fontSize, Color textColor) {
        this.fontSize = fontSize;
        this.font = new Font(FontLoader.mainFont, Font.PLAIN, fontSize);
        this.textColor = textColor;
    }

    public Label(int fontSize) {
        this(fontSize, Color.WHITE);
    }

    @Override
    public Label copy() {
        return new Label(fontSize);
    }
}
