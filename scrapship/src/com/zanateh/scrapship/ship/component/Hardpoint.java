package com.zanateh.scrapship.ship.component;

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
		rotatedRelativePosition.rotate(this.component.getRotation());
		rotatedRelativePosition.add(this.component.getPosition());
		
		this.attached = hp;

		// Need to take the provided vector, 
		// rotate our vector to match @ 180 degrees,
		
		float masterRotationDegrees = rotatedRelativePosition.angle();
		float slaveRotationDegrees = hp.position.angle();
		
		float requiredRotationDegrees = 
				(masterRotationDegrees - slaveRotationDegrees + 180) % 360;
		
		Vector2 newPosition = new Vector2(hp.position);
		newPosition.rotate(requiredRotationDegrees);
		
		Vector2 diff = new Vector2(rotatedRelativePosition);
		diff.sub(newPosition);
		
		this.component.attachRelativeComponent(
				hp.component,
				diff.x, diff.y, 
	 			requiredRotationDegrees);

		this.attached.slaveAttach(this);
		
	}
	
	private void slaveAttach(Hardpoint hp) 
	{
		this.attached = hp;
	}
	
	public void detach()
	{
		// Not implemented.
		throw new RuntimeException("Hardpoint detach not implemented");
	}
	
}
