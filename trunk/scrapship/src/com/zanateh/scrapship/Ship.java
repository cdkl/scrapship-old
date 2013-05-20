package com.zanateh.scrapship;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

public class Ship {
	public Body body;
	
	private Texture shipImage;
	public Rectangle shipRect;
		
	public Ship(World world) 
	{
		BodyDef def = new BodyDef();
		def.position.set(100,100);
		def.type = BodyDef.BodyType.DynamicBody;
		
		body = world.createBody(def);
		FixtureDef fixDef = new FixtureDef();
		fixDef.shape = new CircleShape();
		fixDef.shape.setRadius(1);
		fixDef.density = 1.0f;
		body.createFixture(fixDef);
		body.applyLinearImpulse(new Vector2(100,0), new Vector2(0,1), true);
		
		shipImage = new Texture(Gdx.files.internal("data/spaceship128.png"));
		shipRect = new Rectangle(0, 0, 48, 48);
	}

	public void dispose() {
		shipImage.dispose();
	}

	public void update() {
		shipRect.x = body.getPosition().x;
		shipRect.y = body.getPosition().y;
	}
	
	public void draw(SpriteBatch batch) {
		// TODO Auto-generated method stub
		batch.draw(shipImage, shipRect.x, shipRect.y);
	}
	

}
