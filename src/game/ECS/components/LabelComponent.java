package game.ECS.components;

import util.FontLoader;

import java.awt.*;

/** Defines characteristics of a visible label on the entity.
 * An entity only has a label if it has this component.
 */
public class LabelComponent implements IComponent {

    private final int fontSize;

    private final Color textColor;

    public Color getTextColor() {
        return textColor;
    }


    private final Font font;
    public Font getFont() {
        return font;
    }


    public LabelComponent(int fontSize, Color textColor) {
        this.fontSize = fontSize;
        this.font = new Font(FontLoader.mainFont, Font.PLAIN, fontSize);
        this.textColor = textColor;
    }

    public LabelComponent(int fontSize) {
        this(fontSize, Color.WHITE);
    }

    @Override
    public LabelComponent copy() {
        return new LabelComponent(fontSize);
    }
}
