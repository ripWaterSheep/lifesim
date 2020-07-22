package lifesim.game.item;

import lifesim.io.input.MouseInput;
import lifesim.game.entities.Player;
import lifesim.io.output.GameWindow;
import lifesim.game.handlers.World;

import java.awt.*;


abstract class ConsumeFunctionality extends ItemFunctionality {

    @Override
    public boolean canBeUsed(World world, Player player) {
        return MouseInput.right.isClicked();
    }

    @Override
    public void render(Graphics2D g2d, Player player, GameWindow window) {

    }
}
