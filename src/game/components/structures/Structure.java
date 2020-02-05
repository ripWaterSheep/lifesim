package game.components.structures;

import game.components.Component;
import game.organization.World;
import game.components.entities.stats.PlayerStats;
import game.components.entities.player.Player;
import drawing.DrawString;
import drawing.MyFont;

import java.awt.*;

public class Structure extends Component {

    // Used for interacting with player
    protected Player player;
    protected PlayerStats stats;

    protected Font labelFont;
    protected int fontSize = 0;


    public Structure(String name, double x, double y, double width, double height,  boolean elliptical, Color color, int fontSize) {
        super(name, x, y, width, height, elliptical, color);
        this.fontSize = fontSize;
        labelFont = new Font(MyFont.getMainFont(), Font.PLAIN, fontSize);

        this.elliptical = elliptical;

    }

    public Structure(String name, double x, double y, double width, double height, boolean elliptical, Color color) {
        this(name, x, y, width, height, elliptical, color, 0);
    }


    public Structure(String name, double x, double y, double scale, String imageName) {
        super(name, x, y, scale, imageName);
    }


    public void onTouch() {}

    public void onClick() {}


    public void init(World world) {
        super.init(world);

        player = Player.getInstance();
        stats = player.getStats();
    }


    @Override
    public void update() {

    }


    @Override
    public void draw(Graphics g) {
        super.draw(g);

        if (fontSize > 0)
            DrawString.drawCenteredString(g, name, getShape().getBounds(), labelFont, Color.WHITE);
    }

}
