package com.zanateh.scrapship.ship;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class ComponentShipFactory {
	
	public enum ShipType {
		DebugShip
	};
	
	public static ComponentShip createShip(ShipType shipType, World world)
	{
		ComponentShip ship = null;
		
		switch(shipType) {
		case DebugShip:
			ship = new ComponentShip(world);
		
			PodComponent comp1 = new PodComponent();
			comp1.addHardpoint(new Vector2(0.5f,0));
			comp1.addHardpoint(new Vector2(0,0.5f));
			ship.attachComponent(comp1, 0, 0, 0);
			PodComponent comp2 = new PodComponent();
			comp2.addHardpoint(new Vector2(0.5f,0));
			comp1.getHardpoint(0).attach(comp2.getHardpoint(0));
			PodComponent comp3 = new PodComponent();
			comp3.addHardpoint(new Vector2(0.5f,0));
			comp3.addHardpoint(new Vector2(-0.5f,0));
			comp1.getHardpoint(1).attach(comp3.getHardpoint(1));
			PodComponent comp4 = new PodComponent();
			comp4.addHardpoint(new Vector2(0.5f,0));
			comp3.getHardpoint(0).attach(comp4.getHardpoint(0));

			break;
		}
		
		return ship;
	}
	
	
}
