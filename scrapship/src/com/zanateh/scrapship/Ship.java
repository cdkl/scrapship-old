package com.zanateh.scrapship;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Ship {
	public Body body;
	
	private Texture shipImage;
	private Sprite sprite;
	
	private ShipControl control;
	
	private IThrust mainEngine;
	private IThrust revEngine;
	private IThrust leftEngine;
	private IThrust rightEngine;
	private ArrayList<IThrust> engines = new ArrayList<IThrust>();

	
	public Ship(World world) 
	{
		BodyDef def = new BodyDef();
		def.position.set(0,0);
		def.type = BodyDef.BodyType.DynamicBody;
		def.angularDamping = 0.8f;
		def.linearDamping = 0.2f;
		
		body = world.createBody(def);
		FixtureDef fixDef = new FixtureDef();
		PolygonShape polyShape = new PolygonShape();
		polyShape.setAsBox(0.5f, 0.5f);
		fixDef.shape = polyShape;
		fixDef.density = 1.0f;
		fixDef.restitution = 0.5f;
		fixDef.friction = 0.2f;
		body.createFixture(fixDef);
//		polyShape.setAsBox(0.5f, 0.5f, new Vector2(0,0.75f), 0.1f);
//		body.createFixture(fixDef);
		//body.applyLinearImpulse(new Vector2(10,0), new Vector2(0,0.0f), true);
		
		shipImage = new Texture(Gdx.files.internal("data/spaceship128.png"));
		sprite = new Sprite(shipImage);
		sprite.setSize(1,1);
		sprite.setOrigin(sprite.getWidth()/2, sprite.getHeight()/2);
		
		float enginePower = 5;
		mainEngine = new BasicThruster(new Vector2(0,0), new Vector2(1,0), enginePower * 1f );
		engines.add(mainEngine);
		revEngine = new BasicThruster(new Vector2(0,0), new Vector2(-1,0), enginePower * 0.4f);
		engines.add(revEngine);
		leftEngine = new BasicThruster(new Vector2(0.5f,0), new Vector2(0,1), enginePower * 0.2f);
		engines.add(leftEngine);
		rightEngine = new BasicThruster(new Vector2(0.5f,0), new Vector2(0,-1), enginePower * 0.2f);
		engines.add(rightEngine);
		
	}

	public void dispose() {
		shipImage.dispose();
		if(control != null) {
			control.dispose();
		}
	}

	public void update() {
		for( IThrust engine : engines ) {
			engine.applyThrust(body);
		}
	}
	
	public void setShipControl(ShipControl control) {
		if( this.control != null ) {
			removeControl();
		}

		this.control = control;
		this.control.forwardThrusters.add(mainEngine);
		this.control.reverseThrusters.add(revEngine);
		this.control.leftThrusters.add(leftEngine);
		this.control.rightThrusters.add(rightEngine);
	}
	
	public void removeControl() {
		this.control.remove();
		this.control = null;
	}
	
	public void draw(SpriteBatch batch) {
		// TODO Auto-generated method stub
		sprite.setPosition(body.getPosition().x - (sprite.getWidth()/2), body.getPosition().y - (sprite.getHeight()/2));
		sprite.setRotation(body.getAngle() * MathUtils.radiansToDegrees);
		sprite.draw(batch);
	}

	public void setPosition(Vector2 pos) {
		body.setTransform(pos, 0);
	}
	
	public void setVelocity(Vector2 vel) {
		body.setLinearVelocity(vel);
	}
	

}
