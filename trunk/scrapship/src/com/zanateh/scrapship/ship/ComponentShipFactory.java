package com.zanateh.scrapship.ship;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.zanateh.scrapship.scene.ScrapShipStage;
import com.zanateh.scrapship.ship.component.ComponentThruster;
import com.zanateh.scrapship.ship.component.PodComponent;

public class ComponentShipFactory {
	
	public enum ShipType {
		DebugShip,
		PlayerShip
	};
	
	public static ComponentShip createShip(ShipType shipType, World world, ScrapShipStage stage)
	{
		ComponentShip ship = null;
		
		switch(shipType) {
		case DebugShip:
			{
				ship = new ComponentShip(world, stage);
			
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
			}
			break;
		case PlayerShip:
			{
				ship = new ComponentShip(world, stage);
				
				PodComponent comp1 = new PodComponent();
				ship.attachComponent(comp1, 0, 0, 0);
				
				float enginePower = 5;
				ComponentThruster mainEngine = comp1.addThruster(new Vector2(-0.5f,0), new Vector2(1,0), enginePower * 1f );
				ComponentThruster revEngine = comp1.addThruster(new Vector2(0.5f,0), new Vector2(-1,0), enginePower * 0.4f);
				ComponentThruster leftEngineFront = comp1.addThruster(new Vector2(0.5f,0), new Vector2(0,1), enginePower * 0.1f);
				ComponentThruster rightEngineFront = comp1.addThruster(new Vector2(0.5f,0), new Vector2(0,-1), enginePower * 0.1f);
				ComponentThruster leftEngineRear = comp1.addThruster(new Vector2(-0.5f,0), new Vector2(0,1), enginePower * 0.1f);
				ComponentThruster rightEngineRear = comp1.addThruster(new Vector2(-0.5f,0), new Vector2(0,-1), enginePower * 0.1f);

				
				ShipControl shipControl = new ShipControl();
				shipControl.forwardThrusters.add(mainEngine);
				shipControl.leftThrusters.add(leftEngineFront);
				shipControl.leftThrusters.add(rightEngineRear);
				shipControl.rightThrusters.add(rightEngineFront);
				shipControl.rightThrusters.add(leftEngineRear);
				shipControl.reverseThrusters.add(revEngine);

				ship.setShipControl(shipControl);
				
				comp1.addHardpoint(new Vector2(0,0.5f));
				comp1.addHardpoint(new Vector2(0,-0.5f));
				
				PodComponent comp2 = new PodComponent();
				comp2.addHardpoint(new Vector2(-0.5f, 0));
				comp1.getHardpoint(0).attach(comp2.getHardpoint(0));
				PodComponent comp3 = new PodComponent();
				comp3.addHardpoint(new Vector2(-0.5f, 0));
				comp1.getHardpoint(1).attach(comp3.getHardpoint(0));
				

				
			}
			break;
		}
		
		return ship;
	}
	
	
}
