package com.zanateh.scrapship.ship;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.zanateh.scrapship.camera.IHasPosition;
import com.zanateh.scrapship.ship.component.IComponent;

public class ComponentShip implements IShip {

	private Body body;
	
	private ShipControl control = null;
	
	private ArrayList<IComponent> components = new ArrayList<IComponent>();
	
	public ComponentShip(World world)
	{
		BodyDef def = new BodyDef();
		def.position.set(0,0);
		def.type = BodyDef.BodyType.DynamicBody;
		
		def.angularDamping = 0.8f;
		def.linearDamping = 0.2f;
		
		body = world.createBody(def);
				
	}
	
	public void attachComponent(IComponent component, float x, float y, float deg) {
		component.attach(this, x, y, deg);
		components.add(component);
	}

	@Override
	public void dispose() {
	}

	@Override
	public void update() {
		for(IComponent component : components ) {
			component.update();
		}
	}

	@Override
	public void setShipControl(ShipControl control) {

		if( this.control != null ) {
			removeControl();
		}

		this.control = control;
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

	@Override
	public Vector2 getPosition() {
		return body.getTransform().getPosition();
	}

	@Override
	public ShipControl getShipControl() {
		return this.control;
	}

}
