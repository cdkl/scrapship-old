package com.zanateh.scrapship.ship;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.zanateh.scrapship.camera.IHasPosition;

public interface IShip extends IHasPosition {

	public void dispose();

	public void update();

	public void setShipControl(ShipControl control);
	
	public ShipControl getShipControl();

	public void removeControl();

	public void draw(SpriteBatch batch);

	public void setPosition(Vector2 pos);

	public void setVelocity(Vector2 vel);
	
	public Body getBody();

}