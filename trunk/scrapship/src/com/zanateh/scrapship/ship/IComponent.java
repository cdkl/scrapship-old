package com.zanateh.scrapship.ship;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface IComponent {

	void attach(IShip ship, float posx, float posy, float rad);
	
	void draw(SpriteBatch batch);
	
	void dispose();
}
