package game;

import game.components.entities.Entity;
import game.components.GameComponent;
import game.activity.EntityManagement;
import game.activity.controls.MouseControls;
import game.components.structures.Structure;
import game.components.World;
import game.overlay.Overlay;
import game.overlay.DisplayedStat;
import util.FindComponent;

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

    public static int getCurrentFrame() { return currentFrame; }


    public void debug() {
        currentFrame++;
		//System.out.println(Player.getInstance().getTopTouching().getName());
    }


    public void init() {
		// Add all initially created components to the component list to be iterated through.
		usedComponents = new ArrayList<>();

		usedComponents.addAll(Entity.getEntityInstances());
		usedComponents.addAll(Structure.getStructureInstances());
		usedComponents.addAll(World.getWorldInstances());
		
        Collections.reverse(usedComponents);
		EntityManagement.manageEntities();

		for (GameComponent component : usedComponents) {
			component.init();
		}
		
    }


    public void loop(Graphics g) {

		DisplayedStat.retrieveValues();
		for (GameComponent component: usedComponents) {
			component.update();
			if (component.isOnScreen())
				component.draw(g);
		}
        Overlay.drawOverlays(g);
		EntityManagement.manageEntities();

        debug();
		MouseControls.reset();

    }

}
