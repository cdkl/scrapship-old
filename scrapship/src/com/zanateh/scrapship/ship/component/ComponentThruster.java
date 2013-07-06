package com.zanateh.scrapship.ship.component;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.zanateh.scrapship.ship.IThrust;

public class ComponentThruster implements IThrust {

	// Position of thruster on component
	Vector2 pos;
	// direction of acceleration (NOT the direction of "Thrust")
	Vector2 vec;
	// Strength of thruster
	float strength;
	// Current thruster power setting: should be 0-1
	float power;
	
	PodComponent component;
	
	private Sprite sprite;
	
	public ComponentThruster(PodComponent component, Vector2 pos, Vector2 vec, float strength) {
		this.component = component;
		this.pos = new Vector2(pos);
		this.vec = new Vector2(vec);
		this.strength = strength;
		this.power = 0;
		
		sprite = new Sprite(new Texture(Gdx.files.internal("data/thruster.png")));
		sprite.setSize(0.25f, 0.25f);
		sprite.setOrigin(0, 0);//sprite.getHeight()/2);
	}

	@Override
	public void setThrust(float thrust) {
		power = thrust;
	}

	public void update() {
		Body body = component.getFixture().getBody();
		applyThrust(body);
	}
	
	@Override 
	public void applyThrust(Body body) {		
		Vector2 shipPos = new Vector2(pos);
		component.transformPositionToParent(pos);
		Vector2 shipVec = new Vector2(vec);
		component.transformVectorToParent(shipVec);
		
		body.applyForce(body.getWorldVector(shipVec.scl(strength*power)), body.getWorldPoint(shipPos), true);
	}
	
	public void draw(SpriteBatch batch, Vector2 parentPos, float parentRotationDeg) {
		if( power > 0 ) {
			Vector2 spriteRenderOffset = new Vector2(sprite.getWidth(), sprite.getHeight()/2);
			Vector2 spriteVec = new Vector2(vec);
			component.transformVectorToParent(spriteVec);
			spriteRenderOffset.rotate(spriteVec.angle() + 180);
					
			Vector2 spritePos = new Vector2(pos);
			spritePos.add(spriteRenderOffset);
			spritePos.rotate(parentRotationDeg);
			spritePos.add(parentPos);		
			sprite.setPosition(spritePos.x, spritePos.y);
			
			float rotation = parentRotationDeg + spriteVec.angle();
			sprite.setRotation(rotation);
			sprite.draw(batch);
		}
	}
	
	
}
