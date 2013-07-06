package com.zanateh.scrapship.ship.component;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.zanateh.scrapship.ship.ComponentShip;

public class PodComponent implements IComponent {

	float rotation;
	private Sprite sprite;

	Vector2 position;
	
	Fixture fixture = null;
	ComponentShip ship = null;
	
	private ArrayList<ComponentThruster> thrusters = new ArrayList<ComponentThruster>();
	
	private ArrayList<Hardpoint> hardpoints = new ArrayList<Hardpoint>();
	
	public PodComponent() {
	}
	
	@Override
	public void update() {
		for(ComponentThruster thruster : thrusters) {
			thruster.update();
		}
	}
	
	@Override
	public void draw(SpriteBatch batch) {
		Vector2 worldPos = new Vector2(position);
		fixture.getBody().getTransform().mul(worldPos);

		sprite.setPosition(worldPos.x - (sprite.getWidth()/2), 
						   worldPos.y - sprite.getHeight()/2);

		float worldRot = rotation + (fixture.getBody().getAngle() * MathUtils.radiansToDegrees);
		sprite.setRotation( worldRot );
		
		sprite.draw(batch);
		
		for(ComponentThruster thruster : thrusters) {
			thruster.draw(batch, worldPos, worldRot);
		}
	}

	@Override
	public void attach(ComponentShip ship, float posx, float posy, float deg) {
		this.ship = ship;
		Body shipBody = ship.getBody();
		
		FixtureDef fixDef = new FixtureDef();
		CircleShape shape = new CircleShape();
		shape.setRadius(0.5f);
		this.rotation = deg; // can't rotate a sphere
		position = new Vector2(posx, posy);
		shape.setPosition(position);
		
		fixDef.shape = shape;
		fixDef.density = 1.0f;
		fixDef.restitution = 0.5f;
		fixDef.friction = 0.6f;
		this.fixture = shipBody.createFixture(fixDef);
		
		Texture image = new Texture(Gdx.files.internal("data/pod.png"));
		sprite = new Sprite(image);
		sprite.setSize(1,1);
		sprite.setOrigin(sprite.getWidth()/2, sprite.getHeight()/2);
	}

	@Override
	public void dispose() {
	}
	
	public ComponentShip getShip()
	{
		return ship;
	}
	
	public Fixture getFixture()
	{
		return this.fixture;
	}
	
	public void addHardpoint(Vector2 pos)
	{
		Hardpoint hp = new Hardpoint(this, pos);
		hardpoints.add(hp);
	}
	
	public Hardpoint getHardpoint(int index) {
		return hardpoints.get(index);
	}

	public ComponentThruster addThruster(Vector2 pos, Vector2 vec, float strength) {
		ComponentThruster thruster = new ComponentThruster(this, pos, vec, strength);
		thrusters.add(thruster);
		return thruster;
	}
	
	@Override
	public void attachRelativeComponent(IComponent component, float offsetX, float offsetY, float rad) {
		this.ship.attachComponent(component, offsetX, offsetY, rad);
		
	}

	@Override
	public void transformPositionToParent(Vector2 position) {
		position.add(this.position);
	}

	@Override
	public void transformVectorToParent(Vector2 vector) {
		vector.rotate(this.rotation);
	}
	
}
