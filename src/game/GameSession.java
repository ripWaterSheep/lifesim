package game;

import game.components.entities.Entity;
import game.components.GameComponent;
import game.components.structures.Structure;
import game.components.World;
import game.overlay.Overlay;
import game.overlay.Stat;
import util.GarbageCollection;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;


public class GameSession {


    private static Layout usedLayout = new Layout();

    public static Layout getUsedLayout() { return usedLayout; }


    /** Holds all instances used in the game */
    private static ArrayList< GameComponent> usedComponents;

    public static ArrayList< GameComponent> getUsedComponents() { return usedComponents; }


    private static int currentFrame = 0;

    public static int CurrentFrame() { return currentFrame; }


    public void debug() {
        currentFrame++;
		//System.out.println(Player.getInstance().getTopTouching().getName());
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
		 
		usedComponents = new ArrayList<>();
		/* Arraylists of instances are passed to usedInstances to keep references the ArrayLists so that new
		 * objects that are added mid game will be referenced (like player created projectiles).
		 */

		usedComponents.addAll(Entity.getInstances());
		usedComponents.addAll(Structure.getInstances());
		usedComponents.addAll(World.getInstances());
		
        Collections.reverse(usedComponents);
		Entity.addSpawnedEntities();

		for (GameComponent component : usedComponents) {
			component.setup(panel);
		}
		
    }


    public void loop(Graphics g) {

		Stat.retrieveValues();
		for (GameComponent component: usedComponents) {
			component.act();
			if (component.isOnScreen())
				component.draw(g);
		}

        Overlay.drawOverlays(g);
		Entity.addSpawnedEntities();
		GarbageCollection.removeExpiredEntities();

        debug();

    }

}
