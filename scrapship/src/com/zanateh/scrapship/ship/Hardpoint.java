package com.zanateh.scrapship.ship;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Hardpoint {

	Hardpoint attached = null;
	Vector2 position;
	PodComponent component = null;
	
	public Hardpoint(PodComponent component, Vector2 pos)
	{
		this.component = component;
		this.position = new Vector2(pos);
	}
	
	
	public void attach(Hardpoint hp)
	{
		if( attached != null )
		{
			detach();
		}
	
		// Vector of hardpoint to origin
		Vector2 rotatedRelativePosition = new Vector2(position);
		rotatedRelativePosition.rotate(this.component.rotation * MathUtils.radiansToDegrees);
		rotatedRelativePosition.add(this.component.position);
		
		this.attached = hp;
		this.attached.slaveAttach(this, rotatedRelativePosition);
		
		
	}
	
	private void slaveAttach(Hardpoint hp, Vector2 rotatedRelativePosition) 
	{
		// Need to take the provided vector, 
		// rotate our vector to match @ 180 degrees,
		
		float masterRotationDegrees = rotatedRelativePosition.angle();
		float slaveRotationDegrees = this.position.angle();
		
		float requiredRotationDegrees = 
				(masterRotationDegrees - slaveRotationDegrees + 180) % 360;
		
		Vector2 newPosition = new Vector2(this.position);
		newPosition.rotate(requiredRotationDegrees);
		
		Vector2 diff = new Vector2(rotatedRelativePosition);
		diff.sub(newPosition);
		
	 	((ComponentShip)hp.component.getShip()).attachComponent(this.component, 
	 			diff.x, diff.y, 
	 			requiredRotationDegrees * MathUtils.degreesToRadians);
	}
	
	public void detach()
	{
		// Not implemented.
		throw new RuntimeException("Hardpoint detach not implemented");
	}
	
}
