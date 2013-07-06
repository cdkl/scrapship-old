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
import com.badlogic.gdx.physics.box2d.Transform;
import com.zanateh.scrapship.scene.ScrapShipActorGroup;
import com.zanateh.scrapship.ship.ComponentShip;

public class PodComponent extends ScrapShipActorGroup {

	private Sprite sprite;
	
	Fixture fixture = null;
	ComponentShip ship = null;
	
	private ArrayList<ComponentThruster> thrusters = new ArrayList<ComponentThruster>();
	
	private ArrayList<Hardpoint> hardpoints = new ArrayList<Hardpoint>();
	
	public PodComponent() {
	}
	
//	public void act(float delta) {
//	}
//	
//	@Override
//	public void postUpdate()
//	{		
//	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		
		Vector2 worldPos = new Vector2(getPosition());
	
		sprite.setPosition(worldPos.x - (sprite.getWidth()/2), 
						   worldPos.y - sprite.getHeight()/2);

		sprite.setRotation( getRotation() );
		
		sprite.draw(batch);
	}


	public void attach(ComponentShip ship, float posx, float posy, float deg) {
		this.ship = ship;
		Body shipBody = ship.getBody();
		
		FixtureDef fixDef = new FixtureDef();
		CircleShape shape = new CircleShape();
		shape.setRadius(0.5f);
		setRotation(deg); // can't rotate a sphere
		this.setX(posx);
		this.setY(posy);
		shape.setPosition(getPosition());
		
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

	public void dispose() {
	}
	
	public void setPosition( Vector2 pos)
	{
		this.setPosition(pos.x, pos.y);
	}
	
	public Vector2 getPosition()
	{
		return new Vector2(this.getX(), this.getY());
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
		this.addActor(thruster);
		return thruster;
	}
	
	public void attachRelativeComponent(PodComponent component, float offsetX, float offsetY, float rad) {
		this.ship.attachComponent(component, offsetX, offsetY, rad);
		
	}

	public void transformPositionToParent(Vector2 position) {
		position.add(this.getPosition());
	}

	public void transformVectorToParent(Vector2 vector) {
		vector.rotate(this.getRotation());
	}
	
}
