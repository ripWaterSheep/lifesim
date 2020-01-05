package game;

import game.components.player.Player;
import game.components.GameComponent;
import game.components.Structure;
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
		 * Structures and MobileEntities are both found in Structure.instances.
		 * 
		 */

		 
		usedComponentInstances = new ArrayList<>();
        /*usedComponents.add(Player.getInstance());
        
        usedComponents.addAll(ArrayListMethods.getReverse(Structure.getInstances()));
        usedComponents.addAll(World.getInstances());
		*/
		
		/* Arraylists of instances are passed to usedInstances to keep references the lists to which new
		 * objects are added as they are added mid game (like player created projectiles)
		 */
		 

		usedComponentInstances.add(Player.getInstances());
		Collections.reverse(Structure.getInstances());
		usedComponentInstances.add(Structure.getInstances());
		usedComponentInstances.add(World.getInstances());
		
        Collections.reverse(usedComponentInstances);
        
        for (ArrayList<? extends GameComponent> instances: usedComponentInstances) {
			for (GameComponent instance : instances) {
				instance.setup(panel);
			}
		}
		
    }


    public void loop(Graphics g) {
		for (ArrayList<? extends GameComponent> instances: usedComponentInstances) {
			for (GameComponent instance : instances)
				instance.act();
		}
        Stat.retrieveValues();

		for (ArrayList<? extends GameComponent> instances:usedComponentInstances) {
			for (GameComponent instance : instances)
				instance.draw(g);
		}
		
        Overlay.drawOverlays(g);
        debug();
        
    }


}
