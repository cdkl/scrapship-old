package com.zanateh.scrapship.ship;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public interface IShip {

	public abstract void dispose();

	public abstract void update();

	public abstract void setShipControl(ShipControl control);

	public abstract void removeControl();

	public abstract void draw(SpriteBatch batch);

	public abstract void setPosition(Vector2 pos);

	public abstract void setVelocity(Vector2 vel);
	
	public Body getBody();

}