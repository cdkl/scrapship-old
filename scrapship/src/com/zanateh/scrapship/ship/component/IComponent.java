package com.zanateh.scrapship.ship.component;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.zanateh.scrapship.ship.ComponentShip;

public interface IComponent {

	void attach(ComponentShip ship, float posx, float posy, float rad);
	
	void attachRelativeComponent(IComponent component, float offsetX, float offsetY, float rad);
	
	void update();
	void draw(SpriteBatch batch);
	
	void transformPositionToParent(Vector2 position);
	void transformVectorToParent(Vector2 vector);
	
	void dispose();
}
