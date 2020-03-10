package com.lifesim.main.game.ecs.components;

import com.lifesim.main.util.drawing.FontManager;

import java.awt.*;

/** Defines characteristics of a visible label on the entity.
 * An entity only has a label if it has this component.
 */
public class LabelComponent implements CopyableComponent {

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
        this.font = FontManager.getMainFont(fontSize);
        this.textColor = textColor;
    }

    public LabelComponent(int fontSize) {
        this(fontSize, Color.WHITE);
    }

    @Override
    public LabelComponent copyInitialState() {
        return new LabelComponent(fontSize);
    }


}
