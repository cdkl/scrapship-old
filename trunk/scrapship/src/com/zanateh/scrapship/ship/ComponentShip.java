package com.zanateh.scrapship.ship;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

public class ComponentShip implements IShip {

	private Body body;
	
	private ShipControl control;
	
	private ArrayList<IComponent> components = new ArrayList<IComponent>();
	
	public ComponentShip(World world)
	{
		BodyDef def = new BodyDef();
		def.position.set(0,0);
		def.type = BodyDef.BodyType.DynamicBody;
		
		def.angularDamping = 0.8f;
		def.linearDamping = 0.2f;
		
		body = world.createBody(def);
		
		
		
//		attachComponent(new PodComponent(), 0, 0, 0);
//		attachComponent(new PodComponent(), 1, 0, MathUtils.PI);
//		attachComponent(new PodComponent(), 0, 1, MathUtils.PI/2);
//		attachComponent(new PodComponent(), 0, 2, MathUtils.PI*1.5f);
	}
	
	public void attachComponent(IComponent component, float x, float y, float rad) {
		component.attach(this, x, y, rad);
		components.add(component);
	}

	@Override
	public void dispose() {
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setShipControl(ShipControl control) {

		if( this.control != null ) {
			removeControl();
		}

		this.control = control;
//			this.control.forwardThrusters.add(mainEngine);
//			this.control.reverseThrusters.add(revEngine);
//			this.control.leftThrusters.add(leftEngine);
//			this.control.rightThrusters.add(rightEngine);


	}

	@Override
	public void removeControl() {
		this.control.remove();
		this.control = null;

	}

	@Override
	public void draw(SpriteBatch batch) {
		for( IComponent component : components ) {
			component.draw(batch);
		}

	}

	@Override
	public void setPosition(Vector2 pos) {
		body.setTransform(pos, 0);

	}

	@Override
	public void setVelocity(Vector2 vel) {
		body.setLinearVelocity(vel);

	}

	@Override
	public Body getBody() {
		return body;
	}

}
