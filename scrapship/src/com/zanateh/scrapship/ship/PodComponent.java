package com.zanateh.scrapship.ship;

import java.util.ArrayList;

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

public class PodComponent implements IComponent {

	float rotation;
	private Sprite sprite;

	Vector2 position;
	
	Fixture fixture = null;
	ComponentShip ship = null;
	
	private ArrayList<Hardpoint> hardpoints = new ArrayList<Hardpoint>();
		
	@Override
	public void draw(SpriteBatch batch) {
		Vector2 worldPos = new Vector2(position);
		fixture.getBody().getTransform().mul(worldPos);

		sprite.setPosition(worldPos.x - (sprite.getWidth()/2), 
						   worldPos.y - sprite.getHeight()/2);

		sprite.setRotation((rotation + fixture.getBody().getAngle()) * MathUtils.radiansToDegrees);
		
		sprite.draw(batch);
	}

	@Override
	public void attach(ComponentShip ship, float posx, float posy, float rad) {
		this.ship = ship;
		Body shipBody = ship.getBody();
		
		FixtureDef fixDef = new FixtureDef();
		CircleShape shape = new CircleShape();
		shape.setRadius(0.5f);
		this.rotation = rad; // can't rotate a sphere
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
	
	public void addHardpoint(Vector2 pos)
	{
		Hardpoint hp = new Hardpoint(this, pos);
		hardpoints.add(hp);
	}

	public Hardpoint getHardpoint(int index) {
		return hardpoints.get(index);
	}

	@Override
	public void attachRelativeComponent(IComponent component, float offsetX, float offsetY, float rad) {
		this.ship.attachComponent(component, offsetX, offsetY, rad);
		
	}
	
}
