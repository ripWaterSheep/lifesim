package game;

import game.components.player.Player;
import game.components.GameComponent;
import game.components.structures.Structure;
import game.components.World;
import game.overlay.Overlay;
import game.overlay.Stat;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;


public class GameSession {


    private static GameLayout usedLayout = new GameLayout();

    public static GameLayout getUsedLayout() { return usedLayout; }


    /** Holds all instances used in the game */
    private static ArrayList<ArrayList<? extends GameComponent>> usedComponentInstances;

    public static ArrayList<ArrayList<? extends GameComponent >> getUsedComponents() { return usedComponentInstances; }


    private static int currentFrame = 0;

    public static int CurrentFrame() { return currentFrame; }


    public void debug() {
        currentFrame++;
		//System.out.println(Player.getInstance().getTopTouching().getLabel());
    }


    public void init(JPanel panel) {
		
		/* Create arraylist of arraylists of all components separated by class.
		 * In the beginning of each constructor for every GameComponent subclass,
		 * the instance is added to the static arraylist "instances" which holds
		 * all object references of the class
		 * 
		 * Since MobileEntity is a subclass of structure I just went frick it and didn't put any effort to make
		 * a separate arraylist for MobileEntity instances. it works. The thing is, MobileEntity uses the Structure class
		 * constructor inside its constructor to fill in inherited fields (using super()) so i don't have to rewrite it,
		 * but in the constructor the instance just gets added to Structure.instances. Not a big deal. Just be aware that
		 * Structures and MobileEntities are both found in Structure.instances. MobileEntity has its own instance arraylist
		 * but that's just for using polymorphism with non-inherited functions (idk if it can be cast to avoid needing this
		 * cause it breaks when I try).
		 * It's the same thing with all other Structure Subtypes.
		 */
		 
		usedComponentInstances = new ArrayList<>();
		/* Arraylists of instances are passed to usedInstances to keep references the ArrayLists so that new
		 * objects that are added mid game will be referenced (like player created projectiles).
		 */

		usedComponentInstances.add(Player.getInstances());
		usedComponentInstances.add(Structure.getInstances());
		usedComponentInstances.add(World.getInstances());
		
        Collections.reverse(usedComponentInstances);
        
        for (ArrayList<? extends GameComponent> instances: usedComponentInstances) {
			for (GameComponent component : instances) {
				component.setup(panel);
			}
		}
		
    }


    public void loop(Graphics g) {
		for (ArrayList<? extends GameComponent> instances: usedComponentInstances) {
			for (GameComponent component : instances)
				component.act();
		}
        Stat.retrieveValues();

		for (ArrayList<? extends GameComponent> instances:usedComponentInstances) {
			for (GameComponent component : instances) {
				if (component.onScreen())
					component.draw(g);
			}
		}
		
        Overlay.drawOverlays(g);
        debug();
        
    }


}
